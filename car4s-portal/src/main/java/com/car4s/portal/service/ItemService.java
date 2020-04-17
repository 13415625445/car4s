package com.car4s.portal.service;

import com.car4s.portal.pojo.ContrastItem;
import com.car4s.portal.pojo.ItemInfo;

import java.util.List;

/**
 * Created by 张少强
 */
public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
    ContrastItem contrastItemById(long itemId);
    List<ItemInfo> getItemList();
}
