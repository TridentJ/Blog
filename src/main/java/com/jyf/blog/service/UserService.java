/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/25
 * Time: 13:55
 **/
package com.jyf.blog.service;

import com.jyf.blog.bean.User;

public interface UserService {
    
    User getUserById(Integer id) throws Exception;
    
    User getUserByUsername(String username) throws Exception;
    
    int freezeUser(Integer id) throws Exception;
    
    int freezeUserByName(String userName) throws Exception;
    
    int updateUserById(User user) throws Exception;
}
