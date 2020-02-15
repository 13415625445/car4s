package com.car4s.test;

import com.car4s.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 张少强
 */
public class FTPTest {
    @Test
    public void ftpTest() throws IOException {
        //获取ftp客户端
        FTPClient ftpClient = new FTPClient();
        //获取连接
        ftpClient.connect("192.168.117.131",21);
        //设置账号密码
        ftpClient.login("ftpzsq","111111");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\200109113Z4-1.jpg"));
        //设置文件上传目的地
        ftpClient.changeWorkingDirectory("/home/ftpzsq/www/images");
        //设置文件上传格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //执行上传
        ftpClient.storeFile("hello1.jpg",fileInputStream);
        ftpClient.logout();

    }

    @Test
    public void FTPUtilsTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\1Z924105551-2.jpg"));
        FtpUtil.uploadFile("192.168.117.131",21,"ftpzsq","111111","/home/ftpzsq/www/images","/2020/02/15","hello2.jpg",fileInputStream);
    }
}
