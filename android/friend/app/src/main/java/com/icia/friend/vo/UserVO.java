package com.icia.friend.vo;

import java.util.Date;

public class UserVO {
    private String u_code;
    private String u_name;
    private String u_address;
    private String u_id;
    private String u_pass;
    private boolean u_adult;
    private Date u_date;
    private int manner;
    private int r_count;
    private int u_type;
    private String u_photo;
    private boolean u_status;

    public String getU_code() {
        return u_code;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_address() {
        return u_address;
    }

    public String getU_id() {
        return u_id;
    }

    public String getU_pass() {
        return u_pass;
    }

    public boolean isU_adult() {
        return u_adult;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }

    public void setU_adult(boolean u_adult) {
        this.u_adult = u_adult;
    }

    public Date getU_date() {
        return u_date;
    }

    public void setU_date(Date u_date) {
        this.u_date = u_date;
    }

    public int getManner() {
        return manner;
    }

    public void setManner(int manner) {
        this.manner = manner;
    }

    public int getR_count() {
        return r_count;
    }

    public void setR_count(int r_count) {
        this.r_count = r_count;
    }

    public int getU_type() {
        return u_type;
    }

    public void setU_type(int u_type) {
        this.u_type = u_type;
    }

    public String getU_photo() {
        return u_photo;
    }

    public void setU_photo(String u_photo) {
        this.u_photo = u_photo;
    }

    public boolean isU_status() {
        return u_status;
    }

    public void setU_status(boolean u_status) {
        this.u_status = u_status;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "u_code='" + u_code + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_address='" + u_address + '\'' +
                ", u_id='" + u_id + '\'' +
                ", u_pass='" + u_pass + '\'' +
                ", u_adult=" + u_adult +
                ", u_date=" + u_date +
                ", manner=" + manner +
                ", r_count=" + r_count +
                ", u_type=" + u_type +
                ", u_photo='" + u_photo + '\'' +
                ", u_status=" + u_status +
                '}';
    }
}
