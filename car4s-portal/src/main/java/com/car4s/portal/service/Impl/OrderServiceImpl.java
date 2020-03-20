package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.portal.pojo.Order;
import com.car4s.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by 张少强
 */
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(Order order) {
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtil.objectToJson(order));
        Car4sResult taotaoResult = Car4sResult.format(json);
        if (taotaoResult.getStatus() == 200) {
            Object orderId = taotaoResult.getData();
            return orderId.toString();
        }
        return "";
    }
}
