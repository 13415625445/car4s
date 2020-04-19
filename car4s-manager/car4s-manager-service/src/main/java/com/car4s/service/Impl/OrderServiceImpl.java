package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.mapper.TbOrderItemMapper;
import com.car4s.generator.mapper.TbOrderMapper;
import com.car4s.generator.mapper.TbUserMapper;
import com.car4s.generator.pojo.*;
import com.car4s.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Override
    public EUDataGridResult getOrderList(int page, int rows) {
        TbOrderExample orderExample = new TbOrderExample();
        PageHelper.startPage(page, rows);
        List<OrderInfo> list = new ArrayList<>();
        List<TbOrder> tbOrders = orderMapper.selectByExample(orderExample);
        for (TbOrder tbOrder : tbOrders) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(tbOrder.getOrderId());
            TbUser tbUser = userMapper.selectByPrimaryKey(tbOrder.getUserId());
            orderInfo.setPhone(tbUser.getPhone());
            orderInfo.setPersonName(tbUser.getUsername());
            TbOrderItemExample orderItemExample = new TbOrderItemExample();
            TbOrderItemExample.Criteria criteria = orderItemExample.createCriteria();
            criteria.andOrderIdEqualTo(tbOrder.getOrderId());
            List<TbOrderItem> tbOrderItems = orderItemMapper.selectByExample(orderItemExample);
            String carName = "";
            for (TbOrderItem tbOrderItem : tbOrderItems) {
                carName = carName.concat(tbOrderItem.getTitle())+"";
            }
            orderInfo.setTitle(carName);
            list.add(orderInfo);
        }
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(list);
        return euDataGridResult;
    }

    @Override
    public Car4sResult delete(String orderId) {
        try {
            orderMapper.deleteByPrimaryKey(orderId);
            TbOrderItemExample example = new TbOrderItemExample();
            TbOrderItemExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            orderItemMapper.deleteByExample(example);
            return Car4sResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(400,"删除错误");
        }
    }
}
