package com.car4s.portal.service;

import com.car4s.search.pojo.SearchResult;

/**
 * Created by 张少强
 */
public interface SearchService {
    SearchResult searchItemList(String queryString, Integer page) throws Exception;
}
