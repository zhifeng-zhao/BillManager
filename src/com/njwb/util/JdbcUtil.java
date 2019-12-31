package com.njwb.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接
 */
public class JdbcUtil {

    //创建本地连接
    public  static DataSource ds = null;

    //创建线程本地变量
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    //加载配置文件
    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("D:\\code\\ManagementSystem\\src\\datasource.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //配置文件赋值给本地变量
        try {
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得本地连接
     * @return conn
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = ds.getConnection();
                threadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 释放连接
     */
    public static void closeConnection() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            threadLocal.remove();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭sql执行器和结果集
     * @param pstmt
     * @param rs
     */
    public static void close(PreparedStatement pstmt, ResultSet rs) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
