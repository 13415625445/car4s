package com.car4s.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;

/**
 * Created by 张少强
 */
public interface OrderService {
    EUDataGridResult getOrderList(int page, int rows);
    Car4sResult delete(String orderId);
}
