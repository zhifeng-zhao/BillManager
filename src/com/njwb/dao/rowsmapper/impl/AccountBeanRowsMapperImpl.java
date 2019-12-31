package com.njwb.dao.rowsmapper.impl;

import com.njwb.dao.rowsmapper.RowsMapper;
import com.njwb.entity.AccountBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBeanRowsMapperImpl implements RowsMapper {
    @Override
    public Object objectMapper(ResultSet rs) throws SQLException {
        AccountBean ab = new AccountBean();
        ab.setA_id(rs.getInt("a_id"));
        ab.setA_name(rs.getString("a_name"));
        ab.setA_nums(rs.getInt("a_nums"));
        ab.setA_amount(rs.getDouble("a_amount"));
        ab.setA_unit(rs.getString("a_unit"));
        ab.setA_ispayed(rs.getInt("a_ispayed"));
        ab.setS_id(rs.getInt("s_id"));
        ab.setA_Info(rs.getString("a_Info"));
        ab.setA_Date(rs.getDate("a_Date"));
        return ab;
    }
}
