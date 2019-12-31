package com.njwb.ui;


import com.njwb.entity.UserBean;
import com.njwb.ui.jpanel.JPanelBill;
import com.njwb.ui.jpanel.JPanelReports;
import com.njwb.ui.jpanel.JPanelSuppilers;
import com.njwb.ui.jpanel.JPanelUsers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


public class MainFrame {
    private JFrame jf;

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }
    // test
    public static void main(String[] args){
        UserBean ub = new UserBean();
        ub.setU_name("zzf");
        ub.setU_auth("管理员");
        new MainFrame().show(ub);
    }

    public void show(UserBean userBean) {
//        JFrame jf = new JFrame();
        setJf(new JFrame());
        JPanel jp = new JPanel(null);

        /**
         * 背景
         */
        ImageIcon img = new ImageIcon("images/MainFrame.png");
        JLabel jl = new JLabel(img);
        jf.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
        jl.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

        /**
         * 界面的宽高
         */
        int width = img.getIconWidth();
        int height =img.getIconHeight();

        /**
         * 时间界面
         */
        JPanel jpTime = new JPanel(new GridLayout(3,1));
        jpTime.setBounds((int) (width/4.0*2.0), (int) (height/5.0*0.5), (int) (width/3), (int) (height/5.0*0.35) );
        JLabel jlWelcome = new JLabel("欢迎您：");
        JLabel jlName = new JLabel("                     "+userBean.getU_name());
        JLabel jlTime = new JLabel();
        //实时获取当前时间
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long time = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jlTime.setText("                                                      "+sdf.format(time));
            }
        });
        timer.start();
        jpTime.add(jlWelcome);
        jpTime.add(jlName);
        jpTime.add(jlTime);
        jp.add(jpTime);

        /**
         * 欢迎界面
         */
        JPanel jpWelcome = new JPanel(new BorderLayout());
        JLabel jtext = new JLabel("欢迎使用超市管理系统！",JLabel.CENTER);
        Font fnt = new Font("斜体",Font.ITALIC,35);
        jtext.setFont(fnt);
        jpWelcome.add(jtext, BorderLayout.CENTER);
        jp.add(jpWelcome);
        jpWelcome.setBounds((int) (width/4.0*0.8), (int) (height/5.0*1.2), (int) (width/4.0*3.2), (int) (height/5.0*3.8));

        /**
         * 选择界面
         */
        GridLayout gl = new GridLayout(9,1);
        //设置垂直间距
        gl.setVgap(15);
        JPanel jpChoice =  new JPanel(gl);
        JButton jbBill = new JButton(new ImageIcon("images/btn_bill.gif"));
        jpChoice.add(jbBill);
        JButton jbSuppilers = new JButton(new ImageIcon("images/btn_suppliers.gif"));
        jpChoice.add(jbSuppilers);
        JButton jbUsers = new JButton(new ImageIcon("images/btn_users.gif"));
        jpChoice.add(jbUsers);
        JButton jbReports = new JButton(new ImageIcon("images/btn_reports.gif"));
        jpChoice.add(jbReports);
        JButton jbExit = new JButton(new ImageIcon("images/btn_exit.gif"));
        jpChoice.add(jbExit);
        jp.add(jpChoice);
        jpChoice.setBounds((int) (width/4.0*0.025), (int) (height/5.0*0.9), (int) (width/4.0*0.74), (int) (height/5.0*4.2));

        //账单按钮实现
        jbBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jpBill = new JPanelBill(width, height);
                refresh(jpWelcome, jpBill);
            }
        });

        //供应商按钮实现
        jbSuppilers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jpSuppilers = new JPanelSuppilers(width,height);
                refresh(jpWelcome, jpSuppilers);
            }
        });

        //用户按钮实现
        jbUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //只有管理员有权利修改用户
                if (!"普通员工".equals(userBean.getU_auth())) {
                    JPanel jpUsers = new JPanelUsers(width, height,userBean);
                    refresh(jpWelcome, jpUsers);
                } else {
                    refresh(jpWelcome, new JPanel());
                    JOptionPane.showMessageDialog(jp,"您没有权限操作用户管理！");
                }
            }
        });

        //报表按钮实现
        jbReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jpReports = new JPanelReports(width, height);
                refresh(jpWelcome, jpReports);
            }
        });

        //退出按钮实现
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //提示框是否退出系统
                int i = JOptionPane.showConfirmDialog(jf, "是否退出系统", "退出系统", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    System.exit(0);
                }
            }
        });

        //背景设为透明
        jp.setOpaque(false);
        ((JComponent)jf.getContentPane()).setOpaque(false);
        jpTime.setOpaque(false);

        jf.add(jp);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
        jf.setSize(img.getIconWidth(),img.getIconHeight());
    }

    /**
     * 更新ui界面
     * @param jPanel 主容器
     * @param newJPanel 新界面
     */
    public void refresh(JPanel jPanel, JPanel newJPanel) {
        jPanel.removeAll();
        jPanel.add(newJPanel);
        jPanel.updateUI();
    }

    public void closeMain() {
        getJf().dispose();
    }
}
