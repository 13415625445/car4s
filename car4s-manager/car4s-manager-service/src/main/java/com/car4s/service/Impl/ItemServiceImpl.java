package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.common.utils.IDUtil;
import com.car4s.generator.mapper.TbItemMapper;
import com.car4s.generator.pojo.TbItem;
import com.car4s.generator.pojo.TbItemExample;
import com.car4s.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample tbItemExample = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(tbItems);
        return euDataGridResult;
    }

    @Override
    public Car4sResult createItem(TbItem item) {
        Long itemId = IDUtil.genItemId();
        item.setId(itemId);
        //设置汽车的销售状态 1、在售 2、无货 3、删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        tbItemMapper.insert(item);
        return Car4sResult.ok();
    }
}
