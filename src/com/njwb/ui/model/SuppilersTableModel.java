package com.njwb.ui.model;

import com.njwb.entity.SuppilerBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.SuppilerBeanService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SuppilersTableModel extends AbstractTableModel {
    /**
     * 创建service对象
     */
    private SuppilerBeanService suppilerBeanService = (SuppilerBeanService) ObjectFactory.objectMap.get("SuppilerBeanService");
//    private SuppilerBeanService suppilerBeanService = new SuppilerBeanServiceImpl();
    /**
     * 初始化行列
     */
    String[] columns = {"编号","供应商名称","供应商描述","联系人","电话","地址"};
    Object[][] rows = null;

    public SuppilersTableModel(String name, JPanel jp) {
        rows = new Object[0][columns.length];
        List<SuppilerBean> list = null;
        try {
            //通过名字进行查询，若名字为空默认查询所有
            list = suppilerBeanService.querySuppilerBeanByName(name);
            rows = new Object[list.size()][columns.length];
            if (list.size() > 0) {
                int i = 0;
                for (SuppilerBean s : list
                ) {
                    rows[i][0] = s.getS_id();
                    rows[i][1] = s.getS_name();
                    rows[i][2] = s.getS_info();
                    rows[i][3] = s.getS_linkman();
                    rows[i][4] = s.getS_phone();
                    rows[i][5] = s.getS_address();
                    i++;
                }
            }
        } catch (MyException e) {
            JOptionPane.showMessageDialog(jp, e.getMessage());
        }


    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
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
}
