package com.njwb.entity;

public class SuppilerBean {
    private int s_id;
    private String s_name;
    private String s_info;
    private String s_linkman;
    private String s_phone;
    private String s_address;
    /**
     * 传真
     */
    private String s_faxes;

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

    public String getS_info() {
        return s_info;
    }

    public void setS_info(String s_info) {
        this.s_info = s_info;
    }

    public String getS_linkman() {
        return s_linkman;
    }

    public void setS_linkman(String s_linkman) {
        this.s_linkman = s_linkman;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_faxes() {
        return s_faxes;
    }

    public void setS_faxes(String s_faxes) {
        this.s_faxes = s_faxes;
    }

    @Override
    public String toString() {
        return "SuppilerBean{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_info='" + s_info + '\'' +
                ", s_linkman='" + s_linkman + '\'' +
                ", s_phone='" + s_phone + '\'' +
                ", s_address='" + s_address + '\'' +
                ", s_faxes='" + s_faxes + '\'' +
                '}';
    }
}
