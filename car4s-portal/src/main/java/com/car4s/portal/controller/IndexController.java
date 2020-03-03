package com.car4s.portal.controller;

import com.car4s.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张少强
 */
@Controller
public class IndexController {
    
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        String result = contentService.getContentList();
        model.addAttribute("ad1", result);
        return "index";
    }
}
