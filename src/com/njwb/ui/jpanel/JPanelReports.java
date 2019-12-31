package com.njwb.ui.jpanel;

import com.njwb.ui.model.ShopModel;
import com.njwb.ui.model.SuppilerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelReports extends JPanel {
    private int width;
    private int height;


    public JPanelReports(int width,int height) {
        this.width = width;
        this.height = height;
        reports();
    }
    public void reports() {
        //将当前容器设为绝对布局

        setLayout(null);
        //按钮容器,布局为网格布局
        JPanel button = new JPanel(new GridLayout(1,4));
        //设置按钮容器位置
        button.setBounds(0,0, (int) (width/4.0*3.2), (int) (height/5.0*0.85));
        //将按钮添加到主容器
        add(button);

        //建立空表格
        JTable jTable = new JTable();
        //加入滚动条显示列名
        JScrollPane jsp = new JScrollPane(jTable);

        button.add(new JPanel());
        //商品分组查询按钮
        JRadioButton shop = new JRadioButton("商品分组查询");
        //供应商分组查询按钮
        JRadioButton suppiler = new JRadioButton("供应商分组查询", true);
        //将当选按钮设置为同组
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(shop);
        buttonGroup.add(suppiler);
        //将分组查询按钮添加到按钮容器
        button.add(shop);
        button.add(suppiler);
        button.add(new JPanel());
        //商品按钮实现
        shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(new ShopModel());
            }
        });
        //供应商按钮实现
        suppiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTable.setModel(new SuppilerModel());
            }
        });


        //表格容器，布局为网格布局
        JPanel table = new JPanel(new GridLayout());
        //设置表格容器位置
        table.setBounds(0,(int) (height/5.0*0.85), (int) (width/4.0*3.2), (int) (height/5.0*3.0));
        //将表格容器添加到主容器
        add(table);
        //将供应商表设为默认
        jTable.setModel(new SuppilerModel());
        //将表格加入到表格容器
        table.add(jsp);
    }


}
