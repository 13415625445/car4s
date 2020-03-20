package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.CookieUtil;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbItem;
import com.car4s.portal.pojo.CartItem;
import com.car4s.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Override
    public Car4sResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem = null;
        List<CartItem> itemList = getCartItemList(request);
        for (CartItem item : itemList) {
            if (item.getId() == itemId) {
                item.setNum((item.getNum() + num));
                cartItem = item;
                break;
            }
        }
        if (cartItem == null) {
            cartItem = new CartItem();
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            Car4sResult car4sResult = Car4sResult.formatToPojo(json, TbItem.class);
            if (car4sResult.getStatus() == 200) {
                TbItem tbItem = (TbItem) car4sResult.getData();
                cartItem.setId(tbItem.getId());
                cartItem.setTitle((tbItem.getTitle()));
                cartItem.setNum(tbItem.getNum());
                cartItem.setPrice(tbItem.getPrice());
                cartItem.setImage(tbItem.getImage() == null ? "" : tbItem.getImage().split(",")[0]);
            }
            itemList.add(cartItem);
        }
        CookieUtil.setCookie(request, response, "TT_CART", JsonUtil.objectToJson(itemList), true);
        return null;
    }

    //从cookie获取商品
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        String json = CookieUtil.getCookieValue(request, "TT_CART", true);
        if (json == null) {
            return new ArrayList<>();
        }
        try {
            List<CartItem> list = JsonUtil.jsonToList(json, CartItem.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }

    @Override
    public Car4sResult changeItemNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取商品列表
        List<CartItem> list = getCartItemList(request);
        //从商品列表中找到要修改数量的商品
        for (CartItem item : list) {
            if (item.getId() == itemId) {
                //找到商品，修改数量
                item.setNum(num);
                break;
            }
        }
        //把商品信息写入cookie
        CookieUtil.setCookie(request, response,"TT_CART", JsonUtil.objectToJson(list), true);

        return Car4sResult.ok();
    }

    /**
     * 删除购物车商品
     */
    @Override
    public Car4sResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品
        for (CartItem cartItem : itemList) {
            if (cartItem.getId() == itemId) {
                itemList.remove(cartItem);
                break;
            }

        }
        //把购物车列表重新写入cookie
        CookieUtil.setCookie(request, response, "TT_CART", JsonUtil.objectToJson(itemList), true);
        return Car4sResult.ok();
    }
}
