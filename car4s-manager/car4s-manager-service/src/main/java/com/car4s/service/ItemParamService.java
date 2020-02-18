package com.car4s.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.pojo.TbItemParam;

/**
 * Created by 张少强
 */
public interface ItemParamService {
    Car4sResult getItemParamByCid(Long itemCatId);
    Car4sResult insertItemParam(TbItemParam itemParam);
}
