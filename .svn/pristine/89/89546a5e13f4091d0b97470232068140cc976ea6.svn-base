from flask import Flask, jsonify, request, abort
import train
import solve
import threading
import json
import requests

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/train', methods=['POST'])
def post_train():
    if not request.json or 'id' not in request.json or 'data' not in request.json:
        print(request.json)
        abort(400)

    job_id = request.json.get("id")
    raw_data = eval(request.json.get("data"))
    # raw_data = request.json.get("data")

    t1 = threading.Thread(target=train.run, args=(job_id, raw_data,))
    t1.setDaemon(True)
    t1.start()

    msg = {'msg': {
        'code': '1000',
        'str': '调用成功'}
    }
    return jsonify({'msg': msg})


@app.route('/solve', methods=['POST'])
def post_solve():
    if not request.json or 'id' not in request.json or 'data' not in request.json:
        print(request.json)
        abort(400)

    job_id = request.json.get("id")
    raw_data = eval(request.json.get("data"))
    # raw_data = request.json.get("data")

    t1 = threading.Thread(target=solve.run, args=(job_id, raw_data,))
    t1.setDaemon(True)
    t1.start()

    msg = {'msg': {
        'code': '1000',
        'str': '调用成功'}
    }
    return jsonify({'msg': msg})


def send_ai_result(job_id, job_type, confidence=0.0, data=None):
    url = 'http://192.168.6.40:28080/aiResult'
    headers = {'content-type': 'application/json'}
    body = {
        'param': {
            'id': job_id,
            'type': job_type,
            'confidence': confidence
        },
        'data': data
    }

    r = requests.post(url, headers=headers, data=json.dumps(body))
    print(r.json())


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8090)
