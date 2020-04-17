package com.car4s.rest.service;

import com.car4s.common.pojo.Car4sResult;

/**
 * Created by 张少强
 */
public interface ItemService {
    //获取基本信息
    Car4sResult getItemBaseInfo(long itemId);

    //获取描述信息
    Car4sResult getItemDescInfo(long itemId);

    //获取规格参数
    Car4sResult getItemParamInfo(long itemId);

    //全部车辆
    Car4sResult getAllItem();
}
