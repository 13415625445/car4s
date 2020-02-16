package com.car4s.controller;

import com.car4s.common.utils.JsonUtil;
import com.car4s.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by 张少强
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile) {
        Map result = pictureService.uploadPicture(uploadFile);
        String json = JsonUtil.objectToJson(result);
        return json;
    }
}
