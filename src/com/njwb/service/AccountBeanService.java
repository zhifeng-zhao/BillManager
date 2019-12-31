package com.njwb.service;

import com.njwb.entity.AccountBean;
import com.njwb.myexception.MyException;

import java.util.List;

public interface AccountBeanService {
    /**
     * 查询所有
     * @return
     * @throws MyException
     */
    public abstract List<AccountBean> queryAllAccountBean() throws MyException;

    /**
     * 组合查询
     * @param name
     * @param isPay
     * @return
     * @throws MyException
     */
    public abstract List<AccountBean> comboQueryAccountBean(String name, int isPay) throws MyException;
    /**
     * 添加数据
     * @param accountBean
     * @throws MyException
     */
    public abstract void insertAcountBean(AccountBean accountBean) throws MyException;

    /**
     * 通过id进行查找
     * @param id
     * @return
     * @throws MyException
     */
    public abstract AccountBean queryById(int id) throws MyException;

    /**
     * 通过id进行删除
     * @param id
     * @throws MyException
     */
    public abstract void deleteById(int id) throws MyException;

    /**
     * 通过id进行修改
     * @param accountBean
     * @throws MyException
     */
    public abstract void updateById(AccountBean accountBean) throws  MyException;
}
