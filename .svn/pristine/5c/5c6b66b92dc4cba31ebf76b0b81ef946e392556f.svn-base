import tensorflow as tf  # http://blog.topspeedsnail.com/archives/10399
from sklearn import preprocessing  # 使用scikit-learn进行数据预处理
import pandas as pd
import numpy as np
import rest

checkpoint_dir = "./Model"


# network_complexity = "simple"
# 定义神经网络
def make_data(raw_data):
    data = pd.DataFrame(data=raw_data)
    data_x = preprocessing.scale(np.asarray(data.ix[:, 0:99]))
    data_y = np.asarray(data.iloc[:, -1])
    data_y = np.asarray(pd.get_dummies(data_y))

    split_len = round(data.shape[0] * 0.9)

    train_x = data_x[:split_len]
    train_y = data_y[:split_len]

    test_x = data_x[split_len:]
    test_y = data_y[split_len:]

    total_batches = train_y.shape[0]
    output = train_y.shape[1]

    x = tf.placeholder(tf.float32, shape=[None, 100], name='input_x')  # 网络输入
    y = tf.placeholder(tf.float32, [None, output], name='input_y')  # 网络输出
    return train_x, train_y, test_x, test_y, total_batches, output, x, y


# 定义神经网络
def neural_networks(x, output):
    # --------------------- DNN  ------------------- #
    w_1 = tf.Variable(tf.truncated_normal([100, 32], stddev=0.1))
    b_1 = tf.Variable(tf.constant(0.0, shape=[32]))

    w_2 = tf.Variable(tf.truncated_normal([32, 32], stddev=0.1))
    b_2 = tf.Variable(tf.constant(0.0, shape=[32]))

    w_3 = tf.Variable(tf.truncated_normal([32, 16], stddev=0.1))
    b_3 = tf.Variable(tf.constant(0.0, shape=[16]))

    w_4 = tf.Variable(tf.truncated_normal([16, output], stddev=0.1))
    b_4 = tf.Variable(tf.constant(0.0, shape=[output]))
    #########################################################
    layer_7 = tf.nn.tanh(tf.add(tf.matmul(x, w_1), b_1))
    layer_8 = tf.nn.tanh(tf.add(tf.matmul(layer_7, w_2), b_2))
    layer_9 = tf.nn.softmax(tf.add(tf.matmul(layer_8, w_3), b_3))
    out = tf.nn.softmax(tf.add(tf.matmul(layer_9, w_4), b_4), name="predict_output")
    return out


# 训练神经网络
def train_neural_networks(raw_data):
    train_x, train_y, test_x, test_y, total_batches, output, x, y = make_data(raw_data)
    predict_output = neural_networks(x, output)

    s_cost_function = -tf.reduce_sum(y * tf.log(predict_output))
    s_optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.01).minimize(s_cost_function)

    correct_prediction = tf.equal(tf.argmax(predict_output, 1), tf.argmax(y, 1))
    accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

    training_epochs = 100
    batch_size = 10

    saver = tf.train.Saver()
    with tf.Session() as sess:
        ckpt = tf.train.get_checkpoint_state(checkpoint_dir)
        if ckpt and ckpt.model_checkpoint_path:
            print(ckpt)
            # saver.restore(sess, ckpt.model_checkpoint_path)
        else:
            pass

        sess.run(tf.global_variables_initializer())

        # ---------------- Training NN - Supervised Learning ------------------ #
        for epoch in range(training_epochs):
            epoch_costs = np.empty(0)
            for b in range(total_batches):
                offset = (b * batch_size) % (train_x.shape[0] - batch_size)
                batch_x = train_x[offset:(offset + batch_size), :]
                batch_y = train_y[offset:(offset + batch_size), :]
                _, c = sess.run([s_optimizer, s_cost_function], feed_dict={x: batch_x, y: batch_y})
                epoch_costs = np.append(epoch_costs, c)

            accuracy_in_train_set = sess.run(accuracy, feed_dict={x: train_x, y: train_y})
            accuracy_in_test_set = sess.run(accuracy, feed_dict={x: test_x, y: test_y})
            print("Epoch: ", epoch, " Loss: ", np.mean(epoch_costs), " Accuracy: ", accuracy_in_train_set, ' ',
                  accuracy_in_test_set)

        confidence = sess.run(accuracy, feed_dict={x: train_x, y: train_y})
        saver.save(sess, "./Model/model.ckpt")
    return confidence


def run(job_id, raw_data):
    confidence = train_neural_networks(raw_data)
    confidence = confidence.item()
    rest.send_ai_result(job_id, "TRAIN", confidence=confidence)
