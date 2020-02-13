package com.car4s.service;

import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.pojo.TbItem;

/**
 * Created by ${张少强}
 */
public interface ItemService {

    TbItem findById(Long id);

    EUDataGridResult getItemList(int page,int rows);

}
