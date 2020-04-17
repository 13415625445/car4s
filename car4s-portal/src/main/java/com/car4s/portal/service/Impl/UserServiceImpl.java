package com.car4s.portal.service.Impl;

import com.car4s.common.pojo.Car4sResult;
import com.car4s.common.utils.CookieUtil;
import com.car4s.common.utils.HttpClientUtil;
import com.car4s.common.utils.JsonUtil;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 张少强
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    public String SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    @Value("${SSO_USER_UPDATE}")
    public String SSO_USER_UPDATE;



    @Override
    public TbUser getUserByToken(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            Car4sResult result = Car4sResult.formatToPojo(json, TbUser.class);
            if(result.getStatus() == 200){
                TbUser user = (TbUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
