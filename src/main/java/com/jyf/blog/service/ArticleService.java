/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/17
 * Time: 15:11
 **/
package com.jyf.blog.service;

import com.jyf.blog.bean.Article;

import java.util.List;

public interface ArticleService {
    
    int addArticle(Article article) throws Exception;
    
    Article getArticleById(Integer id) throws Exception;
    
    int getAllCount() throws Exception;
    
    int getCountByUserId(Integer userId) throws Exception;
    
    List<Article> getAllArticle(int pageNum, int pageSize) throws Exception;
    
    List<Article> getHitsArticle(int pageNum,int pageSize) throws Exception;
    
    List<Article> searchArticleByTitle(String title, int pageNum, int pageSize) throws Exception;
    
    List<Article> getArticleByUserId(int userId,int pageNum,int pageSize) throws Exception;
    
    int editArticle(Article article) throws Exception;
    
    int deleteArticle(Integer id) throws Exception;
    
    
    
}
