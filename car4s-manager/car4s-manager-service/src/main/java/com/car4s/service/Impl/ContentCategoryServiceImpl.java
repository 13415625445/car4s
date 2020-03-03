package com.car4s.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUTreeNode;
import com.car4s.generator.mapper.TbContentCategoryMapper;
import com.car4s.generator.pojo.TbContentCategory;
import com.car4s.generator.pojo.TbContentCategoryExample;
import com.car4s.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 张少强
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<EUTreeNode> array = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbContentCategory.getId());
            euTreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            euTreeNode.setText(tbContentCategory.getName());
            array.add(euTreeNode);
        }
        return array;
    }

    @Override
    public Car4sResult insertContentCategory(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        tbContentCategoryMapper.insert(tbContentCategory);
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!category.getIsParent()){
            category.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(category);
        }
        return Car4sResult.ok(tbContentCategory);
    }

    @Override
    public Car4sResult deleteContentCategory(long id) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        Long parentId = tbContentCategory.getParentId();
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        if(tbContentCategoryMapper.selectByExample(tbContentCategoryExample) == null){
           category.setIsParent(false);
           tbContentCategoryMapper.updateByPrimaryKey(category);
        }
        return Car4sResult.ok();
    }

    @Override
    public Car4sResult updateContentCategory(long id, String name) {
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        tbContentCategoryMapper.updateByPrimaryKey(category);
        return Car4sResult.ok();
    }
}
