package com.njwb.dao.impl;

import com.njwb.dao.AccountBeanDao;
import com.njwb.dao.rowsmapper.impl.AccountBeanRowsMapperImpl;
import com.njwb.entity.AccountBean;
import com.njwb.util.JdbcTelmpate;

import java.sql.SQLException;
import java.util.List;

public class AccountBeanDaoImpl implements AccountBeanDao {
    /**
     * 增
     * @param ab
     * @throws SQLException
     */
    @Override
    public void insertAccountBean(AccountBean ab) throws SQLException {
        String sql = "insert into t_accountbean(a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date) values(?,?,?,?,?,?,?,?)";
        JdbcTelmpate.executeUpdate(sql, ab.getA_name(), ab.getA_nums(), ab.getA_amount(), ab.getA_unit(), ab.getA_ispayed(), ab.getS_id(), ab.getA_Info(), ab.getA_Date());
    }

    /**
     * 通过id删除
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteAccountBeanById(int id) throws SQLException {
        String sql = "delete from t_accountbean where a_id = ?";
        return JdbcTelmpate.executeUpdate(sql, id);
    }

    /**
     * 通过id修改
     * @param ab
     * @return
     * @throws SQLException
     */
    @Override
    public int updateAccountBeanById(AccountBean ab) throws SQLException {
        String sql = "update t_accountbean set a_name=?,a_nums=?,a_amount=?,a_unit=?,a_ispayed=?,s_id=?,a_Info=?,a_Date=? where a_id = ?";
        return JdbcTelmpate.executeUpdate(sql, ab.getA_name(), ab.getA_nums(), ab.getA_amount(), ab.getA_unit(), ab.getA_ispayed(), ab.getS_id(), ab.getA_Info(), ab.getA_Date(), ab.getA_id());
    }

    /**
     * 查找所有
     * @return
     * @throws SQLException
     */
    @Override
    public List<AccountBean> selectAllAccountBean() throws SQLException {
        String sql = "select a_id,a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date from t_accountbean";
        return JdbcTelmpate.executeQuery(sql, new AccountBeanRowsMapperImpl(), null);
    }

    /**
     * 通过名字进行模糊查询
     */
    @Override
    public List<AccountBean> selectAccountBeanByAName(String name) throws SQLException {
        String like = "%"+name+"%";
        String sql = "select a_id,a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date from t_accountbean where a_name like ?";
        return JdbcTelmpate.executeQuery(sql, new AccountBeanRowsMapperImpl(), like);
    }

    /**
     * 通过是否付款查询
     * @param isPay
     * @return
     * @throws SQLException
     */
    @Override
    public List<AccountBean> selectAccountBeanByIsPay(int isPay) throws SQLException {
        String sql = "select a_id,a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date from t_accountbean where a_ispayed = ?";
        return JdbcTelmpate.executeQuery(sql, new AccountBeanRowsMapperImpl(), isPay);
    }

    /**
     * 通过名字和是否付款进行组合查询
     * @param name
     * @param ispay
     * @return
     * @throws SQLException
     */
    @Override
    public List<AccountBean> comboSelectAccountBeanBy(String name, int ispay) throws SQLException {
        String like = "%"+name+"%";
        String sql = "select a_id,a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date from t_accountbean where a_ispayed = ? and a_name like ?";
        return JdbcTelmpate.executeQuery(sql, new AccountBeanRowsMapperImpl(), ispay, like);
    }

    /**
     * 通过id进行查询
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public AccountBean selectAccountBeanById(int id) throws SQLException {
        AccountBean accountBean = null;
        String sql = "select a_id,a_name,a_nums,a_amount,a_unit,a_ispayed,s_id,a_Info,a_Date from t_accountbean where a_id = ?";
        List<AccountBean> list = JdbcTelmpate.executeQuery(sql, new AccountBeanRowsMapperImpl(), id);
        if (list.size() > 0 ) {
            accountBean = list.get(0);
        }
        return accountBean;
    }

}
