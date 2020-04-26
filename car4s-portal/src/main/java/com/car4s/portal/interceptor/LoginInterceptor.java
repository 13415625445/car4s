package com.car4s.portal.interceptor;

import com.car4s.common.utils.CookieUtil;
import com.car4s.generator.pojo.TbUser;
import com.car4s.portal.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 张少强
 */
@Controller
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtil.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        if(null == user) {
            response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURI());
            return false;
        }
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
