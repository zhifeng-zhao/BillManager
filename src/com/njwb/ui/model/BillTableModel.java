package com.njwb.ui.model;

import com.njwb.entity.AccountBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.AccountBeanService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * 表格模型
 */
public class BillTableModel extends AbstractTableModel {
    /**
     * 创建service对象
     */
    private AccountBeanService accountBeanService = (AccountBeanService)ObjectFactory.objectMap.get("AccountBeanService");

    /**
     * 初始化行列
     */
    String[] columns = {"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
    Object[][] rows = null;

    public BillTableModel(String name, int isPay) {
        try {
            List<AccountBean> list = accountBeanService.comboQueryAccountBean(name, isPay);
            rows = new Object[0][columns.length];
            if (list.size() > 0) {
                rows = new Object[list.size()][columns.length];
                int i = 0;
                for (AccountBean ab : list
                ) {
                    rows[i][0] = ab.getA_id();
                    rows[i][1] = ab.getA_name();
                    rows[i][2] = ab.getA_nums();
                    rows[i][3] = ab.getA_amount();
                    if (ab.getA_ispayed() == 0) {
                        rows[i][4] = "否";
                    } else if (ab.getA_ispayed() == 1) {
                        rows[i][4] = "是";
                    }
                    rows[i][5] = ab.getS_name();
                    rows[i][6] = ab.getA_Info();
                    rows[i][7] = ab.getA_Date();
                    i++;
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
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
