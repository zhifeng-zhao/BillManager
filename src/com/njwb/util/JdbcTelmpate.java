package com.njwb.util;

import com.njwb.dao.rowsmapper.RowsMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装增删改查
 */
public class JdbcTelmpate {
    /**
     * 增删改
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        //获取连接
        Connection conn = JdbcUtil.getConnection();
        //sql执行器
        PreparedStatement pstmt = conn.prepareStatement(sql);
        setParams(pstmt, params);
        int count = pstmt.executeUpdate();
        JdbcUtil.close(pstmt, null);
        return count;
    }

    /**
     * 查询
     * @param sql
     * @param rows
     * @param params
     * @return
     * @throws SQLException
     */
    public static List executeQuery(String sql, RowsMapper rows, Object... params) throws SQLException {
        List list = new ArrayList();
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        setParams(pstmt, params);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Object obj = rows.objectMapper(rs);
            list.add(obj);
        }
        JdbcUtil.close(pstmt, rs);
        return list;
    }
    /**
     *
     * @param pstmt
     * @param prams
     * @throws SQLException
     */
    public static void setParams(PreparedStatement pstmt, Object... prams) throws SQLException{
        if (prams != null && prams.length > 0) {
            for (int i = 0; i < prams.length; i++) {
                pstmt.setObject(i + 1, prams[i]);
            }
        }
    }

}
