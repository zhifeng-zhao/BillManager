package com.njwb.service;

import com.njwb.entity.ShopReport;
import com.njwb.entity.SuppilerReport;
import com.njwb.myexception.MyException;

import java.util.List;

public interface MultiTableQueryService {
    /**
     * 商品分组查询
     * @return
     * @throws MyException
     */
    public abstract List<ShopReport> queryShop() throws MyException;

    /**
     * 供应商分组查询
     * @return
     * @throws MyException
     */
    public abstract List<SuppilerReport> querySuppiler() throws MyException;
}
