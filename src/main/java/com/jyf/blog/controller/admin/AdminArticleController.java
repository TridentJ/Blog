/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/26
 * Time: 11:22
 **/
package com.jyf.blog.controller.admin;

import com.jyf.blog.bean.Article;
import com.jyf.blog.bean.User;
import com.jyf.blog.service.ArticleService;
import com.jyf.blog.service.UserService;
import com.jyf.blog.util.AjaxResponse;
import com.jyf.blog.util.AjaxResponseList;
import com.jyf.blog.util.article.ArticleString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value="admin/api")
public class AdminArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    
    
    /*
     * 通过cookie获取id
     * */
    @ResponseBody
    @RequestMapping(value="getArticleByUserId.json",method= RequestMethod.POST)
    public AjaxResponseList getArticleByUserId(HttpServletRequest request,/*Integer userId,*/Integer pageNum, Integer pageSize){
        AjaxResponseList ajaxResponseList = new AjaxResponseList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Integer myPageNum = pageNum;
        if(pageNum == null || pageSize == null){
            ajaxResponseList.setCode(109);
            ajaxResponseList.setMsg("参数不能为空");
            return ajaxResponseList;
        }
        if(pageNum < 0){
            ajaxResponseList.setCode(120);
            ajaxResponseList.setMsg("页数不能小于0");
            return ajaxResponseList;
        }
        if(pageSize < 0 || pageSize > 50){
            ajaxResponseList.setCode(121);
            ajaxResponseList.setMsg("每页数量不能小于0");
            return ajaxResponseList;
        }
        /*
        if(userId == null || userId < 0){
            ajaxResponseList.setCode(203);
            ajaxResponseList.setMsg("作者不存在");
            return ajaxResponseList;
        }
        */
        if(myPageNum < 1){
            myPageNum = 1;
        }
        try {
            
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
            //System.out.println("sessionUserId:" + sessionUserId);
            /*
            if(userId != sessionUserId){
                ajaxResponseList.setCode(204);
                ajaxResponseList.setMsg("请求参数不合法");
                return ajaxResponseList;
            }
            */
            User user = userService.getUserById(sessionUserId);
            if(user == null){
                ajaxResponseList.setCode(205);
                ajaxResponseList.setMsg("用户不存在不合法");
                return ajaxResponseList;
            }
            List<Article> articleList = articleService.getArticleByUserId(sessionUserId,pageNum,pageSize);
            if(articleList == null || articleList.isEmpty()){
                ajaxResponseList.setCode(201);
                ajaxResponseList.setMsg("结果为空");
                return ajaxResponseList;
            }
            //int num = articleService.getAllCount();
            int num = articleService.getCountByUserId(sessionUserId);
            ajaxResponseList.setNum(num);
            Integer start = (myPageNum-1)*pageSize + 1;
            if(start > num){
                if(num%pageSize == 0){
                    myPageNum = num/pageSize;
                    ajaxResponseList.setPageNum(myPageNum);
                }else{
                    myPageNum = num/pageSize + 1;
                    ajaxResponseList.setPageNum(myPageNum);
                }
            }else{
                ajaxResponseList.setPageNum(myPageNum);
            }
            start = (myPageNum-1)*pageSize + 1;
            ajaxResponseList.setStart(start);
            ajaxResponseList.setEnd((myPageNum-1)*pageSize + articleList.size());
    
            Article article = null;
            ArticleString articleString = null;
            //User user1 = null;
            List<ArticleString> articleStringList = new ArrayList<>();
    
            Iterator<Article> articleIterator = articleList.iterator();
            while (articleIterator.hasNext()){
                article = articleIterator.next();
                articleString = new ArticleString();
                articleString.setId(article.getId());
                articleString.setUserId(article.getUserId());
                /*
                if(article.getUserId() > 0){
                    user = userService.getUserById(article.getUserId());
                    if(user != null){
                        articleString.setUserName(user.getRealName());
                    }else{
                        articleString.setUserName("");
                    }
                }else{
                    articleString.setUserName("");
                }
                */
                articleString.setUserName(user.getRealName());
                articleString.setTitle(article.getTitle());
                articleString.setSubTitle(article.getSubTitle());
                articleString.setPic(article.getPic());
                //articleString.setContent(article.getContent());
                articleString.setHits(article.getHits());
                articleString.setStatus(article.getStatus());
                try {
                    articleString.setCreateTime(sdf.format(article.getCreateTime()));
                }catch (Exception e){
                    articleString.setCreateTime("");
                }
                articleStringList.add(articleString);
            }
            ajaxResponseList.setCode(0);
            ajaxResponseList.setMsg("ok");
            ajaxResponseList.setContent(articleStringList);
            
            
            
        }catch (Exception e){
            //e.printStackTrace();
            ajaxResponseList.setCode(106);
            ajaxResponseList.setMsg("数据处理失败");
        }
        return ajaxResponseList;
    }
    
    /*
    * 通过cookie获取id
    * */
    @ResponseBody
    @RequestMapping(value="getArticleByUserId2.json",method= RequestMethod.POST)
    public AjaxResponseList getArticleByUserId2(HttpServletRequest request,Integer pageNum, Integer pageSize){
        AjaxResponseList ajaxResponseList = new AjaxResponseList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Integer myPageNum = pageNum;
        Integer userId = null;
        if(pageNum == null || pageSize == null){
            ajaxResponseList.setCode(109);
            ajaxResponseList.setMsg("参数不能为空");
            return ajaxResponseList;
        }
        if(pageNum < 0){
            ajaxResponseList.setCode(120);
            ajaxResponseList.setMsg("页数不能小于0");
            return ajaxResponseList;
        }
        if(pageSize < 0 || pageSize > 50){
            ajaxResponseList.setCode(121);
            ajaxResponseList.setMsg("每页数量不能小于0");
            return ajaxResponseList;
        }
        if(myPageNum < 1){
            myPageNum = 1;
        }
        try {
            
            Cookie[] cookies = request.getCookies();
            if(cookies == null){
                ajaxResponseList.setCode(206);
                ajaxResponseList.setMsg("校验身份失败！");
                return ajaxResponseList;
            }else{
                for(Cookie cookie:cookies){
                    if(cookie.getName().compareTo("userId") == 0){
                        userId = Integer.parseInt(cookie.getValue());
                    }
                }
            }
            if(userId == null){
                ajaxResponseList.setCode(205);
                ajaxResponseList.setMsg("用户不存在");
                return ajaxResponseList;
            }
            User user = userService.getUserById(userId);
            if(user == null){
                ajaxResponseList.setCode(205);
                ajaxResponseList.setMsg("用户不存在");
                return ajaxResponseList;
            }
            List<Article> articleList = articleService.getArticleByUserId(userId,pageNum,pageSize);
            if(articleList == null || articleList.isEmpty()){
                ajaxResponseList.setCode(201);
                ajaxResponseList.setMsg("结果为空");
                return ajaxResponseList;
            }
            //int num = articleService.getAllCount();
            int num = articleService.getCountByUserId(userId);
            ajaxResponseList.setNum(num);
            Integer start = (myPageNum-1)*pageSize + 1;
            if(start > num){
                if(num%pageSize == 0){
                    myPageNum = num/pageSize;
                    ajaxResponseList.setPageNum(myPageNum);
                }else{
                    myPageNum = num/pageSize + 1;
                    ajaxResponseList.setPageNum(myPageNum);
                }
            }else{
                ajaxResponseList.setPageNum(myPageNum);
            }
            start = (myPageNum-1)*pageSize + 1;
            ajaxResponseList.setStart(start);
            ajaxResponseList.setEnd((myPageNum-1)*pageSize + articleList.size());
            
            Article article = null;
            ArticleString articleString = null;
            //User user1 = null;
            List<ArticleString> articleStringList = new ArrayList<>();
            
            Iterator<Article> articleIterator = articleList.iterator();
            while (articleIterator.hasNext()){
                article = articleIterator.next();
                articleString = new ArticleString();
                articleString.setId(article.getId());
                articleString.setUserId(article.getUserId());
                /*
                if(article.getUserId() > 0){
                    user = userService.getUserById(article.getUserId());
                    if(user != null){
                        articleString.setUserName(user.getRealName());
                    }else{
                        articleString.setUserName("");
                    }
                }else{
                    articleString.setUserName("");
                }
                */
                articleString.setUserName(user.getRealName());
                articleString.setTitle(article.getTitle());
                articleString.setSubTitle(article.getSubTitle());
                articleString.setPic(article.getPic());
                //articleString.setContent(article.getContent());
                articleString.setHits(article.getHits());
                articleString.setStatus(article.getStatus());
                try {
                    articleString.setCreateTime(sdf.format(article.getCreateTime()));
                }catch (Exception e){
                    articleString.setCreateTime("");
                }
                articleStringList.add(articleString);
            }
            ajaxResponseList.setCode(0);
            ajaxResponseList.setMsg("ok");
            ajaxResponseList.setContent(articleStringList);
            
            
            
        }catch (Exception e){
            //e.printStackTrace();
            ajaxResponseList.setCode(106);
            ajaxResponseList.setMsg("数据处理失败");
        }
        
        
        
        
        return ajaxResponseList;
    }
    
    
    @ResponseBody
    @RequestMapping(value="getArticleById.json",method= RequestMethod.POST)
    public AjaxResponse getArticleById(HttpServletRequest request, Integer id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            
            Article article = articleService.getArticleById(id);
            if(article == null){
                ajaxResponse.setCode(205);
                ajaxResponse.setMsg("获取博客失败");
                return ajaxResponse;
            }
            
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
            /*
            if(article.getUserId() != sessionUserId){
                ajaxResponse.setCode(110);
                ajaxResponse.setMsg("权限不合法");
                return ajaxResponse;
            }
            */
            ArticleString articleString = new ArticleString();
            articleString.setId(article.getId());
            articleString.setUserId(article.getUserId());
            User user = userService.getUserById(article.getUserId());
            if(user != null){
                articleString.setUserName(user.getRealName());
            }else{
                articleString.setUserName("-");
            }
            articleString.setTitle(article.getTitle());
            articleString.setSubTitle(article.getSubTitle());
            articleString.setPic(article.getPic());
            articleString.setContent(article.getContent());
            articleString.setHits(article.getHits());
            articleString.setStatus(article.getStatus());
            try {
                articleString.setCreateTime(sdf.format(article.getCreateTime()));
            }catch (Exception e){
                articleString.setCreateTime("");
            }
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("ok");
            ajaxResponse.setContent(articleString);
        }catch (Exception e){
            ajaxResponse.setCode(106);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    
    @ResponseBody
    @RequestMapping(value="getArticleById2.json",method= RequestMethod.POST)
    public AjaxResponse getArticleById2(HttpServletRequest request, Integer id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userId = null;
        try {
            
            Article article = articleService.getArticleById(id);
            if(article == null){
                ajaxResponse.setCode(205);
                ajaxResponse.setMsg("获取博客失败");
                return ajaxResponse;
            }
            Cookie[] cookies = request.getCookies();
            if(cookies == null){
                ajaxResponse.setCode(206);
                ajaxResponse.setMsg("校验身份失败！");
                return ajaxResponse;
            }else{
                for(Cookie cookie:cookies){
                    if(cookie.getName().compareTo("userId") == 0){
                        userId = cookie.getValue();
                    }
                }
            }
            /*
            if(article.getUserId().toString().compareTo(userId) != 0 ){
                ajaxResponse.setCode(110);
                ajaxResponse.setMsg("权限不合法");
                return ajaxResponse;
            }
            */
            ArticleString articleString = new ArticleString();
            articleString.setId(article.getId());
            articleString.setUserId(article.getUserId());
            User user = userService.getUserById(article.getUserId());
            if(user != null){
                articleString.setUserName(user.getRealName());
            }else{
                articleString.setUserName("-");
            }
            articleString.setTitle(article.getTitle());
            articleString.setSubTitle(article.getSubTitle());
            articleString.setPic(article.getPic());
            articleString.setContent(article.getContent());
            articleString.setHits(article.getHits());
            articleString.setStatus(article.getStatus());
            try {
                articleString.setCreateTime(sdf.format(article.getCreateTime()));
            }catch (Exception e){
                articleString.setCreateTime("");
            }
            ajaxResponse.setCode(0);
            ajaxResponse.setMsg("ok");
            ajaxResponse.setContent(articleString);
        }catch (Exception e){
            ajaxResponse.setCode(106);
            ajaxResponse.setMsg("数据处理失败");
        }
        return ajaxResponse;
    }
    

}
