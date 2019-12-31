package com.njwb.dao.impl;

import com.njwb.dao.UserBeanDao;
import com.njwb.dao.rowsmapper.impl.UserBeanRowsMapperImpl;
import com.njwb.entity.UserBean;
import com.njwb.util.JdbcTelmpate;

import java.sql.SQLException;
import java.util.List;

public class UserBeanDaoImpl implements UserBeanDao {
    /**
     * 增
     * @param ub
     * @throws SQLException
     */
    @Override
    public void insertUserBean(UserBean ub) throws SQLException {
        String sql = "insert into t_userbean(u_name,u_password,u_gender,u_age,u_phone,u_address,u_auth) values(?,?,?,?,?,?,?) ";
        JdbcTelmpate.executeUpdate(sql, ub.getU_name(), ub.getU_password(), ub.getU_gender(), ub.getU_age(), ub.getU_phone(), ub.getU_address(), ub.getU_auth());
    }

    /**
     * 删
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteUserBeanById(int id) throws SQLException {
        String sql = "delete from t_userbean where u_id = ?";
        return JdbcTelmpate.executeUpdate(sql, id);
    }

    /**
     * 修改
     * @param ub
     * @return
     * @throws SQLException
     */
    @Override
    public int updateUserBeanById(UserBean ub) throws SQLException {
        String sql = "update t_userbean set u_name=?,u_password=?,u_gender=?,u_age=?,u_phone=?,u_address=?,u_auth=? where u_id = ?";
        return JdbcTelmpate.executeUpdate(sql, ub.getU_name(), ub.getU_password(), ub.getU_gender(), ub.getU_age(), ub.getU_phone(), ub.getU_address(), ub.getU_auth(), ub.getU_id());
    }

    /**
     * 查找所有
     * @return
     * @throws SQLException
     */
    @Override
    public List<UserBean> selectAllUserBean() throws SQLException {
        String sql = "select u_id,u_name,u_password,u_gender,u_age,u_phone,u_address,u_auth from t_userbean";
        return JdbcTelmpate.executeQuery(sql, new UserBeanRowsMapperImpl(), null);
    }

    /**
     * 根据用户名查询
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public UserBean selectUserByName(String name) throws SQLException {
        UserBean ub = null;
        String sql = "select u_id,u_name,u_password,u_gender,u_age,u_phone,u_address,u_auth from t_userbean where u_name=?";
        List<UserBean> list = JdbcTelmpate.executeQuery(sql, new UserBeanRowsMapperImpl(), name);
        if (list.size() > 0) {
            ub = list.get(0);
        }
        return ub;
    }

    /**
     * 通过名字进行模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public List<UserBean> selectUserByNameDim(String name) throws SQLException {
        String like = "%" + name + "%";
        String sql = "select u_id,u_name,u_password,u_gender,u_age,u_phone,u_address,u_auth from t_userbean where u_name like ?";
        return JdbcTelmpate.executeQuery(sql, new UserBeanRowsMapperImpl(), like);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public UserBean selectUserBeanById(int id) throws SQLException {
        UserBean userBean = null;
        String sql = "select u_id,u_name,u_password,u_gender,u_age,u_phone,u_address,u_auth from t_userbean where u_id = ?";
        List<UserBean> list = JdbcTelmpate.executeQuery(sql, new UserBeanRowsMapperImpl(), id);
        if (list.size() > 0) {
            userBean = list.get(0);
        }
        return userBean;
    }
}
