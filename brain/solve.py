import tensorflow as tf  # http://blog.topspeedsnail.com/archives/10399
from sklearn import preprocessing  # 使用scikit-learn进行数据预处理
import pandas as pd
import numpy as np
import rest

checkpoint_dir = "./Model"


# 训练神经网络
def solve(raw_data):
    solve_data = pd.DataFrame(data=raw_data)
    solve_id = np.asarray(solve_data.iloc[:, 0])
    solve_x = preprocessing.scale(np.asarray(solve_data.ix[:, 1:100]))

    with tf.Session() as sess:
        ckpt = tf.train.get_checkpoint_state(checkpoint_dir)
        if ckpt and ckpt.model_checkpoint_path:
            saver = tf.train.import_meta_graph(ckpt.model_checkpoint_path + ".meta")
            saver.restore(sess, ckpt.model_checkpoint_path)
        else:
            pass

        graph = tf.get_default_graph()
        x = graph.get_tensor_by_name('input_x:0')
        predict_output = graph.get_tensor_by_name('predict_output:0')
        predict = [tf.argmax(predict_output, 1), tf.reduce_max(predict_output, 1)]

        prediction, confidence = sess.run(predict, feed_dict={x: solve_x})
        print("prediction: ", prediction, " confidence: ", confidence)
        result = pd.concat([pd.DataFrame(solve_id), pd.DataFrame(prediction)], axis=1)
        result = pd.DataFrame(result).values.tolist()
        print(result)
    return result


def run(job_id, raw_data):
    result = solve(raw_data)
    rest.send_ai_result(job_id, "SOLVE", data=result)
