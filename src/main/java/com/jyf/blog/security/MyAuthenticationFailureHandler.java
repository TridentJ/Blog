/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 11:43
 **/
package com.jyf.blog.security;

import com.jyf.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    
    private static int FAILURE_NUM = 0;
    @Autowired
    private UserService userService;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        
        System.out.println("onAuthenticationFailure:" + exception.getMessage());
        System.out.println("onAuthenticationFailure:" + exception.getLocalizedMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
