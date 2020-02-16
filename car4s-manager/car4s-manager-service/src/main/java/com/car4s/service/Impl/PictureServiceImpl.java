package com.car4s.service.Impl;

import com.car4s.common.utils.FtpUtil;
import com.car4s.common.utils.IDUtil;
import com.car4s.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张少强
 */

/**
 * 图片上传
 */
@Service
public class PictureServiceImpl implements PictureService {
    //从resource.properties中获取信息
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile multipartFile) {
        Map resultMap = new HashMap();
        try {
            //判断文件是否为空
            if (multipartFile.isEmpty()) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件为空");
                return resultMap;
            }
            //获取旧的文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //生成新的文件名
            String newFileName = IDUtil.genImageName();
            //合成完整文件名
            newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf("."));
            //获取文件的输入流
            InputStream inputStream = multipartFile.getInputStream();
            //上传文件到服务器
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newFileName, inputStream);
            //根据是否上传成功返回对应数据
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            } else {
                resultMap.put("error", 0);
                resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newFileName);
                return resultMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生错误");
            return resultMap;
        }
    }
}
