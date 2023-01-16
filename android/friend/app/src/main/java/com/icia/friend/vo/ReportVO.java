package com.icia.friend.vo;

import java.util.Date;

public class ReportVO{
    private String r_code;
    private String suer;
    private String defender;
    private Date report_date;
    private String r_result;
    private String r_state;
    private String r_type;


    public String getR_code() {
        return r_code;
    }

    public void setR_code(String r_code) {
        this.r_code = r_code;
    }

    public String getSuer() {
        return suer;
    }

    public void setSuer(String suer) {
        this.suer = suer;
    }

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getR_result() {
        return r_result;
    }

    public void setR_result(String r_result) {
        this.r_result = r_result;
    }

    public String getR_state() {
        return r_state;
    }

    public void setR_state(String r_state) {
        this.r_state = r_state;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "r_code='" + r_code + '\'' +
                ", suer='" + suer + '\'' +
                ", defender='" + defender + '\'' +
                ", report_date=" + report_date +
                ", r_result='" + r_result + '\'' +
                ", r_state='" + r_state + '\'' +
                ", r_type='" + r_type + '\'' +
                '}';
    }
}
