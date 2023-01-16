package com.icia.friend.vo;

import java.util.Date;

public class ReviewVO {
    private String s_code;
    private String u_code;
    private String r_content;
    private String r_photo;
    private double r_rating;
    private Date review_date;

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    public String getS_code() {
        return s_code;
    }

    public void setS_code(String s_code) {
        this.s_code = s_code;
    }

    public String getU_code() {
        return u_code;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public String getR_photo() {
        return r_photo;
    }

    public void setR_photo(String r_photo) {
        this.r_photo = r_photo;
    }

    public double getR_rating() {
        return r_rating;
    }

    public void setR_rating(double r_rating) {
        this.r_rating = r_rating;
    }

    @Override
    public String toString() {
        return "ReviewVO{" +
                "s_code='" + s_code + '\'' +
                ", u_code='" + u_code + '\'' +
                ", r_content='" + r_content + '\'' +
                ", r_photo='" + r_photo + '\'' +
                ", r_rating='" + r_rating + '\'' +
                ", review_date=" + review_date +
                '}';
    }
}
