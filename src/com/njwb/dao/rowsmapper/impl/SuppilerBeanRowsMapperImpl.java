package com.njwb.dao.rowsmapper.impl;

import com.njwb.dao.rowsmapper.RowsMapper;
import com.njwb.entity.SuppilerBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuppilerBeanRowsMapperImpl implements RowsMapper {
    @Override
    public Object objectMapper(ResultSet rs) throws SQLException {
        SuppilerBean sb = new SuppilerBean();
        sb.setS_id(rs.getInt("s_id"));
        sb.setS_name(rs.getString("s_name"));
        sb.setS_info(rs.getString("s_info"));
        sb.setS_linkman(rs.getString("s_linkman"));
        sb.setS_phone(rs.getString("s_phone"));
        sb.setS_address(rs.getString("s_address"));
        sb.setS_faxes(rs.getString("s_faxes"));
        return sb;
    }
}
