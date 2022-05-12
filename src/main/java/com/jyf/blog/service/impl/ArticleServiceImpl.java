/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 10:19
 **/
package com.jyf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.jyf.blog.bean.Article;
import com.jyf.blog.bean.ArticleExample;
import com.jyf.blog.dao.ArticleMapper;
import com.jyf.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    
    
    @Autowired
    private ArticleMapper articleMapper;
    
    
    @Override
    public int addArticle(Article article) throws Exception {
        int result = articleMapper.insertSelective(article);
        if(result == 1){
            return article.getId();
        }
        return 0;
    }
    
    @Override
    public Article getArticleById(Integer id) throws Exception {
        return articleMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public int getAllCount() throws Exception {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andIdGreaterThan(new Integer(0));
        int result = articleMapper.countByExample(articleExample);
        return result;
    }
    
    @Override
    public int getCountByUserId(Integer userId) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andUserIdEqualTo(userId);
        int result = articleMapper.countByExample(articleExample);
        return result;
    }
    
    @Override
    public List<Article> getAllArticle(int pageNum, int pageSize) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andIdGreaterThan(0);
        criteria.andStatusEqualTo(2);
        articleExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        return articleList;
    }
    
    @Override
    public int editArticle(Article article) throws Exception {
        return articleMapper.updateByPrimaryKeySelective(article);
    }
    
    @Override
    public int deleteArticle(Integer id) throws Exception {
        return articleMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<Article> searchArticleByTitle(String title, int pageNum, int pageSize) throws Exception {
        /*
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andTitleLike("%" + title + "%");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        return articleList;
        */
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.selectByTitle("%" + title + "%");
        return articleList;
    }
    
    @Override
    public List<Article> getHitsArticle(int pageNum, int pageSize) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andStatusEqualTo(new Integer(2));
        articleExample.setOrderByClause("hits desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        return articleList;
    }
    
    @Override
    public List<Article> getArticleByUserId(int userId, int pageNum, int pageSize) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        articleExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        return articleList;
    }
}
