package com.njwb.dao;

import com.njwb.entity.UserBean;

import java.sql.SQLException;
import java.util.List;

public interface UserBeanDao {
    /**
     * 增
     * @param ub
     * @throws SQLException
     */
    public abstract void insertUserBean(UserBean ub) throws SQLException;

    /**
     * 删
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract int deleteUserBeanById(int id) throws SQLException;

    /**
     * 改
     * @param ub
     * @return
     * @throws SQLException
     */
    public abstract int updateUserBeanById(UserBean ub) throws SQLException;

    /**
     * 查
     * @return
     * @throws SQLException
     */
    public abstract List<UserBean> selectAllUserBean() throws SQLException;

    /**
     * 登陆功能
     * 根据用户名查询
     * @param name
     * @return
     * @throws SQLException
     */
    public abstract UserBean selectUserByName(String name) throws SQLException;

    /**
     * 通过名字进行模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    public abstract List<UserBean> selectUserByNameDim(String name) throws SQLException;

    /**
     * 通过id查找对象
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract UserBean selectUserBeanById(int id) throws SQLException;
}
