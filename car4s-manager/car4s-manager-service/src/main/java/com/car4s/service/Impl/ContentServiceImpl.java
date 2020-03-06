package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.generator.mapper.TbContentMapper;
import com.car4s.generator.pojo.TbContent;
import com.car4s.generator.pojo.TbContentCategoryExample;
import com.car4s.generator.pojo.TbContentExample;
import com.car4s.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

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
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);
        try {
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+tbContent.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
