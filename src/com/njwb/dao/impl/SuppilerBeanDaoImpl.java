package com.njwb.dao.impl;

import com.njwb.dao.SuppilerBeanDao;
import com.njwb.dao.rowsmapper.impl.SuppilerBeanRowsMapperImpl;
import com.njwb.entity.SuppilerBean;
import com.njwb.util.JdbcTelmpate;

import java.sql.SQLException;
import java.util.List;

public class SuppilerBeanDaoImpl implements SuppilerBeanDao {
    /**
     * 增
     * @param sb
     * @throws SQLException
     */
    @Override
    public void insertSuppiler(SuppilerBean sb) throws SQLException {
        String sql = "insert into t_suppilerbean(s_name,s_info,s_linkman,s_phone,s_address,s_faxes) values(?,?,?,?,?,?)";
        JdbcTelmpate.executeUpdate(sql, sb.getS_name(), sb.getS_info(), sb.getS_linkman(),sb.getS_phone(), sb.getS_address(), sb.getS_faxes());
    }

    /**
     * 删
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteSuppilerById(int id) throws SQLException {
        String sql = "delete from t_suppilerbean where s_id = ?";
        return JdbcTelmpate.executeUpdate(sql, id);
    }

    /**
     * 改
     * @param sb
     * @return
     * @throws SQLException
     */
    @Override
    public int updateSuppilerById(SuppilerBean sb) throws SQLException {
        String sql = "update t_suppilerbean set s_name=?,s_info=?,s_linkman=?,s_phone=?,s_address=?,s_faxes=? where s_id=?";
        return JdbcTelmpate.executeUpdate(sql, sb.getS_name(), sb.getS_info(), sb.getS_linkman(),sb.getS_phone(), sb.getS_address(), sb.getS_faxes(), sb.getS_id());
    }

    /**
     * 查找所有
     * @return
     * @throws SQLException
     */
    @Override
    public List<SuppilerBean> selectAllSuppiler() throws SQLException {
        String sql = "select s_id,s_name,s_info,s_linkman,s_phone,s_address,s_faxes from t_suppilerbean";
        return JdbcTelmpate.executeQuery(sql, new SuppilerBeanRowsMapperImpl(), null);
    }

    /**
     * 根据名字实现模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public List<SuppilerBean> selectSuppilerByName(String name) throws SQLException {
        String likeName = "%"+name+"%";
        String sql = "select s_id,s_name,s_info,s_linkman,s_phone,s_address,s_faxes from t_suppilerbean where s_name like ?";
        return JdbcTelmpate.executeQuery(sql ,new SuppilerBeanRowsMapperImpl(), likeName);
    }

    /**
     * 通过名字进行精准查询
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public List<SuppilerBean> accurateSelectSuppilerByName(String name) throws  SQLException {
        String sql = "select s_id,s_name,s_info,s_linkman,s_phone,s_address,s_faxes from t_suppilerbean where s_name = ?";
        return JdbcTelmpate.executeQuery(sql, new SuppilerBeanRowsMapperImpl(), name);
    }

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public SuppilerBean selectSuppilerById(int id) throws SQLException {
        SuppilerBean suppilerBean = null;
        String sql = "select s_id,s_name,s_info,s_linkman,s_phone,s_address,s_faxes from t_suppilerbean where s_id = ?";
        List<SuppilerBean> list = JdbcTelmpate.executeQuery(sql, new SuppilerBeanRowsMapperImpl(), id);
        if (list.size() > 0) {
            suppilerBean = list.get(0);
        }
        return suppilerBean;
    }


}
