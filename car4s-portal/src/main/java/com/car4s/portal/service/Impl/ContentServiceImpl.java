package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbContent;
import com.car4s.portal.service.ContentService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张少强
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        //通过httpclient请求获取数据
        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        Car4sResult car4sResult = Car4sResult.formatToList(result, TbContent.class);
        try {
            List<TbContent> list = (List<TbContent>) car4sResult.getData();
            List<Map> resultList = new ArrayList<>();
            for (TbContent tbContent : list) {
                Map map = new HashMap<>();
                map.put("src", tbContent.getPic());
                map.put("height", 402);
                map.put("width", 670);
//                map.put("srcB", tbContent.getPic2());
//                map.put("widthB", 670);
//                map.put("heightB", 402);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtil.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
