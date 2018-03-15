package com.hgicreate.rno.xdr.backend.web.rest.cond;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataSearchCond {
    private String area;
    private Long date1;
    private Long date2;
    private String device;
    private String interfere_grade;
    private String cell;
    private String cell_name;

    public DataSearchCond() {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getInterfere_grade() {
        return interfere_grade;
    }

    public void setInterfere_grade(String interfere_grade) {
        this.interfere_grade = interfere_grade;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCell_name() {
        return cell_name;
    }

    public void setCell_name(String cell_name) {
        this.cell_name = cell_name;
    }

    public String buildSql() {
        Date date1 = new Date(this.date1);
        Date date2 = new Date(this.date2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String where = "where area_id = '" + area + "'"  + " and file_time >= '" + sdf.format(date1) + "'" + " and file_time <= '"+ sdf.format(date2) + "'" ;
        if(!("全部".equals(this.device))){
           where += " and factory = '" + this.device + "'" ;
        }
        if(!("全部".equals(this.interfere_grade))) {
            where += " and classification = '" + interfere_grade + "'";
        }
        if(!this.cell.isEmpty()){
            if(this.cell.indexOf(",")!=-1){
                for(int i=0; i<this.cell.split(",").length; i++){
                    if(i==0){
                        where += " and (cell_identifier = '" + this.cell.split(",")[0] + "'";
                    }else {
                        where += " or cell_identifier = '" + this.cell.split(",")[i] + "'";
                        if(i==this.cell.split(",").length-1){
                            where += ")";
                        }
                    }
                }
            }else {
                where += " and cell_identifier = '" + this.cell + "'";
            }
            /*where += " and cell_identifier = '" + this.cell + "'";*/
        }
        if(!this.cell_name.isEmpty()){
            if(this.cell_name.indexOf(",")!=-1){
                for(int i=0; i<this.cell_name.split(",").length; i++){
                    if(i==0){
                        where += " and (cell_name = '" + this.cell_name.split(",")[0] + "'";
                    }else {
                        where += " or cell_name = '" + this.cell_name.split(",")[i] + "'";
                        if(i==this.cell_name.split(",").length-1){
                            where += ")";
                        }
                    }
                }
            }else {
                where += " and cell_name = '" + this.cell_name + "'";
            }
        }
        String sql = "select t.id,t.file_time,t.area_id,t.factory,t.classification,t.cell_name,t.cell_identifier,t.working_band,t.rb1,t.rb2,t.rb3,t.rb4,t.rb5,(SELECT min(prb_max) FROM   (VALUES (t.prb0),(t.prb1),(t.prb2),(t.prb3),(t.prb4),(t.prb5),(t.prb6),(t.prb7),(t.prb8),(t.prb9),(t.prb10),(t.prb11),(t.prb12),(t.prb13),(t.prb14),(t.prb15),(t.prb16),(t.prb17),(t.prb18),(t.prb19),(t.prb20),(t.prb21),(t.prb22),(t.prb23),(t.prb24),(t.prb25),(t.prb26),(t.prb27),(t.prb28),(t.prb29),(t.prb30),(t.prb31),(t.prb32),(t.prb33),(t.prb34),(t.prb35),(t.prb36),(t.prb37),(t.prb38),(t.prb39),(t.prb40),(t.prb41),(t.prb42),(t.prb43),(t.prb44),(t.prb45),(t.prb46),(t.prb47),(t.prb48),(t.prb49),(t.prb50),(t.prb51),(t.prb52),(t.prb53),(t.prb54),(t.prb55),(t.prb56),(t.prb57),(t.prb58),(t.prb59),(t.prb60),(t.prb61),(t.prb62),(t.prb63),(t.prb64),(t.prb65),(t.prb66),(t.prb67),(t.prb68),(t.prb69),(t.prb70),(t.prb71),(t.prb72),(t.prb73),(t.prb74),(t.prb75),(t.prb76),(t.prb77),(t.prb78),(t.prb79),(t.prb80),(t.prb81),(t.prb82),(t.prb83),(t.prb84),(t.prb85),(t.prb86),(t.prb87),(t.prb88),(t.prb89),(t.prb90),(t.prb91),(t.prb92),(t.prb93),(t.prb94),(t.prb95),(t.prb96),(t.prb97),(t.prb98),(t.prb99)   ) AS prb_final ( prb_max ))as prb_max,(cast(t.prb0 as float)+cast(t.prb1 as float)+cast(t.prb2 as float)+cast(t.prb3 as float)+cast(t.prb4 as float)+cast(t.prb5 as float)+cast(t.prb6 as float)+cast(t.prb7 as float)+cast(t.prb8 as float)+cast(t.prb9 as float)+cast(t.prb10 as float)+cast(t.prb11 as float)+cast(t.prb12 as float)+cast(t.prb13 as float)+cast(t.prb14 as float)+cast(t.prb15 as float)+cast(t.prb16 as float)+cast(t.prb17 as float)+cast(t.prb18 as float)+cast(t.prb19 as float)+cast(t.prb20 as float)+cast(t.prb21 as float)+cast(t.prb22 as float)+cast(t.prb23 as float)+cast(t.prb24 as float)+cast(t.prb25 as float)+cast(t.prb26 as float)+cast(t.prb27 as float)+cast(t.prb28 as float)+cast(t.prb29 as float)+cast(t.prb30 as float)+cast(t.prb31 as float)+cast(t.prb32 as float)+cast(t.prb33 as float)+cast(t.prb34 as float)+cast(t.prb35 as float)+cast(t.prb36 as float)+cast(t.prb37 as float)+cast(t.prb38 as float)+cast(t.prb39 as float)+cast(t.prb40 as float)+cast(t.prb41 as float)+cast(t.prb42 as float)+cast(t.prb43 as float)+cast(t.prb44 as float)+cast(t.prb45 as float)+cast(t.prb46 as float)+cast(t.prb47 as float)+cast(t.prb48 as float)+cast(t.prb49 as float)+cast(t.prb50 as float)+cast(t.prb51 as float)+cast(t.prb52 as float)+cast(t.prb53 as float)+cast(t.prb54 as float)+cast(t.prb55 as float)+cast(t.prb56 as float)+cast(t.prb57 as float)+cast(t.prb58 as float)+cast(t.prb59 as float)+cast(t.prb60 as float)+cast(t.prb61 as float)+cast(t.prb62 as float)+cast(t.prb63 as float)+cast(t.prb64 as float)+cast(t.prb65 as float)+cast(t.prb66 as float)+cast(t.prb67 as float)+cast(t.prb68 as float)+cast(t.prb69 as float)+cast(t.prb70 as float)+cast(t.prb71 as float)+cast(t.prb72 as float)+cast(t.prb73 as float)+cast(t.prb74 as float)+cast(t.prb75 as float)+cast(t.prb76 as float)+cast(t.prb77 as float)+cast(t.prb78 as float)+cast(t.prb79 as float)+cast(t.prb80 as float)+cast(t.prb81 as float)+cast(t.prb82 as float)+cast(t.prb83 as float)+cast(t.prb84 as float)+cast(t.prb85 as float)+cast(t.prb86 as float)+cast(t.prb87 as float)+cast(t.prb88 as float)+cast(t.prb89 as float)+cast(t.prb90 as float)+cast(t.prb91 as float)+cast(t.prb92 as float)+cast(t.prb93 as float)+cast(t.prb94 as float)+cast(t.prb95 as float)+cast(t.prb96 as float)+cast(t.prb97 as float)+cast(t.prb98 as float)+cast(t.prb99 as float))/100 as prb_avg,t.interfere_coefficient,t.prb0,t.prb1,t.prb2,t.prb3,t.prb4,t.prb5,t.prb6,t.prb7,t.prb8,t.prb9,t.prb10,t.prb11,t.prb12,t.prb13,t.prb14,t.prb15,t.prb16,t.prb17,t.prb18,t.prb19,t.prb20,t.prb21,t.prb22,t.prb23,t.prb24,t.prb25,t.prb26,t.prb27,t.prb28,t.prb29,t.prb30,t.prb31,t.prb32,t.prb33,t.prb34,t.prb35,t.prb36,t.prb37,t.prb38,t.prb39,t.prb40,t.prb41,t.prb42,t.prb43,t.prb44,t.prb45,t.prb46,t.prb47,t.prb48,t.prb49,t.prb50,t.prb51,t.prb52,t.prb53,t.prb54,t.prb55,t.prb56,t.prb57,t.prb58,t.prb59,t.prb60,t.prb61,t.prb62,t.prb63,t.prb64,t.prb65,t.prb66,t.prb67,t.prb68,t.prb69,t.prb70,t.prb71,t.prb72,t.prb73,t.prb74,t.prb75,t.prb76,t.prb77,t.prb78,t.prb79,t.prb80,t.prb81,t.prb82,t.prb83,t.prb84,t.prb85,t.prb86,t.prb87,t.prb88,t.prb89,t.prb90,t.prb91,t.prb92,t.prb93,t.prb94,t.prb95,t.prb96,t.prb97,t.prb98,t.prb99 from(select id,file_time,area_id,factory,classification,cell_name,cell_identifier,working_band,rb1,rb2,rb3,rb4,rb5,interfere_coefficient,prb0,prb1,prb2,prb3,prb4,prb5,prb6,prb7,prb8,prb9,prb10,prb11,prb12,prb13,prb14,prb15,prb16,prb17,prb18,prb19,prb20,prb21,prb22,prb23,prb24,prb25,prb26,prb27,prb28,prb29,prb30,prb31,prb32,prb33,prb34,prb35,prb36,prb37,prb38,prb39,prb40,prb41,prb42,prb43,prb44,prb45,prb46,prb47,prb48,prb49,prb50,prb51,prb52,prb53,prb54,prb55,prb56,prb57,prb58,prb59,prb60,prb61,prb62,prb63,prb64,prb65,prb66,prb67,prb68,prb69,prb70,prb71,prb72,prb73,prb74,prb75,prb76,prb77,prb78,prb79,prb80,prb81,prb82,prb83,prb84,prb85,prb86,prb87,prb88,prb89,prb90,prb91,prb92,prb93,prb94,prb95,prb96,prb97,prb98,prb99 from rno_uir_interfere_data "
                        + where + ") t" + " ORDER BY t.id asc";
        return sql;
    }
}
