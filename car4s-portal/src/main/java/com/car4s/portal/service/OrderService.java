package com.car4s.portal.service;

import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.portal.pojo.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */

public interface OrderService {
     String createOrder(Order order);
     List<TbOrderItem> getOrderList(HttpServletRequest request, HttpServletResponse response);
}
