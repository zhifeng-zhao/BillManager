package com.njwb.ui.jframe;

import com.njwb.entity.AccountBean;
import com.njwb.entity.SuppilerBean;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.AccountBeanService;
import com.njwb.service.SuppilerBeanService;
import com.njwb.ui.model.BillTableModel;
import com.njwb.util.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BillUpdate {
    private SuppilerBeanService sbs = (SuppilerBeanService) ObjectFactory.objectMap.get("SuppilerBeanService");
    private AccountBeanService abs = (AccountBeanService) ObjectFactory.objectMap.get("AccountBeanService");
    private AccountBean accountBean = new AccountBean();
    private AccountBean oldBean = new AccountBean();
    public void update(JTable jTable, int id) {
        try {
            oldBean = abs.queryById(id);
        } catch (MyException e) {
            e.printStackTrace();
        }
        JFrame jFrame = new JFrame("修改商品");
        JPanel jPanel = new JPanel(new GridLayout(9,2));

        JPanel jp1 = new JPanel();
        JLabel star1 = star();
        JLabel jlName = new JLabel("商品名称：");
        JTextField jtName = new JTextField(20);
        jtName.setText(oldBean.getA_name());
        JPanel jp12 = new JPanel();
        jp1.add(star1);
        jp1.add(jlName);
        jPanel.add(jp1);
        jp12.add(jtName);
        jPanel.add(jp12);


        JLabel star2 = star();
        JLabel jlNums = new JLabel("交易数量：");
        JPanel jp2 = new JPanel();
        JTextField jtNums = new JTextField(20);
        String nums = String.valueOf(oldBean.getA_nums());
        jtNums.setText(nums);
        JPanel jp22 = new JPanel();
        jp2.add(star2);
        jp2.add(jlNums);
        jp22.add(jtNums);
        jPanel.add(jp2);
        jPanel.add(jp22);

        JPanel jp3 = new JPanel();
        JPanel jp32 = new JPanel();
        JLabel star3 = star();
        JLabel jlUnit = new JLabel("交易单位：");
        jp3.add(star3);
        jp3.add(jlUnit);
        JTextField jtUnit = new JTextField(20);
        jtUnit.setText(oldBean.getA_unit());
        jp32.add(jtUnit);
        jPanel.add(jp3);
        jPanel.add(jp32);

        JPanel jp4 = new JPanel();
        JLabel star4  = star();
        jp4.add(star4);
        JLabel jlAmount = new JLabel("交易金额：");
        jp4.add(jlAmount);
        jPanel.add(jp4);
        JPanel jp42 = new JPanel();
        JTextField jtAmount = new JTextField(20);
        String amount = String.valueOf(oldBean.getA_amount());
        jtAmount.setText(amount);
        jp42.add(jtAmount);
        jPanel.add(jp42);

        JPanel jp5 = new JPanel();
        JLabel star5 = star();
        jp5.add(star5);
        JLabel jlIsPay = new JLabel("是否付款：");
        jp5.add(jlIsPay);
        jPanel.add(jp5);
        JPanel jp52 = new JPanel();
        String[] srr = {"否","是"};
        JComboBox<String> jComboBox = new JComboBox<String>(srr);
        jComboBox.setSelectedIndex(oldBean.getA_ispayed());
        jp52.add(jComboBox);
        jPanel.add(jp52);

        JPanel jp6 = new JPanel();
        JPanel jp62 = new JPanel();
        jPanel.add(jp6);
        jPanel.add(jp62);
        JLabel star6 = star();
        jp6.add(star6);
        JLabel jlSName = new JLabel("所属供应商：");
        jp6.add(jlSName);
        String[] sName = null;
        int index = 0;
        try {
            List<SuppilerBean> list = sbs.queryAll();
            sName = new String[list.size()];
            int i = 0;
            for (SuppilerBean sb:list
            ) {
                sName[i] = sb.getS_name();
                if (sName[i].equals(oldBean.getS_name())) {
                    index = i;
                }
                i++;
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        JComboBox<String> jComboBox1 = new JComboBox<String>(sName);
        jComboBox1.setSelectedIndex(index);
        jp62.add(jComboBox1);

        JLabel jlInfo = new JLabel(" 商品描述：");
        JPanel jp7 = new JPanel();
        jp7.add(jlInfo);
        jPanel.add(jp7);
        JTextArea jta = new JTextArea(4,20);
        jta.setText(oldBean.getA_Info());
        JScrollPane jsp = new JScrollPane(jta);
        JPanel jp72 = new JPanel();
        jp72.add(jsp);
        jPanel.add(jp72);

        JPanel jp8 = new JPanel();
        JLabel star8 = star();
        jp8.add(star8);
        JLabel jlTime = new JLabel("交易时间：");
        jp8.add(jlTime);
        JTextField jtTime = new JTextField(20);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jtTime.setText(sdf.format(oldBean.getA_Date()));
        JPanel jp82 = new JPanel();
        jp82.add(jtTime);
        jPanel.add(jp8);
        jPanel.add(jp82);

        JButton jbAdd = new JButton("修改");
        JPanel jpAdd = new JPanel();
        jpAdd.add(jbAdd);
        jPanel.add(jpAdd);
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加修改后的数据
                accountBean.setA_id(id);
                accountBean.setA_name(jtName.getText().trim());
                // 判断修改后数量格式
                if (MyUtil.isNumber(jtNums.getText().trim())) {
                    accountBean.setA_nums(Integer.parseInt(jtNums.getText().trim()));
                } else {
                    try {
                        throw new MyException("输入的数量不能为空且必须为纯数字");
                    } catch (MyException e1) {
                        JOptionPane.showMessageDialog(jPanel, e1.getMessage());
                    }
                }
                accountBean.setA_unit(jtUnit.getText().trim());
                // 判断修改后价格格式
                if (MyUtil.isDecimal(jtAmount.getText().trim())) {
                    accountBean.setA_amount(Double.parseDouble(jtAmount.getText().trim()));
                } else {
                    try {
                        throw new MyException("输入的金额不能为空且必须为纯数字");
                    } catch (MyException e1) {
                        JOptionPane.showMessageDialog(jPanel, e1.getMessage());
                    }
                }
                accountBean.setA_ispayed(jComboBox.getSelectedIndex());
                try {
                    SuppilerBean suppilerBean = sbs.accurateQueryByName(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
                    accountBean.setS_id(suppilerBean.getS_id());
                    accountBean.setA_Info(jta.getText());
                    // 判断日期格式
                    if (!MyUtil.isEmpty(jtTime.getText().trim())) {
                        //解析日期格式
                        String time = jtTime.getText().trim();
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        accountBean.setA_Date(sdf.parse(time));
                    } else if (MyUtil.isEmpty(jtTime.getText().trim())) {
                        long time = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        accountBean.setA_Date(sdf.parse(sdf.format(time)));
                    } else {

                        throw new MyException("日期格式不对");
                    }
                    // 调用service层更新数据
                    abs.updateById(accountBean);
                    JOptionPane.showMessageDialog(jFrame, "修改成功");
                    jFrame.dispose();
                    jTable.setModel(new BillTableModel(null, 2));
                } catch (ParseException e1) {
                    e1.printStackTrace();
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
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jFrame.setSize(500,500);

    }

    public JLabel star() {
        JLabel star = new JLabel("*");
        star.setForeground(Color.RED);
        return star;
    }
}
