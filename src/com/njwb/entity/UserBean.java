package com.njwb.entity;

public class UserBean {
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_gender;
    private int u_age;
    private String u_phone;
    private String u_address;
    /**
     * 权限
     */
    private String u_auth;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public int getU_age() {
        return u_age;
    }

    public void setU_age(int u_age) {
        this.u_age = u_age;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_auth() {
        return u_auth;
    }

    public void setU_auth(String u_auth) {
        this.u_auth = u_auth;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_gender='" + u_gender + '\'' +
                ", u_age=" + u_age +
                ", u_phone='" + u_phone + '\'' +
                ", u_address='" + u_address + '\'' +
                ", u_auth='" + u_auth + '\'' +
                '}';
    }
}
