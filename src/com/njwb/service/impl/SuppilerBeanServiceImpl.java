package com.njwb.service.impl;

import com.njwb.dao.SuppilerBeanDao;
import com.njwb.entity.SuppilerBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.SuppilerBeanService;
import com.njwb.transaction.TransAction;
import com.njwb.util.MyUtil;

import java.sql.SQLException;
import java.util.List;

public class SuppilerBeanServiceImpl implements SuppilerBeanService {
    private SuppilerBeanDao suppilerBeanDao = (SuppilerBeanDao) ObjectFactory.objectMap.get("SuppilerBeanDao");
    private TransAction transAction = (TransAction) ObjectFactory.objectMap.get("TransAction");

    public TransAction getTransAction() {
        return transAction;
    }

    /**
     * 通过名字模糊查找
     * @param name
     * @return
     * @throws MyException
     */
    @Override
    public List<SuppilerBean> querySuppilerBeanByName(String name) throws MyException {
        List<SuppilerBean> list = null;
        try {
            if (MyUtil.isEmpty(name)) {
                list = suppilerBeanDao.selectAllSuppiler();
            } else {
                list = suppilerBeanDao.selectSuppilerByName(name);
                if (list.size() == 0) {
                    throw new MyException("无结果");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除
     * @param id
     * @throws MyException
     */
    @Override
    public void deleteSuppilerBeanById(int id) throws MyException {
        try {
            transAction.begin();
            suppilerBeanDao.deleteSuppilerById(id);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
            throw new MyException("删除失败");
        }
    }

    /**
     * 改
     * @param suppilerBean
     * @throws MyException
     */
    @Override
    public void updateSuppilerBeanByid(String oldName, SuppilerBean suppilerBean) throws MyException {
        try {
            judge(suppilerBean);
            transAction.begin();
            String name = suppilerBean.getS_name();
            //判断新输入的名称是否与当前名称相同
            if (!oldName.equals(name)) {
                //查找该数据是否存在
                List<SuppilerBean> list = suppilerBeanDao.accurateSelectSuppilerByName(name);
                if (list.size() > 0){
                    throw new MyException("该名称已存在");
                }
            }
            suppilerBeanDao.updateSuppilerById(suppilerBean);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 增
     * @param suppilerBean
     * @throws MyException
     */
    @Override
    public void addSuppilerBean(SuppilerBean suppilerBean) throws MyException {
        try {
            //导入的时候判断是否存在该供应商
            int id = suppilerBean.getS_id();
            SuppilerBean sb = suppilerBeanDao.selectSuppilerById(id);
            if (sb != null) {
                throw new MyException("该供应商已存在！");
            }
            String name = suppilerBean.getS_name();
            //通过名字精准查询
            List<SuppilerBean> list = suppilerBeanDao.accurateSelectSuppilerByName(name);
            if (list.size() > 0) {
                throw new MyException("该供应商已存在！");
            }
            judge(suppilerBean);
            transAction.begin();
            suppilerBeanDao.insertSuppiler(suppilerBean);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 查所有
     * @return
     * @throws MyException
     */
    @Override
    public List<SuppilerBean> queryAll() throws MyException {
        List<SuppilerBean> list = null;
        try {
            list = suppilerBeanDao.selectAllSuppiler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据id查找
     * @param id
     * @return
     * @throws MyException
     */
    @Override
    public SuppilerBean queryById(int id) throws MyException {
        SuppilerBean suppilerBean = null;
        try {
            suppilerBean = suppilerBeanDao.selectSuppilerById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppilerBean;
    }

    /**
     * 通过名字精准查询
     * @param name
     * @return
     * @throws MyException
     */
    @Override
    public SuppilerBean accurateQueryByName(String name) throws MyException {
        SuppilerBean suppilerBean = new SuppilerBean();
        try {
            List<SuppilerBean> list = suppilerBeanDao.accurateSelectSuppilerByName(name);
            if (list.size() > 0) {
                suppilerBean = list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppilerBean;
    }

    /**
     * 判断红星内容是否为空
     * @param suppilerBean
     * @throws MyException
     */
    public void judge(SuppilerBean suppilerBean) throws MyException {
        String name = suppilerBean.getS_name();
        String link = suppilerBean.getS_linkman();
        String phone = suppilerBean.getS_phone();
        String addr = suppilerBean.getS_address();
        if (MyUtil.isEmpty(name)) {
            throw new MyException("供应商名不能为空！");
        }
        if (MyUtil.isEmpty(link)) {
            throw new MyException("联系人不能为空");
        }
        if (MyUtil.isEmpty(phone)) {
            throw new MyException("电话格式不正确");
        }
        if (MyUtil.isEmpty(addr)) {
            throw new MyException("地址不能为空");
        }
    }
}
