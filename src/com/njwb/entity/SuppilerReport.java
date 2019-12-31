package com.njwb.entity;

public class SuppilerReport {
    private int s_id;
    private String s_name;
    private Double allPrice;
    private int kind;
    private int nums;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Double allPrice) {
        this.allPrice = allPrice;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
