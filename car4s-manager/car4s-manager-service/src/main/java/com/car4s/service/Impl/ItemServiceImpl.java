package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.common.utils.IDUtil;
import com.car4s.generator.mapper.TbItemDescMapper;
import com.car4s.generator.mapper.TbItemMapper;
import com.car4s.generator.mapper.TbItemParamItemMapper;
import com.car4s.generator.pojo.*;
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

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

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
    public Car4sResult createItem(TbItem item, String itemDesc,String itemParam) throws Exception {
        Long itemId = IDUtil.genItemId();
        item.setId(itemId);
        //设置汽车的销售状态 1、在售 2、无货 3、删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        tbItemMapper.insert(item);
        Car4sResult result = insertItemDesc(itemId, itemDesc);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        result = insertItemParamItem(itemId,itemParam);
        if(result.getStatus()!=200){
            throw new Exception();
        }
        return Car4sResult.ok();
    }

    @Override
    public Car4sResult delete(Long itemId) {
        try {
            tbItemMapper.deleteByPrimaryKey(itemId);
            TbItemDescExample tbItemDescExample = new TbItemDescExample();
            TbItemDescExample.Criteria criteria = tbItemDescExample.createCriteria();
            criteria.andItemIdEqualTo(itemId);
            tbItemDescMapper.deleteByExample(tbItemDescExample);
            TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
            TbItemParamItemExample.Criteria criteria1 = tbItemParamItemExample.createCriteria();
            criteria1.andItemIdEqualTo(itemId);
            tbItemParamItemMapper.deleteByExample(tbItemParamItemExample);
            return Car4sResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Car4sResult.build(400,"删除出错");
        }
    }

    private Car4sResult insertItemDesc(Long itemId, String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        return Car4sResult.ok();
    }

    private Car4sResult insertItemParamItem(Long itemId, String itemParam){
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParam);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        tbItemParamItemMapper.insert(tbItemParamItem);
        return Car4sResult.ok();
    }
}
