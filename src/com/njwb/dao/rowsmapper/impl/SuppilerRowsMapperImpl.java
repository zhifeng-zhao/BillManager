package com.njwb.dao.rowsmapper.impl;

import com.njwb.dao.rowsmapper.RowsMapper;
import com.njwb.entity.SuppilerReport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuppilerRowsMapperImpl implements RowsMapper {
    @Override
    public Object objectMapper(ResultSet rs) throws SQLException {
        SuppilerReport suppilerReport = new SuppilerReport();
        suppilerReport.setS_id(rs.getInt("s_id"));
        suppilerReport.setS_name(rs.getString("s_name"));
        suppilerReport.setAllPrice(rs.getDouble("amount)"));
        suppilerReport.setNums(rs.getInt("nums"));
        suppilerReport.setKind(rs.getInt("kind"));
        return suppilerReport;
    }
}
