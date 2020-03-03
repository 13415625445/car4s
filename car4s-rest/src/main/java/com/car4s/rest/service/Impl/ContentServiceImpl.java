package com.car4s.rest.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.generator.mapper.TbContentMapper;
import com.car4s.generator.pojo.TbContent;
import com.car4s.generator.pojo.TbContentExample;
import com.car4s.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> getContentList(long contentCategoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(contentCategoryId);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        return list;
    }
}
