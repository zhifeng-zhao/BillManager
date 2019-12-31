package com.njwb.service.impl;

import com.njwb.dao.AccountBeanDao;
import com.njwb.entity.AccountBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.AccountBeanService;
import com.njwb.transaction.TransAction;
import com.njwb.util.MyUtil;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AccountBeanServiceImpl implements AccountBeanService {
    private AccountBeanDao accountBeanDao = (AccountBeanDao)ObjectFactory.objectMap.get("AccountBeanDao");
    private TransAction transAction = (TransAction) ObjectFactory.objectMap.get("TransAction");

    /**
     * 查询所有
     * @return
     * @throws MyException
     */
    @Override
    public List<AccountBean> queryAllAccountBean() throws MyException {
        List<AccountBean> list = null;
        try {
            list = accountBeanDao.selectAllAccountBean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 组合查询
     * @param name
     * @param isPay
     * @return
     * @throws MyException
     */
    @Override
    public List<AccountBean> comboQueryAccountBean(String name, int isPay) throws MyException {
        List<AccountBean> list = null;
        try {
            if (MyUtil.isEmpty(name) && isPay == 2) {
                list = accountBeanDao.selectAllAccountBean();
            }
            if (!MyUtil.isEmpty(name) && isPay == 2) {
                list = accountBeanDao.selectAccountBeanByAName(name);
            }
            if (MyUtil.isEmpty(name) && (isPay == 1 || isPay == 0)) {
                list = accountBeanDao.selectAccountBeanByIsPay(isPay);
            }
            if (!MyUtil.isEmpty(name) && (isPay == 1 || isPay == 0)) {
                list = accountBeanDao.comboSelectAccountBeanBy(name, isPay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 插入数据
     * @param accountBean
     * @throws MyException
     */
    @Override
    public void insertAcountBean(AccountBean accountBean) throws MyException {
        try {
            judge(accountBean);
            transAction.begin();
            accountBeanDao.insertAccountBean(accountBean);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 通过id进行查询
     * @param id
     * @return
     * @throws MyException
     */
    @Override
    public AccountBean queryById(int id) throws MyException {
        AccountBean accountBean = null;
        try {
            accountBean = accountBeanDao.selectAccountBeanById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBean;
    }

    /**
     * 通过id删除
     * @param id
     * @throws MyException
     */
    @Override
    public void deleteById(int id) throws MyException {
        try {
            transAction.begin();
            accountBeanDao.deleteAccountBeanById(id);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 修改数据
     * @param accountBean
     * @throws MyException
     */
    @Override
    public void updateById( AccountBean accountBean) throws MyException {
        try {
            transAction.begin();
            judge(accountBean);
            accountBeanDao.updateAccountBeanById(accountBean);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 红星数据不能为空判断
     * @param accountBean
     * @throws MyException
     */
    public void judge(AccountBean accountBean) throws MyException {
        String name = accountBean.getA_name();
        int nums = accountBean.getA_nums();
        String unit = accountBean.getA_unit();
        double amount = accountBean.getA_amount();
        Date time = accountBean.getA_Date();
        if (MyUtil.isEmpty(name)) {
            throw new MyException("商品名不能为空！");
        }
        if (MyUtil.isEmpty(unit)) {
            throw new MyException("交易单位不能为空");
        }
        if (nums == 0) {
            throw new MyException("交易数量不能为零");
        }
        if (amount == 0) {
            throw new MyException("交易金额不能为零");
        }
        //时间为自动获取当前时间
        if (time == null) {
            throw new MyException("交易时间不能为空");
        }
    }

}
