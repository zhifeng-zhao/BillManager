package com.njwb.ui.jpanel;

import com.njwb.entity.SuppilerBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.SuppilerBeanService;
import com.njwb.ui.model.SuppilersTableModel;
import com.njwb.ui.jframe.SuppilersAdd;
import com.njwb.ui.jframe.SuppilersUpdate;
import com.njwb.util.ButtonUtil;
import com.njwb.util.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class JPanelSuppilers extends JPanel {
    private SuppilerBeanService sbs = (SuppilerBeanService) ObjectFactory.objectMap.get("SuppilerBeanService");
    private int width;
    private int height;

    public JPanelSuppilers(int width,int height) {
        this.width = width;
        this.height = height;
        suppilers();
    }

    /**
     * 实现账单管理界面
     */
    public void suppilers() {
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
        JLabel jlName = new JLabel("供应商名称：");
        JTextField jtName = new JTextField(10);
        JButton jbSelect = new JButton("查询");
        head.add(jlName);
        head.add(jtName);
        head.add(jbSelect);
        add(head);

        /**
         * 查询功能实现
         */
        jbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(new SuppilersTableModel(jtName.getText(), head));
            }
        });

        /**
         * 导入增删改容器
         */
        JPanel function = new JPanel(new GridLayout(1,6));
        function.setBounds(0,(int) (height/5.0*0.35), (int) (width/4.0*3.2), (int) (height/5.0*0.5));
        JLabel suppiler = new JLabel("供应商管理>>");
        suppiler.setFont(new Font("宋体", Font.BOLD, 20));
        JButton jbInput = new ButtonUtil().input();
        function.add(suppiler);
        function.add(jbInput);
        function.add(new JPanel());
        JButton jbAdd = new ButtonUtil().add();
        function.add(jbAdd);
        JButton jbUpdate = new ButtonUtil().update();
        function.add(jbUpdate);
        JButton jbDelete = new ButtonUtil().delete();
        function.add(jbDelete);
        add(function);
        /**
         * 导入
         */
        jbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("文件导入");
                if (JFileChooser.APPROVE_OPTION == jFileChooser.showOpenDialog(function)) {
                    File f = jFileChooser.getSelectedFile();
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader(f));
                        String str = "";
                        SuppilerBean suppilerBean = new SuppilerBean();
                        while ((str = reader.readLine()) != null){
                            String[] srr = str.split("[|]");
                            System.out.println(srr.length);
                            System.out.println(srr[0]);
                            suppilerBean.setS_id(Integer.parseInt(srr[0]));
                            suppilerBean.setS_name(srr[1]);
                            suppilerBean.setS_info(srr[2]);
                            suppilerBean.setS_linkman(srr[3]);
                            // 导入的电话校验
                            if (MyUtil.isPhone(srr[4]) ) {
                                suppilerBean.setS_phone(srr[4]);
                            }
                            suppilerBean.setS_address(srr[5]);
                            try {
                                sbs.addSuppilerBean(suppilerBean);
                            } catch (MyException e1) {
                                JOptionPane.showMessageDialog(function, e1.getMessage());
                            }
                        }
                        jTable.setModel(new SuppilersTableModel(null, function));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {
                        if (reader != null){
                            try {
                                reader.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }

            }
        });

        /**
         *增加功能实现
         */
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuppilersAdd().addUI(jTable);
            }
        });
        /**
         * 修改功能实现
         */
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

                new SuppilersUpdate().update(jTable, id);
            }
        });
        /**
         * 删除功能
         */
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
                if (JOptionPane.showConfirmDialog(function, "是否确定要删除选择行", "删除", JOptionPane.YES_NO_OPTION) == 0 ){
                    int[] rows = jTable.getSelectedRows();
                    for (int i = 0; i < rows.length; i++) {
                        int rowId = (int) jTable.getValueAt(rows[i], 0);
                        try {
                            sbs.deleteSuppilerBeanById(rowId);
                            JOptionPane.showMessageDialog(function,
                                    "删除成功！");
                            //删除完成之后重新加载表格
                            jTable.setModel(new SuppilersTableModel(null, function));
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
        jTable.setModel(new SuppilersTableModel(null, head));
        table.add(jsp);
        add(table);
    }

}
