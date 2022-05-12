/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/22
 * Time: 11:14
 **/
package com.jyf.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="admin/")
public class AdminIndexPageController {
    
    @RequestMapping("adminIndex.htm")
    public ModelAndView adminIndexPage(){
        ModelAndView modelAndView = new ModelAndView("admin/admin-index");
        return modelAndView;
    }
    
    
}
