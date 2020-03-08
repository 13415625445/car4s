package com.car4s.rest.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.mapper.TbItemDescMapper;
import com.car4s.generator.mapper.TbItemMapper;
import com.car4s.generator.mapper.TbItemParamItemMapper;
import com.car4s.generator.mapper.TbItemParamMapper;
import com.car4s.generator.pojo.*;
import com.car4s.rest.dao.JedisClient;
import com.car4s.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private long REDIS_ITEM_EXPIRE;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Car4sResult getItemBaseInfo(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if(!StringUtils.isBlank(json)){
                TbItem item = JsonUtil.jsonToPojo(json, TbItem.class);
                return Car4sResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base", JsonUtil.objectToJson(item));
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Car4sResult.ok(item);
    }

    @Override
    public Car4sResult getItemDescInfo(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if(!StringUtils.isBlank(json)){
                TbItemDesc itemDesc = JsonUtil.jsonToPojo(json, TbItemDesc.class);
                return Car4sResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc", JsonUtil.objectToJson(itemDesc));
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc",REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Car4sResult.ok(itemDesc);
    }

    @Override
    public Car4sResult getItemParamInfo(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(json)) {
                TbItemParamItem itemParamItem = JsonUtil.jsonToPojo(json, TbItemParamItem.class);
                return Car4sResult.ok(itemParamItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem itemParamItem = list.get(0);
            try {
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtil.objectToJson(itemParamItem));
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Car4sResult.ok(itemParamItem);
        }
        return Car4sResult.build(400,"无此商品信息");
    }
}
