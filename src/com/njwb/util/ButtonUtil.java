package com.njwb.util;

import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.awt.*;

/**
 * 按钮工具类
 */
public class ButtonUtil {

    /**
     * 导出按钮
     * @return
     */
    public JButton output() {
        JButton output = new JButton("导出");
        output.setBackground(Color.YELLOW);
        return output;
    }

    /**
     * 导入按钮
     * @return
     */
    public JButton input() {
        JButton input = new JButton("导入");
        input.setBackground(Color.YELLOW);
        return input;
    }
    /**
     * 添加数据按钮
     * @return
     */
    public JButton add() {
        JButton add = new JButton("添加数据");
        add.setBackground(Color.PINK);
        return add;
    }

    /**
     * 修改数据按钮
     * @return
     */
    public JButton update() {
        JButton update = new JButton("修改数据");
        update.setBackground(Color.PINK);
        return update;
    }

    /**
     * 删除数据按钮
     * @return
     */
    public JButton delete() {
        JButton delete = new JButton("删除数据");
        delete.setBackground(Color.PINK);
        return delete;
    }
}
