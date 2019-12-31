package com.njwb.dao.impl;

import com.njwb.dao.MultiTableQueryDao;
import com.njwb.dao.rowsmapper.impl.ShopRowsMaperImpl;
import com.njwb.dao.rowsmapper.impl.SuppilerRowsMapperImpl;
import com.njwb.entity.ShopReport;
import com.njwb.entity.SuppilerReport;
import com.njwb.util.JdbcTelmpate;

import java.sql.SQLException;
import java.util.List;

public class MultiTableQueryDaoImpl implements MultiTableQueryDao {
    @Override
    public List<ShopReport> selectShop() throws SQLException {
        String sql = "select a_name,sum(a_nums),sum(a_amount),s_name from t_suppilerbean,t_accountbean where t_accountbean.s_id = t_suppilerbean.s_id group by a_name";
        return JdbcTelmpate.executeQuery(sql, new ShopRowsMaperImpl(), null);
    }

    @Override
    public List<SuppilerReport> selectSuppiler() throws SQLException {
        String sql = "select distinct t_suppilerbean.s_id,s_name,b.amount,b.kind,b.nums from t_suppilerbean a left join (select sum(a_amount) as amount,count(a_name) as kind,sum(a_nums) as nums,s_id from t_accountbean group by a_id) b on t_suppilerbean.s_id =t_accountbean.id group by s_id;";
        return JdbcTelmpate.executeQuery(sql, new SuppilerRowsMapperImpl(), null);
    }
}
