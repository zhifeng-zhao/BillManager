package com.njwb.ui.jframe;

import com.njwb.entity.UserBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.UserBeanService;
import com.njwb.ui.model.UserTableModel;
import com.njwb.util.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户添加界面
 * @author zzf
 */
public class UserAdd {
    /**
     * 创建服务层的类
     */
    private UserBeanService userBeanService = (UserBeanService) ObjectFactory.objectMap.get("UserBeanService");

    public void add(UserBean nowUser, JTable jTable) {
        //主容器
        JFrame jFrame = new JFrame("添加用户");
        JPanel jPanel = new JPanel(new GridLayout(9,2));

        //用户名称
        JPanel jp1 = new JPanel();
        JLabel star1 = star();
        JLabel jlName = new JLabel("用户名称：");
        jp1.add(star1);
        jp1.add(jlName);
        jPanel.add(jp1);
        JPanel jp12 = new JPanel();
        JTextField jtName = new JTextField(20);
        jp12.add(jtName);
        jPanel.add(jp12);

        //用户密码
        JPanel jp2 = new JPanel();
        jPanel.add(jp2);
        JLabel star2 = star();
        JLabel jlPwd = new JLabel("用户密码：");
        jp2.add(star2);
        jp2.add(jlPwd);
        JPanel jp22 = new JPanel();
        jPanel.add(jp22);
        JPasswordField jfPwd = new JPasswordField(20);
        jp22.add(jfPwd);

        //确认密码
        JPanel jp3 = new JPanel();
        jPanel.add(jp3);
        JLabel star3 = star();
        JLabel jlConfirm = new JLabel("确认密码：");
        jp3.add(star3);
        jp3.add(jlConfirm);
        JPasswordField jfConfirm = new JPasswordField(20);
        JPanel jp32 = new JPanel();
        jp32.add(jfConfirm);
        jPanel.add(jp32);

        //用户性别
        JPanel jp4 = new JPanel();
        jPanel.add(jp4);
        JLabel star4 = star();
        JLabel jlSex = new JLabel("用户性别：");
        jp4.add(star4);
        jp4.add(jlSex);
        String[] sex = {"男", "女"};
        JComboBox jcbSex = new JComboBox(sex);
        JPanel jp42 = new JPanel();
        jp42.add(jcbSex);
        jPanel.add(jp42);

        //用户年龄
        JPanel jp5 = new JPanel();
        JLabel star5 = star();
        JLabel jlAge = new JLabel("用户年龄：");
        jp5.add(star5);
        jp5.add(jlAge);
        jPanel.add(jp5);
        JTextField jtAge = new JTextField(20);
        JPanel jp52 = new JPanel();
        jp52.add(jtAge);
        jPanel.add(jp52);

        //用户电话
        JPanel jp6 = new JPanel();
        jPanel.add(jp6);
        JLabel star6 = star();
        JLabel jlPhone = new JLabel("用户电话：");
        JPanel jp62 = new JPanel();
        jp6.add(star6);
        jp6.add(jlPhone);
        JTextField jtPhone = new JTextField(20);
        jp62.add(jtPhone);
        jPanel.add(jp62);

        //用户地址
        JPanel jp7 = new JPanel();
        JLabel jlAddr = new JLabel("用户地址：");
        jp7.add(jlAddr);
        JTextArea jTextArea = new JTextArea(4,20);
        JPanel jp72 = new JPanel();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jp72.add(jScrollPane);
        jPanel.add(jp7);
        jPanel.add(jp72);

        //用户权限
        JPanel jp8 = new JPanel();
        jPanel.add(jp8);
        JLabel star7 = star();
        JLabel jlAuth = new JLabel("用户权限：");
        jp8.add(star7);
        jp8.add(jlAuth);
        String[] auth = {"管理员", "部门经理","普通员工"};
        JComboBox jcbAuth = new JComboBox(auth);
        JPanel jp82 = new JPanel();
        jp82.add(jcbAuth);
        jPanel.add(jp82);

        //添加按钮
        JButton jbAdd = new JButton("添加");
        JPanel jpAdd = new JPanel();
        jpAdd.add(jbAdd);
        jPanel.add(jpAdd);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new 一个对象接收新增的数据
                UserBean userBean = new UserBean();
                userBean.setU_name(jtName.getText().trim());
                String pwd = new String(jfPwd.getPassword());
                String confirm = new String(jfConfirm.getPassword());
                userBean.setU_password(pwd);
                userBean.setU_gender(jcbSex.getSelectedItem().toString());
                // 年龄校验
                if (MyUtil.isNumber(jtAge.getText().trim())) {
                    userBean.setU_age(Integer.parseInt(jtAge.getText().trim()));
                } else {
                    try {
                        throw new MyException("输入的年龄不能为空且必须为纯数字");
                    } catch (MyException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(jPanel, e1.getMessage());
                    }
                }
                if (MyUtil.isPhone(jtPhone.getText().trim()) ) {
                    userBean.setU_phone(jtPhone.getText());
                }
                userBean.setU_address(jTextArea.getText());
                userBean.setU_auth(jcbAuth.getSelectedItem().toString());
                try {
                    userBeanService.addUserbean(nowUser,userBean,confirm);
                    JOptionPane.showMessageDialog(jFrame,"添加成功!");
                    jFrame.dispose();
                    jTable.setModel(new UserTableModel(null,jPanel));
                } catch (MyException e1) {
                    JOptionPane.showMessageDialog(jFrame, e1.getMessage());
                }
            }
        });

        //关闭按钮
        JPanel jpClose = new JPanel();
        jPanel.add(jpClose);
        JButton jbClose = new JButton("关闭");
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
