package com.car4s.portal.service;


import com.car4s.portal.pojo.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */
public interface RecommendService {
    List<Item> getRecommendItems(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
