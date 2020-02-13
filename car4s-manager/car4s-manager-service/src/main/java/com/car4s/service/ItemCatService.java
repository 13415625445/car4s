package com.car4s.service;

import com.car4s.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by 张少强
 */
public interface ItemCatService {

    List<EUTreeNode> getItemCatList(long parentId);
}
