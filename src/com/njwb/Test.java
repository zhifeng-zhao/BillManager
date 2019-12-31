package com.njwb;

import com.njwb.dao.SuppilerBeanDao;
import com.njwb.dao.UserBeanDao;
import com.njwb.dao.impl.SuppilerBeanDaoImpl;
import com.njwb.dao.impl.UserBeanDaoImpl;
import com.njwb.entity.SuppilerBean;
import com.njwb.entity.UserBean;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args){
        SuppilerBeanDao sd = new SuppilerBeanDaoImpl();
        UserBeanDao ub = new UserBeanDaoImpl();
        try {
            List<SuppilerBean> list = sd.selectAllSuppiler();
            for (SuppilerBean sb:list) {
                System.out.println(sb.toString());
            }
//            List<UserBean> userBeans = ub.selectAllUserBean();
//            for (UserBean u:userBeans
//                 ) {
//                u.toString();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
