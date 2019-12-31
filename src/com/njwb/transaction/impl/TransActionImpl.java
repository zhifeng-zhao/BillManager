package com.njwb.transaction.impl;

import com.njwb.transaction.TransAction;
import com.njwb.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransActionImpl implements TransAction {
    @Override
    public void begin() {
        Connection conn = JdbcUtil.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        Connection conn = JdbcUtil.getConnection();
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();

    }

    @Override
    public void rollback() {
        Connection conn = JdbcUtil.getConnection();
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.closeConnection();
    }
}
