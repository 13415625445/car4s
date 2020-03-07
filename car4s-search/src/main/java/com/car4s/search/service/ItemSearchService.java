package com.car4s.search.service;

import com.car4s.search.pojo.SearchResult;

/**
 * Created by 张少强
 */
public interface ItemSearchService {
     SearchResult searchItem(String queryString, int page, int rows) throws Exception;
}
