package com.njwb.entity;

import com.njwb.dao.SuppilerBeanDao;
import com.njwb.factory.ObjectFactory;

import java.util.Date;
import java.sql.SQLException;

public class AccountBean {
    private int a_id;
    private String a_name;
    private int a_nums;
    private double a_amount;
    /**
     * 交易单位
     */
    private String a_unit;
    private int a_ispayed;
    private int s_id;
    private String s_name;
    /**
     *描述
     */
    private String a_Info;
    private Date a_Date;

    private SuppilerBeanDao suppilerBeanDao = (SuppilerBeanDao)ObjectFactory.objectMap.get("SuppilerBeanDao");
    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public int getA_nums() {
        return a_nums;
    }

    public void setA_nums(int a_nums) {
        this.a_nums = a_nums;
    }

    public double getA_amount() {
        return a_amount;
    }

    public void setA_amount(double a_amout) {
        this.a_amount = a_amout;
    }

    public String getA_unit() {
        return a_unit;
    }

    public void setA_unit(String a_unit) {
        this.a_unit = a_unit;
    }

    public int getA_ispayed() {
        return a_ispayed;
    }

    public void setA_ispayed(int a_ispayed) {
        this.a_ispayed = a_ispayed;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        SuppilerBean suppilerBean = new SuppilerBean();
        try {
            suppilerBean = suppilerBeanDao.selectSuppilerById(this.s_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppilerBean.getS_name();
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getA_Info() {
        return a_Info;
    }

    public void setA_Info(String a_Info) {
        this.a_Info = a_Info;
    }

    public Date getA_Date() {
        return a_Date;
    }

    public void setA_Date(Date a_Date) {
        this.a_Date = a_Date;
    }
}
