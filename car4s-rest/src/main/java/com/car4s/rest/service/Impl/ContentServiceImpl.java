package com.car4s.rest.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.mapper.TbContentMapper;
import com.car4s.generator.pojo.TbContent;
import com.car4s.generator.pojo.TbContentExample;
import com.car4s.rest.dao.JedisClient;
import com.car4s.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCategoryId) {
        //从缓存中存取内容
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "");
            if(!StringUtils.isBlank(result)){
                List<TbContent> list = JsonUtil.jsonToList(result, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(contentCategoryId);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        //在缓存中添加内容
        try {
            String json = JsonUtil.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCategoryId+"", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
