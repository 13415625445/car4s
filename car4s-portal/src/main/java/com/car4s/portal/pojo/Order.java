package com.car4s.portal.pojo;

import com.car4s.generator.pojo.TbOrder;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 张少强
 */
public class Order extends TbOrder {
    private List<TbOrderItem> orderItems;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
