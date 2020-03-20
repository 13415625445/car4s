package com.car4s.portal.service;

import com.car4s.portal.pojo.Order;
import org.springframework.stereotype.Service;

/**
 * Created by 张少强
 */
@Service
public interface OrderService {
    public String createOrder(Order order);
}
