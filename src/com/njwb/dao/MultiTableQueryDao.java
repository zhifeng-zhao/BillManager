package com.njwb.dao;

import com.njwb.entity.ShopReport;
import com.njwb.entity.SuppilerReport;

import java.sql.SQLException;
import java.util.List;

/**
 * 多表查询数据访问层
 */
public interface MultiTableQueryDao {
    /**
     * 商品的多表查询
     * @return
     * @throws SQLException
     */
    public abstract List<ShopReport> selectShop() throws SQLException;

    /**
     * 供应商的多表查询
     * @return
     * @throws SQLException
     */
    public abstract List<SuppilerReport> selectSuppiler() throws SQLException;
}
