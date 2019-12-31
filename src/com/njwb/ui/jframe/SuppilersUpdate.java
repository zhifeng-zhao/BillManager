package com.njwb.ui.jframe;

import com.njwb.entity.SuppilerBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.SuppilerBeanService;
import com.njwb.ui.model.SuppilersTableModel;
import com.njwb.util.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuppilersUpdate {
    private SuppilerBeanService sbs = (SuppilerBeanService) ObjectFactory.objectMap.get("SuppilerBeanService");
    private SuppilerBean oldBean = new SuppilerBean();
    private SuppilerBean newBean = new SuppilerBean();
    public void update(JTable jTable, int id) {
        try {
            oldBean = sbs.queryById(id);
        } catch (MyException e) {
            e.printStackTrace();
        }
        JFrame jFrame = new JFrame("修改供应商");
        JPanel jPanel = new JPanel(new GridLayout(7,2));

        JLabel star1 = star();
        JLabel jlName = new JLabel("供应商名称：");
        JPanel jp1 = new JPanel();
        jp1.add(star1);
        jp1.add(jlName);
        jPanel.add(jp1);
        JTextField jtName = new JTextField(20);
        jtName.setText(oldBean.getS_name());
        JPanel jp12 = new JPanel();
        jp12.add(jtName);
        jPanel.add(jp12);

        JLabel jlInfo = new JLabel(" 供应商描述：");
        JPanel jp2 = new JPanel();
        jp2.add(jlInfo);
        jPanel.add(jp2);
        JTextArea jta = new JTextArea(4,20);
        jta.setText(oldBean.getS_info());
        JScrollPane jsp = new JScrollPane(jta);
        JPanel jp22 = new JPanel();
        jp22.add(jsp);
        jPanel.add(jp22);

        JLabel star2 = star();
        JLabel jlLink = new JLabel("供应商联系人：");
        JPanel jp3 = new JPanel();
        JTextField jtLink = new JTextField(20);
        jtLink.setText(oldBean.getS_linkman());
        JPanel jp32 = new JPanel();
        jp3.add(star2);
        jp3.add(jlLink);
        jp32.add(jtLink);
        jPanel.add(jp3);
        jPanel.add(jp32);

        JPanel jp4 = new JPanel();
        JPanel jp42 = new JPanel();
        JLabel star3 = star();
        JLabel jlPhone = new JLabel("供应商电话");
        jp4.add(star3);
        jp4.add(jlPhone);
        JTextField jtPhone = new JTextField(20);
        jtPhone.setText(oldBean.getS_phone());
        jp42.add(jtPhone);
        jPanel.add(jp4);
        jPanel.add(jp42);

        JPanel jp5 = new JPanel();
        JLabel jlFaxes = new JLabel("供应商传真");
        jp5.add(jlFaxes);
        jPanel.add(jp5);
        JPanel jp52 = new JPanel();
        JTextField jtFaxes = new JTextField(20);
        jtFaxes.setText(oldBean.getS_faxes());
        jp52.add(jtFaxes);
        jPanel.add(jp52);

        JPanel jp6 = new JPanel();
        JPanel jp62 = new JPanel();
        jPanel.add(jp6);
        jPanel.add(jp62);
        JLabel star4 = star();
        jp6.add(star4);
        JLabel jlAddr = new JLabel("供应商地址");
        jp6.add(jlAddr);
        JTextField jtAddr = new JTextField(20);
        jtAddr.setText(oldBean.getS_address());
        jp62.add(jtAddr);

        JButton jbAdd = new JButton("修改");
        JPanel jpAdd = new JPanel();
        jpAdd.add(jbAdd);
        jPanel.add(jpAdd);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newBean.setS_id(id);
                newBean.setS_name(jtName.getText().trim());
                newBean.setS_linkman(jtLink.getText().trim());
                newBean.setS_info(jta.getText().trim());
                if (MyUtil.isPhone(jtPhone.getText().trim()) ) {
                    newBean.setS_phone(jtPhone.getText().trim());
                }
                newBean.setS_address(jtAddr.getText().trim());
                newBean.setS_faxes(jtFaxes.getText().trim());
                try {
                    sbs.updateSuppilerBeanByid(oldBean.getS_name(), newBean);
                    JOptionPane.showMessageDialog(jFrame, "修改成功");
                    jFrame.dispose();
                    jTable.setModel(new SuppilersTableModel(null, jPanel));
                } catch (MyException e1) {
                    JOptionPane.showMessageDialog(jFrame, e1.getMessage());
                }
            }
        });

        JButton jbClose = new JButton("关闭");
        JPanel jpClose = new JPanel();
        jpClose.add(jbClose);
        jPanel.add(jpClose);
        /**
         * 实现关闭
         */
        jbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });


        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(500,500);

    }

    public JLabel star() {
        JLabel star = new JLabel("*");
        star.setForeground(Color.RED);
        return star;
    }

}
