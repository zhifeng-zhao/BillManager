package com.njwb.dao.rowsmapper.impl;

import com.njwb.dao.rowsmapper.RowsMapper;
import com.njwb.entity.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBeanRowsMapperImpl implements RowsMapper {
    @Override
    public Object objectMapper(ResultSet rs) throws SQLException {
        UserBean ub = new UserBean();
        ub.setU_id(rs.getInt("u_id"));
        ub.setU_name(rs.getString("u_name"));
        ub.setU_password(rs.getString("u_password"));
        ub.setU_gender(rs.getString("u_gender"));
        ub.setU_age(rs.getInt("u_age"));
        ub.setU_phone(rs.getString("u_phone"));
        ub.setU_address(rs.getString("u_address"));
        ub.setU_auth(rs.getString("u_auth"));
        return ub;
    }
}
