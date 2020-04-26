package com.car4s.order.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbUser;
import com.car4s.order.pojo.Order;

import java.util.List;

/**
 * Created by 张少强
 */
public interface OrderService {
    Car4sResult createOrder(Order order, List<TbOrderItem> itemList);

    Car4sResult showOrder(TbUser user);
}
