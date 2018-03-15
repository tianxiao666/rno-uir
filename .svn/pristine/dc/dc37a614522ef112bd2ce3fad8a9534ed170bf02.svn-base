package com.hgicreate.rno.xdr.backend.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import com.hgicreate.rno.xdr.backend.domain.*;
import com.hgicreate.rno.xdr.backend.repository.*;
import com.hgicreate.rno.xdr.backend.service.TrainService;
import com.hgicreate.rno.xdr.backend.web.rest.util.HttpConnectUtil;
import com.hgicreate.rno.xdr.backend.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TrainResource {
    private final Logger log = LoggerFactory.getLogger(TrainResource.class);

    private final TrainService trainService;

    private final TaskRepository taskRepository;


    private final InterfereTrainRepository interfereTrainRepository;

    private final RecordRepository recordRepository;

    @Value("${rno.brain.url}")
    private String brainUrl;


    public TrainResource(TrainService trainService,
                         TaskRepository taskRepository,
                         RecordRepository recordRepository,
                         InterfereTrainRepository interfereTrainRepository){
        this.trainService = trainService;
        this.taskRepository = taskRepository;
        this.interfereTrainRepository = interfereTrainRepository;
        this.recordRepository = recordRepository;
    }

    @GetMapping("/searchTrainSample")
    public ResponseEntity<List<String>> getSampleType(){
        List<Record> records=recordRepository.findByFileTypeAndState("干扰数据训练样本","入库成功");
        List<String> typeList = new ArrayList<>();
        for(Record record: records){
            typeList.add(record.getFileName());
        }
        return new ResponseEntity<>(typeList,HttpStatus.OK);
    }

    @GetMapping("/searchTrain")
    @Timed
    public ResponseEntity<List<Task>> searchTrain(@RequestParam Long beginTime,
                                                  @RequestParam Long endTime,
                                                  @RequestParam String name,
                                                  @RequestParam String status,
                                                  @RequestParam String city,
                                                  @ApiParam Pageable pageable){
        log.debug("进入查询训练记录方法beginTime={},endTime={},name={},status={},city={}",beginTime,endTime, name, status, city);
        Date dateFrom = new Date(beginTime);
        Date dateTo = new Date(endTime);
        Page<Task> taskPage = trainService.findByNameAndStatusAndTime(name,status,
            dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
            dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),"训练",pageable);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(taskPage,"/api/searchTrain");
        return new ResponseEntity<>(taskPage.getContent(),httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/addTrainTask")
    @ResponseBody
    public String addTrainTask(@RequestParam String sampleName,
                               @RequestParam String sampleExplain,
                               @RequestParam String sampleResource) throws UnsupportedEncodingException {
        Task task = new Task();
        task.setName(sampleName);
        if(!sampleExplain.trim().equals("") && !sampleExplain.equals("undefined")){
            task.setDescription(sampleExplain);
        }
        if(sampleExplain.length() > 100){
            return "训练任务描述请勿超过100字符";
        }
        task.setFileName(sampleResource);
        List<Record> recordList=recordRepository.findByFileNameAndStateAndFileType(sampleResource,
            "入库成功","干扰数据训练样本");
        if(recordList.size() > 0){
          task.setFileId(recordList.get(0).getId());
        }else{
            task.setStatus("解析错误");
            taskRepository.save(task);
            return "该训练数据不存在，请选择其他数据";
        }
        task.setStartTime(LocalDateTime.now());
        task.setStatus("进行中");
        task.setType("训练");
        taskRepository.save(task);
        List<InterfereTrain> interferes=interfereTrainRepository.findByFileId(task.getFileId().toString());
        String[][] prbArr = new String [interferes.size()][101];
        for(int i= 0; i < prbArr.length ; i++){
            prbArr[i][0] = interferes.get(i).getPrb0();
            prbArr[i][1] = interferes.get(i).getPrb1();
            prbArr[i][2] = interferes.get(i).getPrb2();
            prbArr[i][3] = interferes.get(i).getPrb3();
            prbArr[i][4] = interferes.get(i).getPrb4();
            prbArr[i][5] = interferes.get(i).getPrb5();
            prbArr[i][6] = interferes.get(i).getPrb6();
            prbArr[i][7] = interferes.get(i).getPrb7();
            prbArr[i][8] = interferes.get(i).getPrb8();
            prbArr[i][9] = interferes.get(i).getPrb9();
            prbArr[i][10] = interferes.get(i).getPrb10();
            prbArr[i][11] = interferes.get(i).getPrb11();
            prbArr[i][12] = interferes.get(i).getPrb12();
            prbArr[i][13] = interferes.get(i).getPrb13();
            prbArr[i][14] = interferes.get(i).getPrb14();
            prbArr[i][15] = interferes.get(i).getPrb15();
            prbArr[i][16] = interferes.get(i).getPrb16();
            prbArr[i][17] = interferes.get(i).getPrb17();
            prbArr[i][18] = interferes.get(i).getPrb18();
            prbArr[i][19] = interferes.get(i).getPrb19();
            prbArr[i][20] = interferes.get(i).getPrb20();
            prbArr[i][21] = interferes.get(i).getPrb21();
            prbArr[i][22] = interferes.get(i).getPrb22();
            prbArr[i][23] = interferes.get(i).getPrb23();
            prbArr[i][24] = interferes.get(i).getPrb24();
            prbArr[i][25] = interferes.get(i).getPrb25();
            prbArr[i][26] = interferes.get(i).getPrb26();
            prbArr[i][27] = interferes.get(i).getPrb27();
            prbArr[i][28] = interferes.get(i).getPrb28();
            prbArr[i][29] = interferes.get(i).getPrb29();
            prbArr[i][30] = interferes.get(i).getPrb30();
            prbArr[i][31] = interferes.get(i).getPrb31();
            prbArr[i][32] = interferes.get(i).getPrb32();
            prbArr[i][33] = interferes.get(i).getPrb33();
            prbArr[i][34] = interferes.get(i).getPrb34();
            prbArr[i][35] = interferes.get(i).getPrb35();
            prbArr[i][36] = interferes.get(i).getPrb36();
            prbArr[i][37] = interferes.get(i).getPrb37();
            prbArr[i][38] = interferes.get(i).getPrb38();
            prbArr[i][39] = interferes.get(i).getPrb39();
            prbArr[i][40] = interferes.get(i).getPrb40();
            prbArr[i][41] = interferes.get(i).getPrb41();
            prbArr[i][42] = interferes.get(i).getPrb42();
            prbArr[i][43] = interferes.get(i).getPrb43();
            prbArr[i][44] = interferes.get(i).getPrb44();
            prbArr[i][45] = interferes.get(i).getPrb45();
            prbArr[i][46] = interferes.get(i).getPrb46();
            prbArr[i][47] = interferes.get(i).getPrb47();
            prbArr[i][48] = interferes.get(i).getPrb48();
            prbArr[i][49] = interferes.get(i).getPrb49();
            prbArr[i][50] = interferes.get(i).getPrb50();
            prbArr[i][51] = interferes.get(i).getPrb51();
            prbArr[i][52] = interferes.get(i).getPrb52();
            prbArr[i][53] = interferes.get(i).getPrb53();
            prbArr[i][54] = interferes.get(i).getPrb54();
            prbArr[i][55] = interferes.get(i).getPrb55();
            prbArr[i][56] = interferes.get(i).getPrb56();
            prbArr[i][57] = interferes.get(i).getPrb57();
            prbArr[i][58] = interferes.get(i).getPrb58();
            prbArr[i][59] = interferes.get(i).getPrb59();
            prbArr[i][60] = interferes.get(i).getPrb60();
            prbArr[i][61] = interferes.get(i).getPrb61();
            prbArr[i][62] = interferes.get(i).getPrb62();
            prbArr[i][63] = interferes.get(i).getPrb63();
            prbArr[i][64] = interferes.get(i).getPrb64();
            prbArr[i][65] = interferes.get(i).getPrb65();
            prbArr[i][66] = interferes.get(i).getPrb66();
            prbArr[i][67] = interferes.get(i).getPrb67();
            prbArr[i][68] = interferes.get(i).getPrb68();
            prbArr[i][69] = interferes.get(i).getPrb69();
            prbArr[i][70] = interferes.get(i).getPrb70();
            prbArr[i][71] = interferes.get(i).getPrb71();
            prbArr[i][72] = interferes.get(i).getPrb72();
            prbArr[i][73] = interferes.get(i).getPrb73();
            prbArr[i][74] = interferes.get(i).getPrb74();
            prbArr[i][75] = interferes.get(i).getPrb75();
            prbArr[i][76] = interferes.get(i).getPrb76();
            prbArr[i][77] = interferes.get(i).getPrb77();
            prbArr[i][78] = interferes.get(i).getPrb78();
            prbArr[i][79] = interferes.get(i).getPrb79();
            prbArr[i][80] = interferes.get(i).getPrb80();
            prbArr[i][81] = interferes.get(i).getPrb81();
            prbArr[i][82] = interferes.get(i).getPrb82();
            prbArr[i][83] = interferes.get(i).getPrb83();
            prbArr[i][84] = interferes.get(i).getPrb84();
            prbArr[i][85] = interferes.get(i).getPrb85();
            prbArr[i][86] = interferes.get(i).getPrb86();
            prbArr[i][87] = interferes.get(i).getPrb87();
            prbArr[i][88] = interferes.get(i).getPrb88();
            prbArr[i][89] = interferes.get(i).getPrb89();
            prbArr[i][90] = interferes.get(i).getPrb90();
            prbArr[i][91] = interferes.get(i).getPrb91();
            prbArr[i][92] = interferes.get(i).getPrb92();
            prbArr[i][93] = interferes.get(i).getPrb93();
            prbArr[i][94] = interferes.get(i).getPrb94();
            prbArr[i][95] = interferes.get(i).getPrb95();
            prbArr[i][96] = interferes.get(i).getPrb96();
            prbArr[i][97] = interferes.get(i).getPrb97();
            prbArr[i][98] = interferes.get(i).getPrb98();
            prbArr[i][99] = interferes.get(i).getPrb99();
            switch (interferes.get(i).getInterfereGrade()){
                case "无干扰":
                    prbArr[i][100] = "0";
                    break;
                case "弱干扰":
                    prbArr[i][100] = "1";
                    break;
                case "一般干扰":
                    prbArr[i][100] = "2";
                    break;
                case "强干扰":
                    prbArr[i][100] = "3";
                    break;
                case "超强干扰":
                    prbArr[i][100] = "4";
                    break;
                case "特强干扰":
                    prbArr[i][100] = "5";
            }
        }
        log.debug("训练任务待传参数为：data = {}",Arrays.deepToString(prbArr));

        String urlNameString =brainUrl+"/train";
        String result="";
        CloseableHttpClient httpClient = HttpConnectUtil.getHttpClient();
        HttpPost httpPost =new HttpPost(urlNameString);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        CloseableHttpResponse response=null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",task.getId().toString());
            jsonObject.put("data",Arrays.deepToString(prbArr));
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json");
            se.setContentEncoding("utf-8");
            httpPost.setEntity(se);
            String respStr = "";
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                 respStr = EntityUtils.toString(httpEntity);
            }
            log.debug("msg={}",respStr);
            if(respStr.contains("1000")){
                return "添加训练任务成功,训练正在进行";
            }else{
                task.setStatus("解析错误");
                taskRepository.save(task);
                return "人工智能解析错误";
            }

        } catch (IOException e) {
            e.printStackTrace();
            task.setStatus("解析错误");
            taskRepository.save(task);
            return "添加训练任务失败,网络连接异常";
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
