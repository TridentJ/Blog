/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/30
 * Time: 9:27
 **/
package com.jyf.blog.controller;

import com.jyf.blog.bean.Message;
import com.jyf.blog.bean.User;
import com.jyf.blog.service.MessageService;
import com.jyf.blog.service.UserService;
import com.jyf.blog.util.AjaxResponse;
import com.jyf.blog.util.message.MessageString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value="api")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="getIndexMessage.json",method= RequestMethod.POST)
    public AjaxResponse getIndexMessage(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            List<Message> messageList = messageService.getIndexMessage();
            if(messageList == null || messageList.isEmpty()){
                ajaxResponse.setCode(214);
                ajaxResponse.setMsg("未读取到动弹");
                return ajaxResponse;
            }
            Iterator<Message> messageIterator = messageList.iterator();
            MessageString messageString = null;
            List<MessageString> messageStringList = new ArrayList<>();
            Message message = null;
            User user = null;
            while(messageIterator.hasNext()){
                message = messageIterator.next();
                messageString = new MessageString();
                messageString.setId(message.getId());
                messageString.setUserId(message.getUserId());
                
                try {
                    if(message.getUserId() != null){
                        user = userService.getUserById(message.getUserId());
                        if(user == null){
                            messageString.setUsername("-");
                            messageString.setRealName("-");
                            messageString.setPic("img/nm.png");
                        }else{
                            messageString.setUsername(user.getName());
                            messageString.setRealName(user.getRealName());
                            messageString.setPic(user.getPic());
                        }
                    }else{
                        messageString.setUsername("匿名");
                        messageString.setRealName("匿名");
                        messageString.setPic("img/nm.png");
                    }
                }catch (Exception e){
                    messageString.setUsername("-");
                    messageString.setRealName("-");
                    messageString.setPic("img/nm.png");
                }
                try {
                    messageString.setCreateTime(sdf.format(message.getCreateTime()));
                }catch (Exception e){
                    messageString.setCreateTime("1970-01-01 00:00:00");
                }
                messageString.setContent(message.getContent());
                messageStringList.add(messageString);
            }
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("ok");
            ajaxResponse.setContent(messageStringList);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(106);
            ajaxResponse.setMsg("读取动弹失败");
        }
        return ajaxResponse;
    }
    
    
    @ResponseBody
    @RequestMapping(value="addMessage.json",method= RequestMethod.POST)
    public AjaxResponse addMessage(HttpServletRequest request,String content){
        AjaxResponse ajaxResponse = new AjaxResponse();
        Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
        Message message = new Message();
        if(sessionUserId == null || sessionUserId <= 0){
            message.setUserId(null);
        }else{
            try {
                User user = userService.getUserById(sessionUserId);
                if(user == null){
                    ajaxResponse.setContent(212);
                    ajaxResponse.setMsg("用户不合法");
                    return ajaxResponse;
                }
                message.setUserId(sessionUserId);
            }catch (Exception e){
                message.setUserId(null);
            }
        }
        message.setContent(content);
        try {
            int result = messageService.addMessage(message);
            if(result == 1){
                ajaxResponse.setCode(0);
                ajaxResponse.setMsg("ok");
            }else{
                ajaxResponse.setCode(213);
                ajaxResponse.setMsg("发表动弹失败！");
            }
            
        }catch (Exception e){
            ajaxResponse.setCode(106);
            ajaxResponse.setMsg("数据处理失败");
        }
        
        return ajaxResponse;
    }
    

}
