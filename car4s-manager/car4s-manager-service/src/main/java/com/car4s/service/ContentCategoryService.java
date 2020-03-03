package com.car4s.service;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by 张少强
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);

    Car4sResult insertContentCategory(long parentId, String name);

    Car4sResult deleteContentCategory(long id);

    Car4sResult updateContentCategory(long id,String name);
}
