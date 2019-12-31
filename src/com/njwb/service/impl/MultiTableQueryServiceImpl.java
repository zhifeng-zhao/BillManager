package com.njwb.service.impl;

import com.njwb.dao.MultiTableQueryDao;
import com.njwb.entity.ShopReport;
import com.njwb.entity.SuppilerReport;
import com.njwb.factory.ObjectFactory;
import com.njwb.myexception.MyException;
import com.njwb.service.MultiTableQueryService;

import java.sql.SQLException;
import java.util.List;

public class MultiTableQueryServiceImpl implements MultiTableQueryService {

    private MultiTableQueryDao multiTableQueryDao = (MultiTableQueryDao) ObjectFactory.objectMap.get("MultiTableQueryDao");

    /**
     * 商品分表查询
     * @return
     * @throws MyException
     */
    @Override
    public List<ShopReport> queryShop() throws MyException {
        List<ShopReport> list = null;
        try {
            list = multiTableQueryDao.selectShop();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 供应商分组查询
     * @return
     * @throws MyException
     */
    @Override
    public List<SuppilerReport> querySuppiler() throws MyException {
        List<SuppilerReport> list = null;
        try {
            list = multiTableQueryDao.selectSuppiler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
