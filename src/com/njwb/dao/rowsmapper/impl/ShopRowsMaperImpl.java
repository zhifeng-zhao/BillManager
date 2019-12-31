package com.njwb.dao.rowsmapper.impl;

import com.njwb.dao.rowsmapper.RowsMapper;
import com.njwb.entity.ShopReport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowsMaperImpl implements RowsMapper {
    @Override
    public Object objectMapper(ResultSet rs) throws SQLException {
        ShopReport shopReport = new ShopReport();
        shopReport.setA_name(rs.getString("a_name"));
        shopReport.setNums(rs.getInt("sum(a_nums)"));
        shopReport.setAmount(rs.getDouble("sum(a_amount)"));
        shopReport.setS_name(rs.getString("s_name"));
        return shopReport;
    }
}
