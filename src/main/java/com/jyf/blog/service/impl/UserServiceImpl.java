/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/17
 * Time: 16:44
 **/
package com.jyf.blog.service.impl;

import com.jyf.blog.bean.User;
import com.jyf.blog.bean.UserExample;
import com.jyf.blog.dao.UserMapper;
import com.jyf.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserById(Integer id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public User getUserByUsername(String username) throws Exception {
        UserExample usersExample = new UserExample();
        UserExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(usersExample);
        if(userList == null | userList.isEmpty()){
            return null;
        }else{
            return userList.get(0);
        }
    }
    
    @Override
    public int freezeUser(Integer id) throws Exception {
        return 0;
    }
    
    @Override
    public int freezeUserByName(String userName) throws Exception {
        
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(userName);
        User user = new User();
        user.setStatus(0);
        int result = userMapper.updateByExampleSelective(user,userExample);
        return result;
    }
    
    @Override
    public int updateUserById(User user) throws Exception {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
