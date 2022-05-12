/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/30
 * Time: 8:44
 **/
package com.jyf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.jyf.blog.bean.Message;
import com.jyf.blog.bean.MessageExample;
import com.jyf.blog.dao.MessageMapper;
import com.jyf.blog.dao.UserMapper;
import com.jyf.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public List<Message> getAllMessage(int pageNum, int pageSize) throws Exception {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andIdGreaterThan(0);
        criteria.andStatusEqualTo(1);
        messageExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Message> messageList = messageMapper.selectByExample(messageExample);
        return messageList;
    }
    
    @Override
    public List<Message> getIndexMessage() throws Exception {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andIdGreaterThan(0);
        criteria.andStatusEqualTo(1);
        messageExample.setOrderByClause("id desc");
        PageHelper.startPage(1,10);
        List<Message> messageList = messageMapper.selectByExample(messageExample);
        return messageList;
    }
    
    @Override
    public int addMessage(Message message) throws Exception {
        
        return messageMapper.insertSelective(message);
    }
}
