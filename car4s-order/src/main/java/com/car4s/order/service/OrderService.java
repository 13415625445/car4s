package com.car4s.order.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.pojo.TbOrder;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 张少强
 */
public interface OrderService {
    Car4sResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
