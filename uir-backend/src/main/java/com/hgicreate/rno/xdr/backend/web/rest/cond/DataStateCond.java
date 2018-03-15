package com.hgicreate.rno.xdr.backend.web.rest.cond;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataStateCond {
    private String area;
    private Long date1;
    private Long date2;
    private String device;
    private String cell_name;

    public DataStateCond() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getDate1() {
        return date1;
    }

    public void setDate1(Long date1) {
        this.date1 = date1;
    }

    public Long getDate2() {
        return date2;
    }

    public void setDate2(Long date2) {
        this.date2 = date2;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCell_name() {
        return cell_name;
    }

    public void setCell_name(String cell_name) {
        this.cell_name = cell_name;
    }

    public String buildSql(String flag) {
        String sql = "";
        Date date1 = new Date(this.date1);
        Date date2 = new Date(this.date2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String where = "where area_id = '" + area + "'" + " and file_time >= '" + sdf.format(date1) + "'" + " and file_time <= '"+ sdf.format(date2) + "'" ;
        if(!("全部".equals(this.device))){
            where += " and factory = '" + this.device + "'" ;
        }
        if("analysis".equals(flag)){
            where += " and cell_name = '" + this.cell_name + "'";
            sql = "select ";
            for(int i=0;i<100;i++){
                sql += "avg(cast(prb" + i + " as float)) as avg" + i + ",";
            }
            for (int j=0;j<100;j++){
                sql += "max(cast(prb" + j + " as float)) as max" + j;
                if(j<99){
                    sql += ",";
                }
            }
            sql += " from rno_uir_interfere_data_train "+ where ;
        }else {
            if(!this.cell_name.isEmpty()){
                where += " and cell_name = '" + this.cell_name + "'";
            }
            sql = "select interfere_grade,count(distinct cell_name) from rno_uir_interfere_data_train " + where + " group by interfere_grade";
        }
        return sql;
    }

    public String countRecord(){
        Date date1 = new Date(this.date1);
        Date date2 = new Date(this.date2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String where = "where area_id = '" + area + "'" + " and file_time >= '" + sdf.format(date1) + "'" + " and file_time <= '"+ sdf.format(date2) + "'" + " and cell_name = '" + this.cell_name + "'";
        if(!("全部".equals(this.device))){
            where += " and factory = '" + this.device + "'" ;
        }
        String sql = "select count(1) from rno_uir_interfere_data_train " + where;
        return  sql;
    }
}
