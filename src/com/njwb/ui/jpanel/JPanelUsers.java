package com.njwb.ui.jpanel;

import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;
import com.njwb.ui.model.UserTableModel;
import com.njwb.ui.jframe.UserAdd;
import com.njwb.ui.jframe.UserUpdate;
import com.njwb.util.ButtonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelUsers extends JPanel {

    private UserBeanService userBeanService = (UserBeanService) ObjectFactory.objectMap.get("UserBeanService");

    private int width;
    private int height;

    public JPanelUsers(int width, int height,UserBean nowUser) {
        this.width = width;
        this.height = height;
        users(nowUser);
    }

    public void users(UserBean nowUser) {
        setLayout(null);

        //建立空表格
        JTable jTable = new JTable();
        JScrollPane jsp = new JScrollPane(jTable);

        /**
         * 头部容器
         */
        JPanel head = new JPanel();
        //设置背景颜色
        head.setBackground(Color.CYAN);
        //设置容器位置
        head.setBounds(0,0, (int) (width/4.0*3.2), (int) (height/5.0*0.35));
        JLabel jlName = new JLabel("用户名称：");
        head.add(jlName);
        JTextField jtName = new JTextField(10);
        head.add(jtName);
        JButton jbSelect = new JButton("查询");
        head.add(jbSelect);
        add(head);

        /**
         * 查询功能实现
         */
        jbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(new UserTableModel(jtName.getText(), head));
            }
        });

        /**
         * 增删改容器
         */
        JPanel function = new JPanel(new GridLayout(1,4));
        //设置容器位置
        function.setBounds(0,(int) (height/5.0*0.35), (int) (width/4.0*3.2), (int) (height/5.0*0.5));
        JLabel suppiler = new JLabel("用户管理>>");
        suppiler.setFont(new Font("宋体", Font.BOLD, 20));
        function.add(suppiler);
        function.add(new JPanel());
        JButton jbAdd = new ButtonUtil().add();
        JButton jbUpdate = new ButtonUtil().update();
        function.add(jbAdd);
        function.add(jbUpdate);
        JButton jbDelete = new ButtonUtil().delete();
        function.add(jbDelete);
        add(function);

        /**
         *增加功能实现
         */
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserAdd().add(nowUser, jTable);
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

                //获取当前要修改的行
                int row = jTable.getSelectedRow();
                int id = (int) jTable.getValueAt(row, 0);

                //获取要修改的对象
                UserBean updateUser = null;
                try {
                    updateUser = userBeanService.queryUserById(id);
                } catch (MyException e1) {
                    JOptionPane.showMessageDialog(function, e1.getMessage());
                }

                // 获取当前对象权限
                String nowAuth = nowUser.getU_auth();
                //获得未修改前的权限
                String oldAuth = updateUser.getU_auth();

                // 判断用户是否有权限修改
                if (nowUser.getU_id() == updateUser.getU_id()) {
                    new UserUpdate().update(nowUser, jTable, updateUser);
                } else if (nowAuth.equals(oldAuth)) {
                    JOptionPane.showMessageDialog(function, "同级间不能修改");
                } else if ("部门经理".equals(nowAuth) && "管理员".equals(oldAuth)) {
                    JOptionPane.showMessageDialog(function, "不能修改上级");
                } else {
                    /**
                     * 传入当前界面的用户,表格,要修改的对象
                     */
                    new UserUpdate().update(nowUser, jTable, updateUser);
                }
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
                            System.out.println(rowId);
                            userBeanService.deleteUserBeanById(nowUser,rowId);
//                            删除完成之后重新加载表格
                            JOptionPane.showMessageDialog(function, "删除成功！");
                            jTable.setModel(new UserTableModel(null, function));
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
        //初始化表格
        jTable.setModel(new UserTableModel(null, table));
        table.setBounds(0,(int) (height/5.0*0.85), (int) (width/4.0*3.2), (int) (height));
        table.add(jsp);
        add(table);
    }
}
