
package com.jyf.blog.dao;

import com.jyf.blog.bean.Article;
import com.jyf.blog.bean.ArticleExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int countByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int deleteByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int insert(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int insertSelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    List<Article> selectByExample(ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Article record);
    
    List<Article> selectByTitle(String title);
    
    
}