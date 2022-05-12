/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2021/9/16
 * Time: 17:44
 **/
package com.jyf.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyf.blog.bean.User;
import com.jyf.blog.service.UserService;
import com.jyf.blog.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="admin/api")
public class UserController {
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value="getUserInfo.json")
    public AjaxResponse getUserInfo(HttpServletRequest request, Integer id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
            User user = userService.getUserById(sessionUserId);
            user.setPassword(null);
            user.setPic(null);
            user.setCreateTime(null);
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("ok");
            ajaxResponse.setContent(user);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequestMapping(value="updateUserInfo.json")
    public AjaxResponse updateUserInfo(HttpServletRequest request, String mail,Integer age){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            User user = new User();
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
            user.setId(sessionUserId);
            user.setMail(mail);
            user.setAge(age);
            int result = userService.updateUserById(user);
            if(result == 1){
                ajaxResponse.setCode(0);
                ajaxResponse.setMsg("ok");
            }else{
                ajaxResponse.setCode(333);
                ajaxResponse.setMsg("更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequestMapping(value="updateUserInfo2.json")
    public AjaxResponse updateUserInfo2(User user){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int result = userService.updateUserById(user);
            if(result == 1){
                ajaxResponse.setCode(0);
                ajaxResponse.setMsg("ok");
            }else{
                ajaxResponse.setCode(333);
                ajaxResponse.setMsg("更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequestMapping(value="updateUserInfo2.json",method= RequestMethod.POST)
    public AjaxResponse updateUserInfo2(HttpServletRequest request, @RequestBody String userJson){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
            JSONObject jsonObject = JSON.parseObject(userJson);
            User user = JSONObject.toJavaObject(jsonObject,User.class);
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
            user.setId(sessionUserId);
            int result = userService.updateUserById(user);
            if(result == 1){
                ajaxResponse.setCode(0);
                ajaxResponse.setMsg("ok");
            }else{
                ajaxResponse.setCode(333);
                ajaxResponse.setMsg("更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequestMapping(value="updateUserInfo4.json",method= RequestMethod.POST)
    public AjaxResponse updateUserInfo4(@RequestBody String str){
        System.out.println("updateUserInfo4-->str:" + str);
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
            JSONObject jsonObject = JSON.parseObject(str);
            System.out.println("updateUserInfo4-->json:" + jsonObject.toJSONString());
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("ok");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }

}
