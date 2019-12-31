package com.njwb.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象工厂类
 */
public class ObjectFactory {
    public static Map<String, Object> objectMap = new HashMap<String, Object>();

    static {
        BufferedReader br = null;
        /**
         * 通过类加载器getClassLoader()读取
         */
        br = new BufferedReader(
                new InputStreamReader(
                        ObjectFactory.class.getClassLoader().getResourceAsStream("object")));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String[] srr = line.split("=");
                String key = srr[0];
                Object value = Class.forName(srr[1]).newInstance();
                objectMap.put(key, value);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
