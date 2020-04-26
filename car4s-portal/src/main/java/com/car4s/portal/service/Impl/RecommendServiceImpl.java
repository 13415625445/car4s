package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.CookieUtil;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbOrderItem;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.pojo.Item;
import com.car4s.portal.pojo.ItemInfo;
import com.car4s.portal.pojo.SearchResult;
import com.car4s.portal.service.ItemService;
import com.car4s.portal.service.RecommendService;
import com.car4s.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张少强
 */

@Service
public class RecommendServiceImpl implements RecommendService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Value("${ORDER_SHOW_URL}")
    private String ORDER_SHOW_URL;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SearchService searchService;

    @Override
    public List<Item> getRecommendItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = CookieUtil.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        if(user!=null){
            String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_SHOW_URL, JsonUtil.objectToJson(user));
            Car4sResult car4sResult = Car4sResult.formatToList(json, TbOrderItem.class);
            if (car4sResult.getStatus() == 200) {
                List<TbOrderItem> orderItemList = (List<TbOrderItem>) car4sResult.getData();
                if(orderItemList.size()>0){
                    List<Item> totalList = new ArrayList<>();
                    for (TbOrderItem orderItem : orderItemList) {
                        ItemInfo item = itemService.getItemById(Long.valueOf(orderItem.getItemId()));
                        if(item!=null) {
                            String keyWord = item.getTitle().substring(0, 4);
                            SearchResult searchItemList = searchService.searchItemList(keyWord, 1);
                            List<Item> list = searchItemList.getItemList();
                            for (Item item1 : list) {
                                totalList.add(item1);
                                }
                            }
                        }
                    for  ( int  i  =   0 ; i  <  totalList.size()  -   1 ; i ++ )  {
                        for  ( int  j  =  totalList.size()  -   1 ; j  >  i; j -- )  {
                            if  (totalList.get(j).getId().equals(totalList.get(i).getId()))  {
                                totalList.remove(j);
                            }
                        }
                    }
                    return totalList;
                    }
                    else{
                        String likeModel = user.getFavoriteModel();
                        SearchResult searchResult = searchService.searchItemList(likeModel, 1);
                        List<Item> list = searchResult.getItemList();
                        return list;
                    }
                }
            }
            else {
                response.sendRedirect("http://localhost:8083/page/login");
        }
        return null;
    }
}
