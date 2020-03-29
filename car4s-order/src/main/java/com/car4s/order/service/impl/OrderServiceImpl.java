package com.car4s.order.service.impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.mapper.TbOrderItemMapper;
import com.car4s.generator.mapper.TbOrderMapper;
import com.car4s.generator.mapper.TbOrderShippingMapper;
import com.car4s.generator.pojo.TbOrder;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbOrderShipping;
import com.car4s.order.dao.JedisClient;
import com.car4s.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;

    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;

    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Override
    public Car4sResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        String string = jedisClient.get(ORDER_GEN_KEY);
        if(StringUtils.isBlank(string)){
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        order.setOrderId(orderId+"");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //1.未确定预约 2.已确定预约 3.已受理 4.预约成功 5.试车成功 6,关闭预约
        order.setStatus(1);
        //0.未评价 1,已评价
        order.setBuyerRate(0);
        orderMapper.insert(order);
        for (TbOrderItem orderItem : itemList) {
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId+"");
            orderItem.setOrderId(orderId+"");
            orderItemMapper.insert(orderItem);
        }
        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);

        return Car4sResult.ok(orderId);
    }
}