/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2022/1/10
 * Time: 22:24
 **/
package com.jyf.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="admin/")
public class AdminUserPageController {
    
    @RequestMapping("getUserInfo.htm")
    public ModelAndView getPersonInfoPage(){
        ModelAndView modelAndView = new ModelAndView("admin/userInfo");
        return modelAndView;
    }
    
    @RequestMapping("updateUserInfo.htm")
    public ModelAndView updateUserInfoPage(){
        ModelAndView modelAndView = new ModelAndView("admin/updateUserInfo");
        return modelAndView;
    }
}
