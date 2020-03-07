package com.car4s.portal.controller;

import com.car4s.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.car4s.search.pojo.SearchResult;

/**
 * Created by 张少强
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String searchItemList(@RequestParam(value="q")String queryString, Integer page, Model model) throws Exception {
        //字符串转码
        queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");

        SearchResult searchResult = searchService.searchItemList(queryString, page);
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("page", searchResult.getCurPage());
        model.addAttribute("pages", searchResult.getPageCount());
        return "search";
    }
}

