package com.car4s.service.Impl;

import com.car4s.generator.mapper.TbItemMapper;
import com.car4s.generator.pojo.TbItem;
import com.car4s.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 张少强
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem findById(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
