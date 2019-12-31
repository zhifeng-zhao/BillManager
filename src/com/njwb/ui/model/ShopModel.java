package com.njwb.ui.model;

import com.njwb.entity.ShopReport;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.MultiTableQueryService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ShopModel extends AbstractTableModel {
    private MultiTableQueryService multiTableQueryService = (MultiTableQueryService) ObjectFactory.objectMap.get("MultiTableQueryService");

    /**
     * 初始化行和列
     */
    String[] columns = {"商品名称","交易数量","交易金额","供应商名称"};
    Object[][] rows = null;

    public ShopModel () {
        try {
            List<ShopReport> list = multiTableQueryService.queryShop();
            rows = new Object[0][columns.length];
            if (list.size() > 0) {
                rows = new Object[list.size()][columns.length];
                int i = 0;
                for (ShopReport sr:list
                     ) {
                    rows[i][0] = sr.getA_name();
                    rows[i][1] = sr.getNums();
                    rows[i][2] = sr.getAmount();
                    rows[i][3] = sr.getS_name();
                    i++;
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
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
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }
}
