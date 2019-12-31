package com.njwb.service;

import com.njwb.entity.UserBean;
import com.njwb.myexception.MyException;

import java.util.List;

public interface UserBeanService {
    /**
     * 登陆时根据用户名查数据
     * @param ub
     * @throws MyException
     */
    public abstract UserBean queryUserBeanByName(UserBean ub) throws MyException;

    /**
     * 表格查询
     * @param name
     * @return
     * @throws MyException
     */
    public abstract List<UserBean> queryUserBeanTable(String name) throws MyException;

    /**
     * 根据id删除
     * @param id
     * @throws MyException
     */
    public abstract void deleteUserBeanById(UserBean nowUser,int id) throws MyException;

    /**
     * 添加数据
     * @param oldub
     * @param newub
     * @param confirm
     * @throws MyException
     */
    public abstract void addUserbean(UserBean oldub, UserBean newub, String confirm) throws MyException;

    /**
     * 通过id查询用户
     * @param id
     * @return
     * @throws MyException
     */
    public abstract UserBean queryUserById(int id) throws MyException;

    /**
     * 修改用户
     * @param nowUser
     * @param newUser
     * @param comfirm
     * @throws MyException
     */
    public abstract void updateUser(UserBean nowUser, UserBean newUser, String comfirm) throws MyException;
}
