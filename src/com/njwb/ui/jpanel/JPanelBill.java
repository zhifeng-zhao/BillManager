package com.njwb.ui.jpanel;

import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.AccountBeanService;
import com.njwb.ui.model.BillTableModel;
import com.njwb.ui.jframe.BillAdd;
import com.njwb.ui.jframe.BillUpdate;
import com.njwb.util.ButtonUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 容器类：账单管理
 */
public class JPanelBill extends JPanel {
    private int width;
    private int height;

    private AccountBeanService accountBeanService = (AccountBeanService) ObjectFactory.objectMap.get("AccountBeanService");
    public JPanelBill(int width,int height) {
        this.width = width;
        this.height = height;
        bill();
    }

    /**
     * 实现账单管理界面
     */
    public void bill() {
        setLayout(null);

        //建立空表格
        JTable jTable = new JTable();
        JScrollPane jsp = new JScrollPane(jTable);

        /**
         * 头部容器
         */
        JPanel head = new JPanel();
        head.setBackground(Color.CYAN);
        head.setBounds(0,0, (int) (width/4.0*3.2), (int) (height/5.0*0.35));
        JLabel jlName = new JLabel("商品名称：");
        JTextField jtName = new JTextField(10);
        JLabel jlPay = new JLabel("是否付款：");
        String[] srr = {"否","是","请选择"};
        JComboBox<String> jComboBox = new JComboBox<String>(srr);
        jComboBox.setSelectedIndex(2);
        JButton jbSelect = new JButton("组合查询");
        head.add(jlName);
        head.add(jtName);
        head.add(jlPay);
        head.add(jComboBox);
        add(head);

        /**
         * 组合查询按钮实现
         */
        head.add(jbSelect);
        jbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(new BillTableModel(jtName.getText(), jComboBox.getSelectedIndex()));
            }
        });

        /**
         * 导入增删改容器
         */
        JPanel function = new JPanel(new GridLayout(1,6));
        function.setBounds(0,(int) (height/5.0*0.35), (int) (width/4.0*3.2), (int) (height/5.0*0.5));
        JLabel suppiler = new JLabel("账单管理>>");
        suppiler.setFont(new Font("宋体", Font.BOLD, 20));
        JButton jbOutput = new ButtonUtil().output();
        JButton jbAdd = new ButtonUtil().add();
        JButton jbUpdate = new ButtonUtil().update();
        JButton jbDelete = new ButtonUtil().delete();
        function.add(suppiler);
        add(function);
        /**
         * 导出功能实现
         */
        function.add(jbOutput);
        function.add(new JPanel());
        jbOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new一个文件选择器
                JFileChooser jFileChooser = new JFileChooser();
                //给文件选择器设置名称
                jFileChooser.setDialogTitle("文件导出");
                //弹出文件选择器
                if (JFileChooser.APPROVE_OPTION == jFileChooser.showSaveDialog(function)) {
                    BufferedWriter bufferedWriter = null;
                    try {
                        //获取并设置文件存储路径
                        bufferedWriter = new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile()));
                        TableModel model = jTable.getModel();
                        //获取所有行
                        int row = model.getRowCount();
                        //获取选中行
                        int[] rows = jTable.getSelectedRows();
                        //获取所有列
                        int column = model.getColumnCount();
                        for (int i = 0;i < column;i++) {
                            bufferedWriter.write(model.getColumnName(i));
                            bufferedWriter.write("|");
                        }
                        //判断是否选中表格，未选择打印所有
                        if (jTable.getSelectedRows().length > 0) {
                            for (int i = 0;i < rows.length;i++) {
                                bufferedWriter.newLine();
                                for (int j = 0;j < column;j++) {
                                    String str1 = jTable.getValueAt(rows[i], j).toString();
                                    bufferedWriter.write(str1);
                                    bufferedWriter.write("|");
                                }
                            }
                        } else {
                            for (int i = 0;i < row;i++) {
                                bufferedWriter.newLine();
                                for (int j = 0;j < column;j++) {
                                    String str = model.getValueAt(i, j).toString();
                                    bufferedWriter.write(str);
                                    bufferedWriter.write("|");
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(function, "导出成功！");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(function, "导出失败！");
                    } finally {
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        /**
         * 添加数据功能
         */
        function.add(jbAdd);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BillAdd().add(jTable);
            }
        });
        /**
         * 修改数据功能
         */
        function.add(jbUpdate);
        jbUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedRows().length <= 0) {
                    JOptionPane.showMessageDialog(function, "请选择要修改的内容！");
                    return;
                }

                if (jTable.getSelectedRows().length > 1) {
                    JOptionPane.showMessageDialog(function, "只能修改单行数据！");
                    return;
                }

                int row = jTable.getSelectedRow();
                int id = (int) jTable.getValueAt(row, 0);
                new BillUpdate().update(jTable, id);
            }
        });
        /**
         * 删除功能
         */
        function.add(jbDelete);
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedRows().length <= 0) {
                    JOptionPane.showMessageDialog(function, "请选中要删除的内容！");
                    return;
                }
                /**
                 * 删除多行
                 */
                if (JOptionPane.showConfirmDialog(function, "是否确定要删除选择行") == 0 ){
                    int[] rows = jTable.getSelectedRows();
                    for (int i = 0; i < rows.length; i++) {
                        int rowId = (int) jTable.getValueAt(rows[i], 0);
                        try {
                            accountBeanService.deleteById(rowId);
                            JOptionPane.showMessageDialog(function, "删除成功！");
                            //删除完成之后重新加载表格
                            jTable.setModel(new BillTableModel(null, 0));
                        } catch (MyException e1) {
                            JOptionPane.showMessageDialog(function, e1.getMessage());
                        }
                    }

                }
            }
        });
        /**
         * 表格容器
         */
        JPanel table = new JPanel(new GridLayout());
        table.setBounds(0,(int) (height/5.0*0.85), (int) (width/4.0*3.2), (int) (height));
        jTable.setModel(new BillTableModel(null, 2));
        table.add(jsp);
        add(table);
    }
}
