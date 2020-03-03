package com.car4s.rest.service;

import com.car4s.generator.pojo.TbContent;

import java.util.List;

/**
 * Created by 张少强
 */
public interface ContentService {
    List<TbContent> getContentList(long contentCategoryId);
}
