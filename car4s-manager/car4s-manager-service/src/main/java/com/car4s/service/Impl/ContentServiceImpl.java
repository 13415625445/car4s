package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.mapper.TbContentMapper;
import com.car4s.generator.pojo.TbContent;
import com.car4s.generator.pojo.TbContentCategoryExample;
import com.car4s.generator.pojo.TbContentExample;
import com.car4s.service.ContentService;
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
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public EUDataGridResult getContentCategoryList(long categoryId, Integer page, Integer rows) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public Car4sResult addContent(TbContent tbContent) {
        tbContentMapper.insert(tbContent);
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        return Car4sResult.ok();
    }

    @Override
    public Car4sResult updateContent(TbContent tbContent) {
        tbContentMapper.updateByPrimaryKey(tbContent);
        return Car4sResult.ok();
    }

    @Override
    public Car4sResult deleteContent(long id) {
        tbContentMapper.deleteByPrimaryKey(id);
        return Car4sResult.ok();
    }
}
