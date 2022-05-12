/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/25
 * Time: 17:29
 **/
package com.jyf.blog.controller;


import com.jyf.blog.bean.Article;
import com.jyf.blog.bean.User;
import com.jyf.blog.service.ArticleService;
import com.jyf.blog.service.UserService;
import com.jyf.blog.util.AjaxResponse;
import com.jyf.blog.util.AjaxResponseList;
import com.jyf.blog.util.AjaxResponseString;
import com.jyf.blog.util.article.ArticleString;
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
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    
    
    @ResponseBody
    @RequestMapping(value="searchByTitle.json",method= RequestMethod.POST)
    public AjaxResponseString searchByTitle(String title, Integer pageNum, Integer pageSize){
        AjaxResponseString ajaxResponseString = new AjaxResponseString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Integer myPageNum = pageNum;
        String newTitle = title;
        if(pageNum == null || pageSize == null){
            ajaxResponseString.setCode(109);
            ajaxResponseString.setMsg("参数不能为空");
            ajaxResponseString.setKeyword(newTitle);
            return ajaxResponseString;
        }
        if(pageNum < 0){
            ajaxResponseString.setCode(120);
            ajaxResponseString.setMsg("页数不能小于0");
            return ajaxResponseString;
        }
        if(pageSize < 0 || pageSize > 50){
            ajaxResponseString.setCode(121);
            ajaxResponseString.setMsg("每页数量不能小于0");
            return ajaxResponseString;
        }
        
        if(title == null || title.compareTo("") == 0){
            ajaxResponseString.setCode(202);
            ajaxResponseString.setMsg("搜索内容不能为空");
            ajaxResponseString.setKeyword(newTitle);
            return ajaxResponseString;
        }
    
        List<Article> articleList = null;
        try {
           /*
            newTitle = title.replaceAll("<","&lt;");
            newTitle = newTitle.replaceAll(">","&gt;");
            */
            articleList = articleService.searchArticleByTitle(newTitle,pageNum,pageSize);
            if(articleList == null || articleList.isEmpty()){
                ajaxResponseString.setCode(201);
                ajaxResponseString.setMsg("结果为空");
                ajaxResponseString.setKeyword(newTitle);
                return ajaxResponseString;
            }
            List<ArticleString> articleStringList = new ArrayList<>();
            Iterator<Article> iterator = articleList.iterator();
            Article article = null;
            ArticleString articleString = null;
            User user = null;
            while (iterator.hasNext()){
                article = iterator.next();
                articleString = new ArticleString();
                articleString.setId(article.getId());
                
                articleString.setUserId(article.getUserId());
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
                articleStringList.add(articleString);
            }
            ajaxResponseString.setCode(0);
            ajaxResponseString.setMsg("ok");
            ajaxResponseString.setKeyword(newTitle);
            ajaxResponseString.setContent(articleStringList);
        }catch (Exception e){
            e.printStackTrace();
            
            ajaxResponseString.setCode(106);
            ajaxResponseString.setKeyword(newTitle);
            ajaxResponseString.setMsg("数据处理失败");
        }
        
        
        return ajaxResponseString;
    }
    
    
    @ResponseBody
    @RequestMapping(value="getArticleByHits.json",method= RequestMethod.POST)
    public AjaxResponseList getArticleByHits(Integer pageNum,Integer pageSize){
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
        if(myPageNum < 1){
            myPageNum = 1;
        }
        try {
            
            List<Article> articleList = articleService.getHitsArticle(pageNum,pageSize);
            if(articleList == null || articleList.isEmpty()){
                ajaxResponseList.setCode(201);
                ajaxResponseList.setMsg("结果为空");
                return ajaxResponseList;
            }
            int num = articleService.getAllCount();
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
            User user = null;
            List<ArticleString> articleStringList = new ArrayList<>();
            
            Iterator<Article> articleIterator = articleList.iterator();
            while (articleIterator.hasNext()){
                article = articleIterator.next();
                articleString = new ArticleString();
                articleString.setId(article.getId());
                articleString.setUserId(article.getUserId());
                if(article.getUserId() > 0){
                    user = userService.getUserById(article.getUserId());
                    if(user != null){
                        articleString.setUserName(user.getRealName());
                        articleString.setPic(user.getPic());
                    }else{
                        articleString.setUserName("");
                        articleString.setPic("img/5.png");
                    }
                }else{
                    articleString.setUserName("");
                    articleString.setPic("img/5.png");
                }
                articleString.setTitle(article.getTitle());
                articleString.setSubTitle(article.getSubTitle());
                //articleString.setPic(article.getPic());
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
            e.printStackTrace();
            ajaxResponseList.setCode(106);
            ajaxResponseList.setMsg("数据库处理失败");
        }
        return ajaxResponseList;
    }
    
    
    @ResponseBody
    @RequestMapping(value="getAllArticle.json",method= RequestMethod.POST)
    public AjaxResponseList getAllArticle(Integer pageNum,Integer pageSize){
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
        if(myPageNum < 1){
            myPageNum = 1;
        }
        try {
        
            List<Article> articleList = articleService.getAllArticle(pageNum,pageSize);
            if(articleList == null || articleList.isEmpty()){
                ajaxResponseList.setCode(201);
                ajaxResponseList.setMsg("结果为空");
                return ajaxResponseList;
            }
            int num = articleService.getAllCount();
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
            User user = null;
            List<ArticleString> articleStringList = new ArrayList<>();
        
            Iterator<Article> articleIterator = articleList.iterator();
            while (articleIterator.hasNext()){
                article = articleIterator.next();
                articleString = new ArticleString();
                articleString.setId(article.getId());
                articleString.setUserId(article.getUserId());
                if(article.getUserId() > 0){
                    user = userService.getUserById(article.getUserId());
                    if(user != null){
                        articleString.setUserName(user.getRealName());
                        articleString.setPic(user.getPic());
                    }else{
                        articleString.setUserName("");
                        articleString.setPic("img/5.png");
                    }
                }else{
                    articleString.setUserName("");
                    articleString.setPic("img/5.png");
                }
                articleString.setTitle(article.getTitle());
                articleString.setSubTitle(article.getSubTitle());
                //articleString.setPic(article.getPic());
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
            e.printStackTrace();
            ajaxResponseList.setCode(106);
            ajaxResponseList.setMsg("数据库处理失败");
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
            
            /*
            Integer sessionUserId = (Integer) request.getSession().getAttribute("userId");
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
    
    

}
