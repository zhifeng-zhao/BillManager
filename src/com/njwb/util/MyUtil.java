package com.njwb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class MyUtil {
    /**
     *  预编译正则表达式
     */
    private static final Pattern NUMBER = Pattern.compile("[0-9]*");
    private static final Pattern DATE = Pattern.compile("^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$");
    private static final Pattern PHONE = Pattern.compile("1[0-9]{10}");
    private static final Pattern PRICE = Pattern.compile("[0-9]*(\\.?)[0-9]*");

    /**
     * 判断空校验
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 利用正则表达式判断是否为纯数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if (isEmpty(str)) {
            return false;
        }
        // 创建匹配器与对应的正则表达式匹配
        Matcher isNum = NUMBER.matcher(str);
        // 不为数字进入if
        if( isNum.matches() ){
            return true;
        }
        return false;
    }

    /**
     * 利用正则判断日期格式
     * @param date
     * @return
     */
    public static boolean isDate(String date){
        if (isEmpty(date)) {
            return false;
        }
        Matcher isDate = DATE.matcher(date);
        if (isDate.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 电话格式校验
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        Matcher isPhone = PHONE.matcher(phone);
        // 格式
        if (isPhone.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 价格校验
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Matcher isDecimal = PRICE.matcher(str);
        if (isDecimal.matches()) {
            return true;
        }
        return false;
    }

}
