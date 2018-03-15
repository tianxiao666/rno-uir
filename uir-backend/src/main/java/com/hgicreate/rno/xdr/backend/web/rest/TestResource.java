package com.hgicreate.rno.xdr.backend.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import com.hgicreate.rno.xdr.backend.domain.*;
import com.hgicreate.rno.xdr.backend.repository.InterfereTestRepository;
import com.hgicreate.rno.xdr.backend.repository.RecordRepository;
import com.hgicreate.rno.xdr.backend.repository.TaskRepository;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TestResource {
    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    private final TaskRepository taskRepository;

    private final RecordRepository recordRepository;

    private final InterfereTestRepository interfereTestRepository;

    private final TrainService trainService;

    @Value("${rno.brain.url}")
    private String brainUrl;

    public TestResource(TaskRepository taskRepository,
                        RecordRepository recordRepository,
                        InterfereTestRepository interfereTestRepository,
                        TrainService trainService){
        this.taskRepository = taskRepository;
        this.recordRepository = recordRepository;
        this.interfereTestRepository = interfereTestRepository;
        this.trainService = trainService;
    }

    @GetMapping("/searchTestSample")
    public ResponseEntity<List<String>> getTestSampleType(){
        List<Record> recordList= recordRepository.findByFileTypeAndState("干扰数据测试样本","入库成功");
        List<String> typeList = new ArrayList<>();
        for(Record record: recordList){
            typeList.add(record.getFileName());
        }
        return new ResponseEntity<>(typeList, HttpStatus.OK);
    }

    @GetMapping("/searchTest")
    @Timed
    public ResponseEntity<List<Task>> searchTest(@RequestParam Long beginTime,
                                                 @RequestParam Long endTime,
                                                 @RequestParam String name,
                                                 @RequestParam String status,
                                                 @RequestParam String city,
                                                 @ApiParam Pageable pageable){
        log.debug("进入查询测试记录方法beginTime={},endTime={},name={},status={},city={}",beginTime,endTime, name, status, city);
        Date dateFrom = new Date(beginTime);
        Date dateTo = new Date(endTime);
        Page<Task> taskPage = trainService.findByNameAndStatusAndTime(name,status,
            dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
            dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),"测试",pageable);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(taskPage,"/api/searchTest");
        return new ResponseEntity<>(taskPage.getContent(),httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/addTestTask")
    @ResponseBody
    public String addTestTask(@RequestParam String sampleName,
                              @RequestParam String sampleExplain,
                              @RequestParam String sampleResource) throws UnsupportedEncodingException {
        Task task = new Task();
        task.setName(sampleName);
        if(!sampleExplain.trim().equals("") && !sampleExplain.equals("undefined")){
            task.setDescription(sampleExplain);
        }
        if(sampleExplain.length() > 100){
            return "测试任务描述请勿超过100字符";
        }
        task.setStatus("进行中");
        task.setFileName(sampleResource);
        List<Record> recordList = recordRepository.findByFileNameAndStateAndFileType(sampleResource,
            "入库成功","干扰数据测试样本");
        if(recordList.size() > 0){
            task.setFileId(recordList.get(0).getId());
        }else{
            return "样本不存在";
        }
        task.setStartTime(LocalDateTime.now());
        task.setType("测试");
        taskRepository.save(task);
        List<InterfereTest> interferes=interfereTestRepository.findByFileId(task.getFileId().toString());
        log.debug("测试样本数据 testList={}",interferes);
        if(interferes.size() == 0){
            task.setStatus("解析错误");
            taskRepository.save(task);
            return "该测试数据不存在，请选择其他数据";
        }
        String[][] prbArr = new String [interferes.size()][101];
        for(int i= 0; i < prbArr.length ; i++){
            prbArr[i][0] = interferes.get(i).getId().toString();
            prbArr[i][1] = interferes.get(i).getPrb0();
            prbArr[i][2] = interferes.get(i).getPrb1();
            prbArr[i][3] = interferes.get(i).getPrb2();
            prbArr[i][4] = interferes.get(i).getPrb3();
            prbArr[i][5] = interferes.get(i).getPrb4();
            prbArr[i][6] = interferes.get(i).getPrb5();
            prbArr[i][7] = interferes.get(i).getPrb6();
            prbArr[i][8] = interferes.get(i).getPrb7();
            prbArr[i][9] = interferes.get(i).getPrb8();
            prbArr[i][10] = interferes.get(i).getPrb9();
            prbArr[i][11] = interferes.get(i).getPrb10();
            prbArr[i][12] = interferes.get(i).getPrb11();
            prbArr[i][13] = interferes.get(i).getPrb12();
            prbArr[i][14] = interferes.get(i).getPrb13();
            prbArr[i][15] = interferes.get(i).getPrb14();
            prbArr[i][16] = interferes.get(i).getPrb15();
            prbArr[i][17] = interferes.get(i).getPrb16();
            prbArr[i][18] = interferes.get(i).getPrb17();
            prbArr[i][19] = interferes.get(i).getPrb18();
            prbArr[i][20] = interferes.get(i).getPrb19();
            prbArr[i][21] = interferes.get(i).getPrb20();
            prbArr[i][22] = interferes.get(i).getPrb21();
            prbArr[i][23] = interferes.get(i).getPrb22();
            prbArr[i][24] = interferes.get(i).getPrb23();
            prbArr[i][25] = interferes.get(i).getPrb24();
            prbArr[i][26] = interferes.get(i).getPrb25();
            prbArr[i][27] = interferes.get(i).getPrb26();
            prbArr[i][28] = interferes.get(i).getPrb27();
            prbArr[i][29] = interferes.get(i).getPrb28();
            prbArr[i][30] = interferes.get(i).getPrb29();
            prbArr[i][31] = interferes.get(i).getPrb30();
            prbArr[i][32] = interferes.get(i).getPrb31();
            prbArr[i][33] = interferes.get(i).getPrb32();
            prbArr[i][34] = interferes.get(i).getPrb33();
            prbArr[i][35] = interferes.get(i).getPrb34();
            prbArr[i][36] = interferes.get(i).getPrb35();
            prbArr[i][37] = interferes.get(i).getPrb36();
            prbArr[i][38] = interferes.get(i).getPrb37();
            prbArr[i][39] = interferes.get(i).getPrb38();
            prbArr[i][40] = interferes.get(i).getPrb39();
            prbArr[i][41] = interferes.get(i).getPrb40();
            prbArr[i][42] = interferes.get(i).getPrb41();
            prbArr[i][43] = interferes.get(i).getPrb42();
            prbArr[i][44] = interferes.get(i).getPrb43();
            prbArr[i][45] = interferes.get(i).getPrb44();
            prbArr[i][46] = interferes.get(i).getPrb45();
            prbArr[i][47] = interferes.get(i).getPrb46();
            prbArr[i][48] = interferes.get(i).getPrb47();
            prbArr[i][49] = interferes.get(i).getPrb48();
            prbArr[i][50] = interferes.get(i).getPrb49();
            prbArr[i][51] = interferes.get(i).getPrb50();
            prbArr[i][52] = interferes.get(i).getPrb51();
            prbArr[i][53] = interferes.get(i).getPrb52();
            prbArr[i][54] = interferes.get(i).getPrb53();
            prbArr[i][55] = interferes.get(i).getPrb54();
            prbArr[i][56] = interferes.get(i).getPrb55();
            prbArr[i][57] = interferes.get(i).getPrb56();
            prbArr[i][58] = interferes.get(i).getPrb57();
            prbArr[i][59] = interferes.get(i).getPrb58();
            prbArr[i][60] = interferes.get(i).getPrb59();
            prbArr[i][61] = interferes.get(i).getPrb60();
            prbArr[i][62] = interferes.get(i).getPrb61();
            prbArr[i][63] = interferes.get(i).getPrb62();
            prbArr[i][64] = interferes.get(i).getPrb63();
            prbArr[i][65] = interferes.get(i).getPrb64();
            prbArr[i][66] = interferes.get(i).getPrb65();
            prbArr[i][67] = interferes.get(i).getPrb66();
            prbArr[i][68] = interferes.get(i).getPrb67();
            prbArr[i][69] = interferes.get(i).getPrb68();
            prbArr[i][70] = interferes.get(i).getPrb69();
            prbArr[i][71] = interferes.get(i).getPrb70();
            prbArr[i][72] = interferes.get(i).getPrb71();
            prbArr[i][73] = interferes.get(i).getPrb72();
            prbArr[i][74] = interferes.get(i).getPrb73();
            prbArr[i][75] = interferes.get(i).getPrb74();
            prbArr[i][76] = interferes.get(i).getPrb75();
            prbArr[i][77] = interferes.get(i).getPrb76();
            prbArr[i][78] = interferes.get(i).getPrb77();
            prbArr[i][79] = interferes.get(i).getPrb78();
            prbArr[i][80] = interferes.get(i).getPrb79();
            prbArr[i][81] = interferes.get(i).getPrb80();
            prbArr[i][82] = interferes.get(i).getPrb81();
            prbArr[i][83] = interferes.get(i).getPrb82();
            prbArr[i][84] = interferes.get(i).getPrb83();
            prbArr[i][85] = interferes.get(i).getPrb84();
            prbArr[i][86] = interferes.get(i).getPrb85();
            prbArr[i][87] = interferes.get(i).getPrb86();
            prbArr[i][88] = interferes.get(i).getPrb87();
            prbArr[i][89] = interferes.get(i).getPrb88();
            prbArr[i][90] = interferes.get(i).getPrb89();
            prbArr[i][91] = interferes.get(i).getPrb90();
            prbArr[i][92] = interferes.get(i).getPrb91();
            prbArr[i][93] = interferes.get(i).getPrb92();
            prbArr[i][94] = interferes.get(i).getPrb93();
            prbArr[i][95] = interferes.get(i).getPrb94();
            prbArr[i][96] = interferes.get(i).getPrb95();
            prbArr[i][97] = interferes.get(i).getPrb96();
            prbArr[i][98] = interferes.get(i).getPrb97();
            prbArr[i][99] = interferes.get(i).getPrb98();
            prbArr[i][100] = interferes.get(i).getPrb99();
        }

        log.debug("测试任务待传参数为：data = {}",Arrays.deepToString(prbArr));
       // return "添加测试任务成功";
        String urlNameString =brainUrl+"/solve";
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
                return "添加测试任务成功,测试正在进行";
            }else{
                task.setStatus("解析错误");
                taskRepository.save(task);
                return "人工智能解析错误";
            }
        } catch (IOException e) {
            e.printStackTrace();
            task.setStatus("解析错误");
            taskRepository.save(task);
            return "添加测试任务失败,网络连接异常";
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

    @GetMapping("/downloadTestResult")
    @Timed
    public void downloadTestResult(@RequestParam Long taskId, HttpServletResponse response)
        throws IllegalAccessException, NoSuchFieldException, JSONException{
        Long fileId =taskRepository.findOne(taskId).getFileId();
        List<InterfereTest> interfereTests =interfereTestRepository.findByFileId(fileId.toString());
        String fileName = "测试任务提取结果.xlsx";
        try {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  // 为了解决中文名称乱码问题
        }

        response.setContentType("application/x.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        Map<String, List> res = new LinkedHashMap<>();
        res.put("测试结果",interfereTests);
        createExcel(response,res, taskRepository.findOne(taskId).getSuccessRate());
    }

    @SuppressWarnings("unused")
    private  boolean createExcel(HttpServletResponse response, Map<String, List> data,String successRate) throws JSONException, NoSuchFieldException, IllegalAccessException {
        log.debug("------------------------------------"+data);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row;
        Cell cell;
        int n = 0;

        for (String s : data.keySet()) {
            int titleIndex = 0, rowIndex = 0;
            if (n > 0) {
                sheet = workbook.createSheet();
            }
            workbook.setSheetName(n, s);
            n++;

            for (Object obj:  data.get(s)) {
                int valueIndex = 0;
                if (rowIndex == 0) {
                    row = sheet.createRow(0);
                    for (Field field : obj.getClass().getDeclaredFields()) {
                        field.setAccessible(true);

                        if(!field.getName().equals("id")&&!field.getName().equals("fileId")
                            &&!field.getName().equals("areaId") && !field.getName().equals("serialVersionUID")){
                            cell = row.createCell(titleIndex++);
                            log.debug("导出列名={}",field.getName());
                            cell.setCellValue(transferEngToChinese(field.getName()));
                        }
                    }
                }
                row = sheet.createRow(++rowIndex);
                for (Field field : obj.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if(!field.getName().equals("id")&&!field.getName().equals("fileId")
                        &&!field.getName().equals("areaId")&& !field.getName().equals("serialVersionUID")){
                        cell = row.createCell(valueIndex++);
                        if(field.get(obj) != null){
                            cell.setCellValue(field.get(obj)+"");
                        }else{
                            cell.setCellValue("");
                        }
                    }
                }
            }
            if(successRate!=null) {
                row = sheet.createRow(rowIndex + 1);
                DecimalFormat df = new DecimalFormat("#.00");
                row.createCell(0).setCellValue("测试准确率为"
                    + df.format(Double.parseDouble(successRate)*100) +"%");
            }
        }
        try {
            workbook.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    private String transferEngToChinese(String eng){
        Map<String,String> map= new HashMap<>();
        map.put("time","时间");
        map.put("factory","厂家名称");
        map.put("enodeb","所属E-NODEB");
        map.put("cellName","小区中文名");
        map.put("cellIdendifier","小区标识");
        map.put("coverType","覆盖类型");
        map.put("longitude","经度");
        map.put("latitude","纬度");
        map.put("workBand","工作频段");
        map.put("carrierFrequency","中心频段的信道");
        map.put("prb0","小区RB上行平均干扰电平PRB0");        map.put("prb1","小区RB上行平均干扰电平PRB1");
        map.put("prb2","小区RB上行平均干扰电平PRB2");        map.put("prb3","小区RB上行平均干扰电平PRB3");
        map.put("prb4","小区RB上行平均干扰电平PRB4");        map.put("prb5","小区RB上行平均干扰电平PRB5");
        map.put("prb6","小区RB上行平均干扰电平PRB6");        map.put("prb7","小区RB上行平均干扰电平PRB7");
        map.put("prb8","小区RB上行平均干扰电平PRB8");        map.put("prb9","小区RB上行平均干扰电平PRB9");
        map.put("prb10","小区RB上行平均干扰电平PRB10");      map.put("prb11","小区RB上行平均干扰电平PRB11");
        map.put("prb12","小区RB上行平均干扰电平PRB12");        map.put("prb13","小区RB上行平均干扰电平PRB13");
        map.put("prb14","小区RB上行平均干扰电平PRB14");        map.put("prb15","小区RB上行平均干扰电平PRB15");
        map.put("prb16","小区RB上行平均干扰电平PRB16");        map.put("prb17","小区RB上行平均干扰电平PRB17");
        map.put("prb18","小区RB上行平均干扰电平PRB18");        map.put("prb19","小区RB上行平均干扰电平PRB19");
        map.put("prb20","小区RB上行平均干扰电平PRB20");        map.put("prb21","小区RB上行平均干扰电平PRB21");
        map.put("prb22","小区RB上行平均干扰电平PRB22");        map.put("prb23","小区RB上行平均干扰电平PRB23");
        map.put("prb24","小区RB上行平均干扰电平PRB24");        map.put("prb25","小区RB上行平均干扰电平PRB25");
        map.put("prb26","小区RB上行平均干扰电平PRB26");        map.put("prb27","小区RB上行平均干扰电平PRB27");
        map.put("prb28","小区RB上行平均干扰电平PRB28");        map.put("prb29","小区RB上行平均干扰电平PRB29");
        map.put("prb30","小区RB上行平均干扰电平PRB30");        map.put("prb31","小区RB上行平均干扰电平PRB31");
        map.put("prb32","小区RB上行平均干扰电平PRB32");        map.put("prb33","小区RB上行平均干扰电平PRB33");
        map.put("prb34","小区RB上行平均干扰电平PRB34");        map.put("prb35","小区RB上行平均干扰电平PRB35");
        map.put("prb36","小区RB上行平均干扰电平PRB36");        map.put("prb37","小区RB上行平均干扰电平PRB37");
        map.put("prb38","小区RB上行平均干扰电平PRB38");        map.put("prb39","小区RB上行平均干扰电平PRB39");
        map.put("prb40","小区RB上行平均干扰电平PRB40");        map.put("prb41","小区RB上行平均干扰电平PRB41");
        map.put("prb42","小区RB上行平均干扰电平PRB42");        map.put("prb43","小区RB上行平均干扰电平PRB43");
        map.put("prb44","小区RB上行平均干扰电平PRB44");        map.put("prb45","小区RB上行平均干扰电平PRB45");
        map.put("prb46","小区RB上行平均干扰电平PRB46");        map.put("prb47","小区RB上行平均干扰电平PRB47");
        map.put("prb48","小区RB上行平均干扰电平PRB48");        map.put("prb49","小区RB上行平均干扰电平PRB49");
        map.put("prb50","小区RB上行平均干扰电平PRB50");        map.put("prb51","小区RB上行平均干扰电平PRB51");
        map.put("prb52","小区RB上行平均干扰电平PRB52");        map.put("prb53","小区RB上行平均干扰电平PRB53");
        map.put("prb54","小区RB上行平均干扰电平PRB54");        map.put("prb55","小区RB上行平均干扰电平PRB55");
        map.put("prb56","小区RB上行平均干扰电平PRB56");        map.put("prb57","小区RB上行平均干扰电平PRB57");
        map.put("prb58","小区RB上行平均干扰电平PRB58");        map.put("prb59","小区RB上行平均干扰电平PRB59");
        map.put("prb60","小区RB上行平均干扰电平PRB60");        map.put("prb61","小区RB上行平均干扰电平PRB61");
        map.put("prb62","小区RB上行平均干扰电平PRB62");        map.put("prb63","小区RB上行平均干扰电平PRB63");
        map.put("prb64","小区RB上行平均干扰电平PRB64");        map.put("prb65","小区RB上行平均干扰电平PRB65");
        map.put("prb66","小区RB上行平均干扰电平PRB66");        map.put("prb67","小区RB上行平均干扰电平PRB67");
        map.put("prb68","小区RB上行平均干扰电平PRB68");        map.put("prb69","小区RB上行平均干扰电平PRB69");
        map.put("prb70","小区RB上行平均干扰电平PRB70");        map.put("prb71","小区RB上行平均干扰电平PRB71");
        map.put("prb72","小区RB上行平均干扰电平PRB72");        map.put("prb73","小区RB上行平均干扰电平PRB73");
        map.put("prb74","小区RB上行平均干扰电平PRB74");        map.put("prb75","小区RB上行平均干扰电平PRB75");
        map.put("prb76","小区RB上行平均干扰电平PRB76");        map.put("prb77","小区RB上行平均干扰电平PRB77");
        map.put("prb78","小区RB上行平均干扰电平PRB78");        map.put("prb79","小区RB上行平均干扰电平PRB79");
        map.put("prb80","小区RB上行平均干扰电平PRB80");        map.put("prb81","小区RB上行平均干扰电平PRB81");
        map.put("prb82","小区RB上行平均干扰电平PRB82");        map.put("prb83","小区RB上行平均干扰电平PRB83");
        map.put("prb84","小区RB上行平均干扰电平PRB84");        map.put("prb85","小区RB上行平均干扰电平PRB85");
        map.put("prb86","小区RB上行平均干扰电平PRB86");        map.put("prb87","小区RB上行平均干扰电平PRB87");
        map.put("prb88","小区RB上行平均干扰电平PRB88");        map.put("prb89","小区RB上行平均干扰电平PRB89");
        map.put("prb90","小区RB上行平均干扰电平PRB90");        map.put("prb91","小区RB上行平均干扰电平PRB91");
        map.put("prb92","小区RB上行平均干扰电平PRB92");        map.put("prb93","小区RB上行平均干扰电平PRB93");
        map.put("prb94","小区RB上行平均干扰电平PRB94");        map.put("prb95","小区RB上行平均干扰电平PRB95");
        map.put("prb96","小区RB上行平均干扰电平PRB96");        map.put("prb97","小区RB上行平均干扰电平PRB97");
        map.put("prb98","小区RB上行平均干扰电平PRB98");        map.put("prb99","小区RB上行平均干扰电平PRB99");
        map.put("averageInterfere","平均干扰");
        map.put("interfereGrade","干扰分类");
        map.put("classification","智能分类");
        return map.get(eng);
    }
}
