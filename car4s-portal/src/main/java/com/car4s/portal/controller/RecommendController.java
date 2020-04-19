package com.car4s.portal.controller;

import com.car4s.portal.pojo.Item;
import com.car4s.portal.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 张少强
 */
@Controller
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 推荐模块
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/recommend")
    public String recommendItem(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        List<Item> itemList = recommendService.getRecommendItems(request, response);
        model.addAttribute("itemList", itemList);
        return "search";
    }
}
