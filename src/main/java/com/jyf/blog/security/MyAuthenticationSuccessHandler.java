/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 11:34
 **/
package com.jyf.blog.security;


import com.jyf.blog.bean.User;
import com.jyf.blog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    
    @Autowired
    private UserService userService;
    
    private static final Logger LOGGER = LogManager.getLogger(MyAuthenticationSuccessHandler.class);
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        RequestCache requestCache = new HttpSessionRequestCache();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = null;
        try {
        
            user = userService.getUserByUsername(userDetails.getUsername());
            //request.getSession().setAttribute("username",user.getUsername());
            request.getSession().setAttribute("realName",user.getRealName());
            request.getSession().setAttribute("userId",user.getId());
            //logService.addLog(1,2,"认证模块","登录","成功",user.getUsername() + "用户登陆成功，登陆IP为" + request.getRemoteAddr());
            logger.info("[INFO]登录成功，user:" + user.getName());
            Cookie cookie = new Cookie("userId",user.getId().toString());
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }catch (Exception e){
            //logService.addLog(1,2,"认证模块","登录","失败","保存session失败,username为" + user.getUsername());
        }
        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            url = savedRequest.getRedirectUrl();
        }
        if(url == null){
            getRedirectStrategy().sendRedirect(request,response,"/admin/adminIndex.htm");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
