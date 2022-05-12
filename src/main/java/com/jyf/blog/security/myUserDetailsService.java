/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 11:19
 **/
package com.jyf.blog.security;


import com.jyf.blog.bean.User;
import com.jyf.blog.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class myUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;
    
    private static final Logger logger = LogManager.getLogger(myUserDetailsService.class);
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        UserDetails userDetails = null;
        User user = null;
        try {
            user = userService.getUserByUsername(username);
        }catch (Exception e){
            //logService.addLog(1,1,"认证模块","登录","失败",username +"用户登陆时发生错误" + e.getMessage());
            //userDetails = new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),false,true,true,false,null);
            logger.error("[ERROR]用户不存在，user:" + username);
            throw new UsernameNotFoundException("获取用户失败");
        }
        if(user == null){
            //logService.addLog(1,3,"认证模块","登录","失败",username +"用户不存在");
            logger.error("[ERROR]用户不存在，user:" + username);
            throw new UsernameNotFoundException("用户不存在");
        }
        if(user.getStatus() == 0){
            //用户被冻结
            //logService.addLog(1,3,"认证模块","登录","失败",username +"用户被冻结");
            //userDetails = new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),true,true,true,true,authorityList);
            logger.error("[ERROR]用户被锁定，user:" + username);
            throw new UsernameNotFoundException("账户被冻结");
        }
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if(user.getAuth() == 2){
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else if(user.getAuth() == 1){
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            authorityList.add(new SimpleGrantedAuthority("ROLE_VISITOR"));
        }
        userDetails = new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),true,true,true,true,authorityList);
    
        return userDetails;
    }
}
