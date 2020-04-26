package com.car4s.order.service.impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.mapper.TbOrderItemMapper;
import com.car4s.generator.mapper.TbOrderMapper;
import com.car4s.generator.mapper.TbOrderShippingMapper;
import com.car4s.generator.pojo.*;
import com.car4s.order.dao.JedisClient;
import com.car4s.order.pojo.Order;
import com.car4s.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;

    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;

    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;


    @Override
    public Car4sResult createOrder(Order order, List<TbOrderItem> itemList) {
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
        int sum = 0;
        for (TbOrderItem orderItem : itemList) {
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId+"");
            orderItem.setOrderId(orderId+"");
            orderItemMapper.insert(orderItem);
            sum+=orderItem.getPrice();
        }
        order.setPayment(sum+"");
        orderMapper.updateByPrimaryKey(order);
        return Car4sResult.ok(orderId);
    }

    @Override
    public Car4sResult showOrder(TbUser user) {
        TbOrderExample tbOrderExample = new TbOrderExample();
        TbOrderExample.Criteria orderExampleCriteria = tbOrderExample.createCriteria();
        orderExampleCriteria.andUserIdEqualTo(user.getId());
        List<TbOrder> orders = orderMapper.selectByExample(tbOrderExample);
        List<TbOrderItem> tbOrderItems = new ArrayList<>();
        for (TbOrder order : orders) {
            TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
            TbOrderItemExample.Criteria tbOrderItemExampleCriteria = tbOrderItemExample.createCriteria();
            tbOrderItemExampleCriteria.andOrderIdEqualTo(order.getOrderId());
            List<TbOrderItem> tbOrderItems1 = orderItemMapper.selectByExample(tbOrderItemExample);
            for (TbOrderItem tbOrderItem : tbOrderItems1) {
                tbOrderItems.add(tbOrderItem);
            }
        }
        return Car4sResult.ok(tbOrderItems);
    }
}
