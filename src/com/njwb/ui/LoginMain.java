package com.njwb.ui;

import com.njwb.dao.rowsmapper.impl.UserBeanRowsMapperImpl;
import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMain {

    private UserBeanService ubs = (UserBeanService)ObjectFactory.objectMap.get("UserBeanService");

    private UserBean ub = (UserBean)ObjectFactory.objectMap.get("UserBean");

    public static void main(String[] args) {
        new LoginMain().show();
    }

    /**
     * 登陆主界面
     */
    public void show (){
        /**
         * 页面设计
         */
        JFrame jf = new JFrame();
        JPanel jp = new JPanel(new GridLayout(2,1));
        jp.setLayout(null);

        JLabel jname = new JLabel("姓名：");
        JTextField jtname = new JTextField(10);
        jp.add(jname);
        jp.add(jtname);
        jname.setBounds(180,165,50,40);
        jtname.setBounds(250,175,140,20);

        JLabel jpwd = new JLabel("密码：");
        JPasswordField jtpwd = new JPasswordField(10);
        jp.add(jpwd);
        jp.add(jtpwd);
        jpwd.setBounds(180,195,50,30);
        jtpwd.setBounds(250,200,140,20);

        JLabel jauth = new JLabel("身份：");
        String[] srr = {"普通员工", "部门经理","管理员"};
        JComboBox<String> jcb = new JComboBox<String>(srr);
        jp.add(jauth);
        jp.add(jcb);
        jauth.setBounds(180,220,50,30);
        jcb.setBounds(250,225,140,20);

        JButton jblogin = new JButton("登陆系统");
        JButton jbreset = new JButton("重置");
        jp.add(jblogin);
        jp.add(jbreset);
        jblogin.setBounds(180,255,90,25);
        jbreset.setBounds(300,255,90,25);

        /**
         * 实现登陆功能
         */
        jblogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserBean userBean = null;
                ub.setU_name(jtname.getText());
                ub.setU_password(new String(jtpwd.getPassword()));
                ub.setU_auth((String)jcb.getSelectedItem());
                try {
                    //接收当前登陆的用户
                    userBean = ubs.queryUserBeanByName(ub);
                    JOptionPane.showMessageDialog(jf,"登陆成功");
                    jf.dispose();
                    //传入当前用户类给下个界面
                    new MainFrame().show(userBean);
                } catch (MyException e1) {
                    JOptionPane.showMessageDialog(jf,e1.getMessage());
                }
            }
        });

        /**
         * 重置
         */
        jbreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtname.setText("");
                jtpwd.setText("");
                jcb.setSelectedIndex(0);
            }
        });
        /**
         * 背景设为透明
         */
        jp.setOpaque(false);
        ((JComponent)jf.getContentPane()).setOpaque(false);

        /**
         * 设置背景图片
         */
        ImageIcon img = new ImageIcon("images/login_box.jpg");
        JLabel jl = new JLabel(img);
        jf.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
        jl.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

        jf.add(jp);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
        jf.setSize(img.getIconWidth(), img.getIconHeight());
    }
}
