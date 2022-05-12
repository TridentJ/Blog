/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/18
 * Time: 15:43
 **/
package com.jyf.blog.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

@Controller
public class ArticlePageController {
    
    @RequestMapping("search2.htm")
    public ModelAndView search2Page(@RequestParam(defaultValue = "") String title){
        ModelAndView modelAndView = new ModelAndView("search2");
        //System.out.println("title:" + title);
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            String decoderTitle = new String(decoder.decode(title),"UTF-8");
            //System.out.println("title decode:" + decoderTitle);
            modelAndView.addObject("title",decoderTitle);
        }catch (Exception e){
            modelAndView.addObject("title","");
        }
        
        return modelAndView;
    }
    
    
    @RequestMapping("search.htm")
    public ModelAndView searchPage(){
        ModelAndView modelAndView = new ModelAndView("search");
        return modelAndView;
    }
    
    @RequestMapping("showArticle.htm")
    public ModelAndView adminShowArticlePage(@RequestParam(defaultValue = "1")Integer id){
        ModelAndView modelAndView = new ModelAndView("article/showArticle");
        modelAndView.addObject("id",id);
        return modelAndView;
    }
    
}
