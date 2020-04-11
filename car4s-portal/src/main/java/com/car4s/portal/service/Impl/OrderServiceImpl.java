package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.CookieUtil;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbItem;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.pojo.Order;
import com.car4s.portal.service.OrderService;
import com.car4s.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Value("${ORDER_SHOW_URL}")
    private String ORDER_SHOW_URL;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public String createOrder(Order order) {
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtil.objectToJson(order));
        Car4sResult car4sResult = Car4sResult.format(json);
        if (car4sResult.getStatus() == 200) {
            Object orderId = car4sResult.getData();
            return orderId.toString();
        }
        return "";
    }

    @Override
    public List<TbOrderItem> getOrderList(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_SHOW_URL, JsonUtil.objectToJson(user));
        Car4sResult car4sResult = Car4sResult.formatToList(json, TbOrderItem.class);
        if (car4sResult.getStatus() == 200) {
            List<TbOrderItem> orderItemList = (List<TbOrderItem>) car4sResult.getData();
            return orderItemList;
        }
        return null;
    }
}
