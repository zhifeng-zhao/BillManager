package com.njwb.transaction;

public interface TransAction {
    /**
     * 开启
     */
    void begin();

    /**
     * 提交
     */
    void commit();

    /**
     * 关闭
     */
    void rollback();

}
