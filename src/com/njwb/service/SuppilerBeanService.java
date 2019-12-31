package com.njwb.service;

import com.njwb.entity.SuppilerBean;
import com.njwb.myexception.MyException;

import java.util.List;

public interface SuppilerBeanService {
    /**
     * 通过名字模糊查询
     * @param name
     * @return
     * @throws MyException
     */
    public abstract List<SuppilerBean> querySuppilerBeanByName(String name) throws MyException;

    /**
     * 通过id删除数据
     * @param id
     * @throws MyException
     */
    public abstract void deleteSuppilerBeanById(int id) throws MyException;

    /**
     * 通过id修改数据
     * @param oldName,suppilerBean
     * @throws MyException
     */
    public abstract void updateSuppilerBeanByid(String oldName, SuppilerBean suppilerBean) throws MyException;

    /**
     * 添加数据
     * @param suppilerBean
     * @throws MyException
     */
    public abstract void addSuppilerBean(SuppilerBean suppilerBean) throws MyException;

    /**
     * 查询所有
     * @return
     * @throws MyException
     */
    public abstract List<SuppilerBean> queryAll() throws MyException;

    /**
     * 通过id查询
     * @param id
     * @return
     * @throws MyException
     */
    public abstract SuppilerBean queryById(int id) throws MyException;

    /**
     * 通过名字精准查询
     * @param name
     * @return
     * @throws MyException
     */
    public abstract SuppilerBean accurateQueryByName (String name) throws  MyException;
}
