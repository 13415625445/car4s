package com.car4s.portal.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */
public interface CartService {
    //加入购物车
    Car4sResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
    //获取购物车
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    //修改购物车数量
    Car4sResult changeItemNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
    //删除购物车
    Car4sResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
    //获取当前联系人
    TbUser findCurrentUser(HttpServletRequest request);
}
