package com.car4s.portal.service;

import com.car4s.portal.pojo.ItemInfo;

/**
 * Created by 张少强
 */
public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
