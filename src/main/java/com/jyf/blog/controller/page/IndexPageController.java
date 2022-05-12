/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/17
 * Time: 16:22
 **/
package com.jyf.blog.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class IndexPageController {
    
    @RequestMapping("index.htm")
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    
    @RequestMapping("file.htm")
    public ModelAndView filePage(){
        ModelAndView modelAndView = new ModelAndView("file");
        return modelAndView;
    }
    
    @RequestMapping("demo.htm")
    public ModelAndView demoPage(){
        ModelAndView modelAndView = new ModelAndView("demo");
        return modelAndView;
    }
    
    
    @RequestMapping(value="login.htm")
    public ModelAndView loginPage(HttpSession session){
        ModelAndView modelAndView = null;
        if(session.getAttribute("userId") == null){
            modelAndView = new ModelAndView("login");
        }else{
            modelAndView = new ModelAndView("redirect:adminIndex.htm");
        }
        return modelAndView;
    }
    
    
    @RequestMapping(value="login2.htm")
    public ModelAndView login2Page(HttpSession session){
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView("login2");
        return modelAndView;
    }
    
    
    @RequestMapping("test.htm")
    public ModelAndView testPage(){
        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }
    
    @RequestMapping("upload.htm")
    public ModelAndView uploadPage(){
        ModelAndView modelAndView = new ModelAndView("upload");
        return modelAndView;
    }
    
    
    @RequestMapping("uploadStatus.htm")
    public ModelAndView uploadStatusPage(String status){
        ModelAndView modelAndView = new ModelAndView("uploadStatus");
        modelAndView.addObject("status",status);
        return modelAndView;
    }
    
    @RequestMapping("getUserInfo.htm")
    public ModelAndView getPersonInfoPage(){
        ModelAndView modelAndView = new ModelAndView("userInfo");
        return modelAndView;
    }
    
    @RequestMapping("updateUserInfo.htm")
    public ModelAndView updateUserInfoPage(){
        ModelAndView modelAndView = new ModelAndView("updateUserInfo");
        return modelAndView;
    }
    
    @RequestMapping("redirect.htm")
    public ModelAndView getOtherPage(String url){
        ModelAndView modelAndView = new ModelAndView("redirect:" + url);
        return modelAndView;
    }
    
    @RequestMapping("redirect2.htm")
    public void getOtherPage2(String url, HttpServletResponse response){
        String htmlContent;
        PrintWriter writer = null;
        StringBuffer html = new StringBuffer();
        try {
            writer = response.getWriter();
            URL u = new URL(url);   //实例化url的对象
            URLConnection urlConnection = u.openConnection();//打开一个URL连接，并运行客户端访问资源。
            //HttpURLConnection httpUrl = (HttpURLConnection) urlConnection;  //强转为HttpURLConnection
            BufferedReader base = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));  //获取url中的资源
            
            while ((htmlContent = base.readLine()) != null) {
                html.append(htmlContent);  //htmlContent添加到html里面
            }
            base.close();
            writer.println(html);//响应中输出读取的资源
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        //return html.toString();
    }
    
}
