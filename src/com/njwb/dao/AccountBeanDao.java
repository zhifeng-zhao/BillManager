package com.njwb.dao;

import com.njwb.entity.AccountBean;

import java.sql.SQLException;
import java.util.List;

public interface AccountBeanDao {
    /**
     * 增
     * @param ab
     * @throws SQLException
     */
    public abstract void insertAccountBean(AccountBean ab) throws SQLException;

    /**
     * 删
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract int deleteAccountBeanById(int id) throws SQLException;

    /**
     * 改
     * @param ab
     * @return
     * @throws SQLException
     */
    public abstract int updateAccountBeanById(AccountBean ab) throws SQLException;

    /**
     * 查找所有
     * @return
     * @throws SQLException
     */
    public abstract List<AccountBean> selectAllAccountBean() throws SQLException;

    /**
     * 通过名字进行模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    public abstract List<AccountBean> selectAccountBeanByAName(String name) throws SQLException;

    /**
     * 通过付款进行模糊查询
     * @param isPay
     * @return
     * @throws SQLException
     */
    public abstract List<AccountBean> selectAccountBeanByIsPay(int isPay) throws SQLException;

    /**
     * 通过名字和是否付款进行组合查询
     * @param name
     * @param ispay
     * @return
     * @throws SQLException
     */
    public abstract List<AccountBean> comboSelectAccountBeanBy(String name, int ispay) throws SQLException;

    /**
     * 通过id进行查询
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract AccountBean selectAccountBeanById(int id) throws SQLException;
}
