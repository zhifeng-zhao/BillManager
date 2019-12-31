package com.njwb.dao;

import com.njwb.entity.SuppilerBean;

import java.sql.SQLException;
import java.util.List;

public interface SuppilerBeanDao {
    /**
     * 查询
     * @param sb
     * @throws SQLException
     */
    public abstract void insertSuppiler (SuppilerBean sb) throws SQLException;

    /**
     * 根据id删除
     * @param id
     * @throws SQLException
     */
    public abstract int deleteSuppilerById (int id) throws SQLException;

    /**
     * 根据id更新
     * @param sb
     * @throws SQLException
     */
    public abstract int updateSuppilerById (SuppilerBean sb) throws SQLException;

    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    public abstract List<SuppilerBean> selectAllSuppiler () throws SQLException;

    /**
     * 通过名字进行模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    public abstract List<SuppilerBean> selectSuppilerByName (String name) throws SQLException;

    /**
     * 通过名字精准查询
     * @param name
     * @return
     * @throws SQLException
     */
    public abstract List<SuppilerBean> accurateSelectSuppilerByName(String name) throws  SQLException;
    /**
     * 通过id进行查询
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract SuppilerBean selectSuppilerById (int id) throws SQLException;

//    public abstract SuppilerBean addSuppilerBySql (String sql) throws SQLException;
}
