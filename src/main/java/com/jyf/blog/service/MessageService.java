/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/30
 * Time: 8:42
 **/
package com.jyf.blog.service;

import com.jyf.blog.bean.Message;

import java.util.List;

public interface MessageService {
    
    List<Message> getAllMessage(int pageNum, int pageSize) throws Exception;
    
    //获取留言，展示在首页，获取最近的10条
    List<Message> getIndexMessage() throws Exception;
    
    int addMessage(Message message) throws Exception;
    
}
