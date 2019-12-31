package com.njwb.service.impl;

import com.njwb.dao.UserBeanDao;
import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;
import com.njwb.transaction.TransAction;
import com.njwb.util.MyUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBeanServiceImpl implements UserBeanService {
    /**
     * 创建dao类对象
     * 获取事务对象
     */
    private UserBeanDao userBeanDao = (UserBeanDao)ObjectFactory.objectMap.get("UserBeanDao");
    private TransAction transAction = (TransAction)ObjectFactory.objectMap.get("TransAction");

    /**
     * 登陆条件判断
     * @param ub
     * @throws MyException
     */
    @Override
    public UserBean queryUserBeanByName(UserBean ub) throws MyException {
        UserBean userBean = null;
        String name = ub.getU_name();
        String pwd = ub.getU_password();
        String auth = ub.getU_auth();
        if (MyUtil.isEmpty(name)) {
            throw new MyException("用户名不能为空！");
        }
        if (MyUtil.isEmpty(pwd)) {
            throw new MyException("密码不能为空！");
        }
        if (MyUtil.isEmpty(auth)) {
            throw new MyException("身份不能为空！");
        }
        try {
            //通过登陆名查找数据库是否存在这个用户
            userBean = userBeanDao.selectUserByName(name);
            if (userBean == null) {
                throw new MyException("用户不存在！");
            } else if (!pwd.equals(userBean.getU_password()) || !auth.equals(userBean.getU_auth())) {
                throw new MyException("您输入的密码或选择的身份有误，请重新输入和选择！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }

    /**
     * 表格的查询
     * @param name
     * @return
     * @throws MyException
     */
    @Override
    public List<UserBean> queryUserBeanTable(String name) throws MyException {
        List<UserBean> list = null;
        try {
            //判断名字为空默认查所有
            if (MyUtil.isEmpty(name)) {
                list = userBeanDao.selectAllUserBean();
            } else {
                list = userBeanDao.selectUserByNameDim(name);
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
     * 根据id删除
     * @param id
     * @throws MyException
     */
    @Override
    public void deleteUserBeanById(UserBean nowUser, int id) throws MyException {
        try {
            UserBean deleteUser = userBeanDao.selectUserBeanById(id);
            String nowAuth = nowUser.getU_auth();
            String deleteAuth = deleteUser.getU_auth();
            if (nowAuth.equals(deleteAuth)) {
                throw new MyException("同级不能删除!");
            }
            if ("普通员工".equals(nowAuth)) {
                throw new MyException("普通员工不能删除用户！");
            }
            if ("部门经理".equals(nowAuth) && "管理员".equals(deleteAuth)) {
                throw new MyException("不能删除上级!");
            }
            transAction.begin();
            userBeanDao.deleteUserBeanById(id);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
            throw new MyException("删除失败");
        }
    }

    /**
     * 添加用户
     * @param oldub 当前操作用户
     * @param newub 新增用户
     * @param confirm 确认密码
     * @throws MyException
     */
    @Override
    public void addUserbean(UserBean oldub, UserBean newub, String confirm) throws MyException {
        judge(newub, confirm);
        try {
            //通过名字精准查询
            UserBean userBean = userBeanDao.selectUserByName(newub.getU_name());
            if (userBean != null) {
                throw new MyException("该用户已存在!");
            }
            //权限校验
            if ("普通员工".equals(oldub.getU_auth())) {
                throw new MyException("员工不能添加用户！");
            }
            if ("部门经理".equals(oldub.getU_auth()) && "部门经理".equals(newub.getU_auth())) {
                throw new MyException("不能添加同级用户！");
            }
            if ("管理员".equals(newub.getU_auth())) {
                throw new MyException("不能添加管理员！");
            }
            transAction.begin();
            userBeanDao.insertUserBean(newub);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }

    }

    /**
     * 通过id查询
     * @param id
     * @return
     * @throws MyException
     */
    @Override
    public UserBean queryUserById(int id) throws MyException {
        UserBean userBean = null;
        try {
            userBean = userBeanDao.selectUserBeanById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }

    /**
     * 修改用户
     * @param newUser
     * @param newUser
     * @param comfirm
     * @throws MyException
     */
    @Override
    public void updateUser(UserBean nowUser, UserBean newUser, String comfirm) throws MyException {
        try {
            judge(newUser, comfirm);
            if (newUser.getU_id() != nowUser.getU_id()) {
                if ("管理员".equals(newUser.getU_auth())) {
                    throw new MyException("不能提拨为管理员");
                }
            }
            transAction.begin();
            userBeanDao.updateUserBeanById(newUser);
            transAction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transAction.rollback();
        }
    }

    /**
     * 红星内容空判断
     * @param userBean
     * @throws MyException
     */
    public void judge(UserBean userBean, String confirm) throws MyException {
        String name = userBean.getU_name();
        String pwd = userBean.getU_password();
        String sex = userBean.getU_gender();
        int age = userBean.getU_age();
        String phone = userBean.getU_phone();
        String auth = userBean.getU_auth();
        if (MyUtil.isEmpty(name)) {
            throw new MyException("用户名名不能为空！");
        }
        if (MyUtil.isEmpty(pwd)) {
            throw new MyException("用户密码不能为空");
        }
        if (MyUtil.isEmpty(sex)) {
            throw new MyException("用户性别不能为空");
        }
        if (MyUtil.isEmpty(phone)) {
            throw new MyException("用户电话不能为空");
        }
        if (MyUtil.isEmpty(auth)) {
            throw new MyException("用户权限不能为空");
        }
        if (age == 0) {
            throw new MyException("用户年龄不能为零");
        }
        if (MyUtil.isEmpty(confirm)) {
            throw new MyException("确认密码不能为空");
        }
        //校验密码是否相等
        if (!confirm.equals(userBean.getU_password())) {
            throw new MyException("两次密码输入不一致！");
        }

    }
}
