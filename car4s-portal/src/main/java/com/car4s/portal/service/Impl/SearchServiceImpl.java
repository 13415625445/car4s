package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.car4s.search.pojo.SearchResult;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张少强
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult searchItemList(String queryString, Integer page) throws Exception {
        //查询参数
        Map<String, String> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page", page==null?"1":page.toString());
        String resultString = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        Car4sResult taotaoResult = Car4sResult.formatToPojo(resultString, SearchResult.class);
        SearchResult searchResult = null;
        //查询成功
        if (taotaoResult.getStatus() == 200) {
            //取查询结果
            searchResult = (SearchResult) taotaoResult.getData();
        }
        return searchResult;
    }

}

