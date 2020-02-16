package com.car4s.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by 张少强
 */
public interface PictureService {
     Map uploadPicture(MultipartFile multipartFile);
}
