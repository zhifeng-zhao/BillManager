package com.njwb.ui.model;

import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * 用户表格模型
 */
public class UserTableModel extends AbstractTableModel {
    private UserBeanService userBeanService = (UserBeanService) ObjectFactory.objectMap.get("UserBeanService");

    String[] columns = {"编号","姓名","性别","年龄","电话","地址","权限"};
    Object rows[][] = null;

    public UserTableModel(String name, JPanel jPanel) {
        try {
            List<UserBean> list = userBeanService.queryUserBeanTable(name);
            rows = new Object[0][columns.length];
            if (list.size() > 0) {
                rows = new Object[list.size()][columns.length];
                int i = 0;
                for (UserBean ub:list
                     ) {
                    rows[i][0] = ub.getU_id();
                    rows[i][1] = ub.getU_name();
                    rows[i][2] = ub.getU_gender();
                    rows[i][3] = ub.getU_age();
                    rows[i][4] = ub.getU_phone();
                    rows[i][5] = ub.getU_address();
                    rows[i][6] = ub.getU_auth();
                    i++;
                }
            }
        } catch (MyException e) {
            //无结果信息显示
            JOptionPane.showMessageDialog(jPanel, e.getMessage());
        }
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
