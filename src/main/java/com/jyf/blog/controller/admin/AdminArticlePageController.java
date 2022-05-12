/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/26
 * Time: 15:42
 **/
package com.jyf.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="admin/")
public class AdminArticlePageController {
    
    
    @RequestMapping("listArticle.htm")
    public ModelAndView adminListArticlePage(@RequestParam(defaultValue = "1")Integer pageNum){
        ModelAndView modelAndView = new ModelAndView("admin/article/listArticle");
        modelAndView.addObject("pageNum",pageNum);
        return modelAndView;
    }
    
    @RequestMapping("listArticle2.htm")
    public ModelAndView adminListArticlePage2(@RequestParam(defaultValue = "1")Integer pageNum){
        ModelAndView modelAndView = new ModelAndView("admin/article/listArticle2");
        modelAndView.addObject("pageNum",pageNum);
        return modelAndView;
    }
    
    @RequestMapping("showArticle.htm")
    public ModelAndView adminShowArticlePage(@RequestParam(defaultValue = "1")Integer id){
        ModelAndView modelAndView = new ModelAndView("admin/article/showArticle");
        modelAndView.addObject("id",id);
        return modelAndView;
    }
    
    
    @RequestMapping("showArticle2.htm")
    public ModelAndView adminShowArticlePage2(@RequestParam(defaultValue = "1")Integer id){
        ModelAndView modelAndView = new ModelAndView("admin/article/showArticle2");
        modelAndView.addObject("id",id);
        return modelAndView;
    }
}
