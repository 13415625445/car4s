package com.car4s.portal.controller;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.portal.pojo.CartItem;
import com.car4s.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue="1")Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        Car4sResult result = cartService.addCartItem(itemId, num, request, response);
        return "redirect:/cart/success.html";
    }

    @RequestMapping("/success")
    public String showSuccess() {
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public Car4sResult updateNumById(@PathVariable Long itemId, @PathVariable Integer num,
                                      HttpServletRequest request, HttpServletResponse response) {
        Car4sResult result = cartService.changeItemNum(itemId, num, request, response);
        return result;
    }


    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }

}
