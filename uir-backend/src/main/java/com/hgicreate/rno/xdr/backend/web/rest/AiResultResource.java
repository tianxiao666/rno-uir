package com.hgicreate.rno.xdr.backend.web.rest;

import com.hgicreate.rno.xdr.backend.domain.Interfere;
import com.hgicreate.rno.xdr.backend.domain.InterfereTest;
import com.hgicreate.rno.xdr.backend.domain.Msg;
import com.hgicreate.rno.xdr.backend.domain.Task;
import com.hgicreate.rno.xdr.backend.repository.InterfereRepository;
import com.hgicreate.rno.xdr.backend.repository.InterfereTestRepository;
import com.hgicreate.rno.xdr.backend.repository.InterfereTrainRepository;
import com.hgicreate.rno.xdr.backend.repository.TaskRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class AiResultResource {

    private final Logger log = LoggerFactory.getLogger(AiResultResource.class);

    private final TaskRepository taskRepository;

    private final InterfereTestRepository interfereTestRepository;

    private final InterfereRepository interfereRepository;

    private final InterfereTrainRepository interfereTrainRepository;

    public AiResultResource(TaskRepository taskRepository,
                            InterfereTestRepository interfereTestRepository,
                            InterfereRepository interfereRepository,
                            InterfereTrainRepository interfereTrainRepository){
        this.taskRepository = taskRepository;
        this.interfereRepository = interfereRepository;
        this.interfereTestRepository = interfereTestRepository;
        this.interfereTrainRepository = interfereTrainRepository;
    }

    // 接收人工智能返回结果
    @PostMapping("/aiResult")
    @ResponseBody
    @SuppressWarnings("unused")
    public ResponseEntity<Msg> getAiResult(@RequestBody String params){
        log.debug("params={}",params);
         Map<String,Object> map=(Map<String, Object>) com.alibaba.fastjson.JSONObject.parse(params);
        Map<String,Object> param = (Map<String,Object>) map.get("param");

        List<List<Integer>> list = new ArrayList<>();
        log.debug("求解结果={}", map.get("data"));
        if(map.get("data")!=null){
            list=(List<List<Integer>>)map.get("data");
        }
        log.debug("list={}",list);
        Msg msg = new Msg();
        msg.setStr("调用成功");
        msg.setCode("1000");
        if(param.get("type").equals("TRAIN")){
            Task idTask = taskRepository.findOne(Long.parseLong(param.get("id").toString()));
            idTask.setConfidence(Double.parseDouble(param.get("confidence").toString()));
            idTask.setEndTime(LocalDateTime.now());
            idTask.setStatus("正常完成");
            taskRepository.save(idTask);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else if(taskRepository.findOne(Long.parseLong(param.get("id").toString())).getType().equals("测试")){
            Task task=taskRepository.findOne(Long.parseLong(param.get("id").toString()));
            task.setEndTime(LocalDateTime.now());
            task.setStatus("正常完成");
            taskRepository.save(task);
            for (List<Integer> aList : list) {
                switch (aList.get(1)) {
                    case 0:
                        InterfereTest interfereTest = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest.setClassification("无干扰");
                        interfereTestRepository.save(interfereTest);
                        break;
                    case 1:
                        InterfereTest interfereTest1 = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest1.setClassification("弱干扰");
                        interfereTestRepository.save(interfereTest1);
                        break;
                    case 2:
                        InterfereTest interfereTest2 = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest2.setClassification("一般干扰");
                        interfereTestRepository.save(interfereTest2);
                        break;
                    case 3:
                        InterfereTest interfereTest3 = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest3.setClassification("强干扰");
                        interfereTestRepository.save(interfereTest3);
                        break;
                    case 4:
                        InterfereTest interfereTest4 = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest4.setClassification("超强干扰");
                        interfereTestRepository.save(interfereTest4);
                        break;
                    case 5:
                        InterfereTest interfereTest5 = interfereTestRepository.findById(aList.get(0).longValue());
                        interfereTest5.setClassification("特强干扰");
                        interfereTestRepository.save(interfereTest5);
                        break;
                }
            }
            long a =interfereTestRepository.countClassifyCationByFileId(task.getFileId().toString());
            double b =(double)interfereTestRepository.countTotalByFileId(task.getFileId().toString());
           if(b > 0) {
               Double rate = a / b;
               task.setSuccessRate(rate.toString());
               taskRepository.save(task);
           }
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            Task task=taskRepository.findOne(Long.parseLong(param.get("id").toString()));
            task.setEndTime(LocalDateTime.now());
            task.setStatus("正常完成");
            taskRepository.save(task);
            for (List<Integer> aList : list) {
                switch (aList.get(1)) {
                    case 0:
                        Interfere interfere = interfereRepository.findById(aList.get(0).longValue());
                        interfere.setClassification("无干扰");
                        interfereRepository.save(interfere);
                        break;
                    case 1:
                        Interfere interfere1 = interfereRepository.findById(aList.get(0).longValue());
                        interfere1.setClassification("弱干扰");
                        interfereRepository.save(interfere1);
                        break;
                    case 2:
                        Interfere interfere2 = interfereRepository.findById(aList.get(0).longValue());
                        interfere2.setClassification("一般干扰");
                        interfereRepository.save(interfere2);
                        break;
                    case 3:
                        Interfere interfere3 = interfereRepository.findById(aList.get(0).longValue());
                        interfere3.setClassification("强干扰");
                        interfereRepository.save(interfere3);
                        break;
                    case 4:
                        Interfere interfere4 = interfereRepository.findById(aList.get(0).longValue());
                        interfere4.setClassification("超强干扰");
                        interfereRepository.save(interfere4);
                        break;
                    case 5:
                        Interfere interfere5 = interfereRepository.findById(aList.get(0).longValue());
                        interfere5.setClassification("特强干扰");
                        interfereRepository.save(interfere5);
                        break;
                }
            }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    }
}
