package com.njwb.ui.model;

import com.njwb.entity.SuppilerReport;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.MultiTableQueryService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SuppilerModel extends AbstractTableModel {
    private MultiTableQueryService multiTableQueryService = (MultiTableQueryService) ObjectFactory.objectMap.get("MultiTableQueryService");

    String[] columns = {"供应商编号","名称","交易金额","商品种类","总商品数量"};
    Object[][] rows = null;

    public SuppilerModel() {
        try {
            List<SuppilerReport> list = multiTableQueryService.querySuppiler();
            rows = new Object[0][columns.length];
            if (list.size() > 0) {
                rows = new Object[list.size()][columns.length];
                int i = 0;
                for (SuppilerReport sr:list
                     ) {
                    rows[i][0] = sr.getS_id();
                    rows[i][1] = sr.getS_name();
                    rows[i][2] = sr.getAllPrice();
                    rows[i][3] = sr.getKind();
                    rows[i][4] = sr.getNums();
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
