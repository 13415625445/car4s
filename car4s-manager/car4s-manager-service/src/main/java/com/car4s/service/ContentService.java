package com.car4s.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUDataGridResult;
import com.car4s.generator.pojo.TbContent;

/**
 * Created by 张少强
 */
public interface ContentService {
    //获取广告分类内容列表
    EUDataGridResult getContentCategoryList(long categoryId, Integer page, Integer rows);

    //增加广告分类内容信息
    Car4sResult addContent(TbContent tbContent);

    //更新广告内容信息
    Car4sResult updateContent(TbContent tbContent);

    //删除广告内容信息
    Car4sResult deleteContent(long id);

}
