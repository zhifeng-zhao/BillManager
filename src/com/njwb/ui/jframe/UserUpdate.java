package com.njwb.ui.jframe;

import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;
import com.njwb.ui.LoginMain;
import com.njwb.ui.MainFrame;
import com.njwb.ui.model.UserTableModel;
import com.njwb.util.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUpdate {
    private UserBeanService userBeanService = (UserBeanService) ObjectFactory.objectMap.get("UserBeanService");

    public void update(UserBean nowUser, JTable jTable, UserBean updateUser) {
        //主容器
        JFrame jFrame = new JFrame("修改用户");
        JPanel jPanel = new JPanel(new GridLayout(9,2));

        //用户名称
        JPanel jp1 = new JPanel();
        JPanel jp12 = new JPanel();
        JLabel star1 = star();
        JLabel jlName = new JLabel("用户名称：");
        jp1.add(star1);
        jp1.add(jlName);
        JTextField jtName = new JTextField(20);
        jtName.setText(updateUser.getU_name());
        jp12.add(jtName);
        jPanel.add(jp1);
        jPanel.add(jp12);

        //用户密码
        JPanel jp2 = new JPanel();
        jPanel.add(jp2);
        JLabel star2 = star();
        JLabel jlPwd = new JLabel("用户密码：");
        jp2.add(star2);
        jp2.add(jlPwd);
        JPanel jp22 = new JPanel();
        JPasswordField jfPwd = new JPasswordField(20);
        jfPwd.setText(updateUser.getU_password());
        jp22.add(jfPwd);
        jPanel.add(jp22);

        //确认密码
        JPanel jp3 = new JPanel();
        JPanel jp32 = new JPanel();
        JLabel star3 = star();
        JLabel jlConfirm = new JLabel("确认密码：");
        JPasswordField jfConfirm = new JPasswordField(20);
        jfConfirm.setText(updateUser.getU_password());
        jp3.add(star3);
        jp3.add(jlConfirm);
        jp32.add(jfConfirm);
        jPanel.add(jp3);
        jPanel.add(jp32);

        //用户性别
        JPanel jp4 = new JPanel();
        JLabel star4 = star();
        jp4.add(star4);
        JLabel jlSex = new JLabel("用户性别：");
        jp4.add(jlSex);
        String[] sex = {"男", "女"};
        JComboBox jcbSex = new JComboBox(sex);
        if ("男".equals(updateUser.getU_gender())){
            jcbSex.setSelectedIndex(0);
        } else if ("女".equals(updateUser.getU_gender())) {
            jcbSex.setSelectedIndex(1);
        }
        JPanel jp42 = new JPanel();
        jp42.add(jcbSex);
        jPanel.add(jp4);
        jPanel.add(jp42);

        //用户年龄
        JPanel jp5 = new JPanel();
        JLabel star5 = star();
        JLabel jlAge = new JLabel("用户年龄：");
        JTextField jtAge = new JTextField(20);
        jp5.add(star5);
        jp5.add(jlAge);
        jPanel.add(jp5);
        jtAge.setText(String.valueOf(updateUser.getU_age()));
        JPanel jp52 = new JPanel();
        jp52.add(jtAge);
        jPanel.add(jp52);

        //用户电话
        JPanel jp6 = new JPanel();
        JLabel star6 = star();
        jp6.add(star6);
        JLabel jlPhone = new JLabel("用户电话：");
        jp6.add(jlPhone);
        JPanel jp62 = new JPanel();
        JTextField jtPhone = new JTextField(20);
        jtPhone.setText(updateUser.getU_phone());
        jp62.add(jtPhone);
        jPanel.add(jp6);
        jPanel.add(jp62);

        //用户地址
        JPanel jp7 = new JPanel();
        JLabel jlAddr = new JLabel("用户地址：");
        jp7.add(jlAddr);
        JTextArea jTextArea = new JTextArea(4,20);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextArea.setText(updateUser.getU_address());
        JPanel jp72 = new JPanel();
        jPanel.add(jp7);
        jp72.add(jScrollPane);
        jPanel.add(jp72);

        //用户权限
        JPanel jp8 = new JPanel();
        jPanel.add(jp8);
        JLabel star7 = star();
        jp8.add(star7);
        JLabel jlAuth = new JLabel("用户权限：");
        jp8.add(jlAuth);
        String[] auth = {"管理员", "部门经理","普通员工"};
        JComboBox jcbAuth = new JComboBox(auth);
        if ("管理员".equals(updateUser.getU_auth())) {
            jcbAuth.setSelectedIndex(0);
        }
        if ("部门经理".equals(updateUser.getU_auth())) {
            jcbAuth.setSelectedIndex(1);
            jcbAuth.setEnabled(false);
        }
        if ("普通员工".equals(updateUser.getU_auth())) {
            jcbAuth.setSelectedIndex(2);
            jcbAuth.setEnabled(false);
        }
        if ("管理员".equals(nowUser.getU_auth())) {
            jcbAuth.setEnabled(true);
        }

        JPanel jp82 = new JPanel();
        jp82.add(jcbAuth);
        jPanel.add(jp82);

        //添加按钮
        JButton jbAdd = new JButton("修改");
        JPanel jpAdd = new JPanel();
        jpAdd.add(jbAdd);
        jPanel.add(jpAdd);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new 一个对象接收新增的数据
                UserBean userBean = new UserBean();
                userBean.setU_id(updateUser.getU_id());
                userBean.setU_name(jtName.getText().trim());
                String pwd = new String(jfPwd.getPassword());
                userBean.setU_password(pwd);
                String confirm = new String(jfConfirm.getPassword());
                userBean.setU_gender(jcbSex.getSelectedItem().toString());
                // 年龄格式校验
                if (MyUtil.isNumber(jtAge.getText().trim())) {
                    userBean.setU_age(Integer.parseInt(jtAge.getText().trim()));
                } else {
                    try {
                        throw new MyException("输入的年龄不能为空且必须为纯数字");
                    } catch (MyException e1) {
                        JOptionPane.showMessageDialog(jPanel, e1.getMessage());
                    }
                }
                if (MyUtil.isPhone(jtPhone.getText().trim()) ) {
                    userBean.setU_phone(jtPhone.getText().trim());
                }
                userBean.setU_address(jTextArea.getText().trim());
                userBean.setU_auth(jcbAuth.getSelectedItem().toString());
                try {
                    userBeanService.updateUser(nowUser, userBean, confirm);
                    JOptionPane.showMessageDialog(jFrame,"修改成功!");
                    jFrame.dispose();
                    jTable.setModel(new UserTableModel(null,jPanel));
                    if (nowUser.getU_id() == userBean.getU_id()) {
                        if ("管理员".equals(nowUser.getU_auth()) && ("部门经理".equals(userBean.getU_auth()) || "普通员工".equals(userBean.getU_auth()))) {
                            new MainFrame().closeMain();
                            new LoginMain().show();
                        }
                    }
                } catch (MyException e1) {
                    JOptionPane.showMessageDialog(jFrame, e1.getMessage());
                }
            }

        });

        //关闭按钮
        JPanel jpClose = new JPanel();
        JButton jbClose = new JButton("关闭");
        jPanel.add(jpClose);
        jpClose.add(jbClose);
        /**
         * 实现关闭
         */
        jbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        jFrame.setSize(550,550);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
    }

    public JLabel star() {
        JLabel star = new JLabel("*");
        star.setForeground(Color.RED);
        return star;
    }

}
