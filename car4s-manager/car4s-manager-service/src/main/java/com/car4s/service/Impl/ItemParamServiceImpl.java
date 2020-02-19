package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.mapper.TbItemParamMapper;
import com.car4s.generator.pojo.TbItemParam;
import com.car4s.generator.pojo.TbItemParamExample;
import com.car4s.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 根据id获取分类信息进行判断
     * @param itemCatId
     * @return
     */
    @Override
    public Car4sResult getItemParamByCid(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list !=null && !list.isEmpty()){
            return Car4sResult.ok(list.get(0));
        }
        return Car4sResult.ok();
    }

    /**
     * 根据分类插入新的规格
     * @param itemParam
     * @return
     */
    @Override
    public Car4sResult insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        tbItemParamMapper.insert(itemParam);
        return Car4sResult.ok();
    }
}