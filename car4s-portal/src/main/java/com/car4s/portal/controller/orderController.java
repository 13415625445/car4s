package com.car4s.portal.controller;

import com.car4s.common.utils.ExceptionUtil;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.pojo.CartItem;
import com.car4s.portal.pojo.Order;
import com.car4s.portal.service.CartService;
import com.car4s.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/order")
public class orderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "order-cart";
    }

    @RequestMapping("/show")
    public String showOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<TbOrderItem> list = orderService.getOrderList(request, response);
        model.addAttribute("orderList", list);
        return "order-information";
    }

    @RequestMapping("/create")
    public String createOrder(Order order, Model model, HttpServletRequest request) {
        try{
        TbUser user = (TbUser)request.getAttribute("user");
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        String orderId = orderService.createOrder(order);
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", order.getPayment());
        model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
        return "success";}
        catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", ExceptionUtil.getStackTrace(e));
            return "error/exception";
        }
    }





}
