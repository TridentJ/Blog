<%--
  Created by IntelliJ IDEA.
  User: tridentj
  Date: 2021/04/16
  Time: 10:33
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<aside>
    <div id="sidebar"  class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <li>
                <a href="adminIndex.htm" id="nav-home">
                    <i class="icon-home"></i>
                    <span>主页</span>
                </a>
            </li>
    
            <li class="sub-menu">
                <a href="javascript:" id="nav-article" >
                    <i class="icon-file-text-alt"></i>
                    <span>博客管理</span>
        
                </a>
                <ul class="sub">
                    <li id="article-info"><a  href="listArticle.htm">博客列表</a></li>
                    <li id="article-add"><a  href="addArticle.htm">添加博客</a></li>
                    <li id="article-search"><a  href="searchArticle.htm">搜索博客</a></li>
                    
                </ul>
            </li>
            
            
            <li class="sub-menu">
                <a href="javascript:" id="nav-message">
                    <i class="icon-envelope-alt"></i>
                    <span>留言管理</span>
                </a>
                <ul class="sub">
                    <li id="message-info"><a  href="listMessage.htm">留言列表</a></li>
                    <li id="message-add"><a  href="addMessage.htm">添加留言</a></li>
                    <li id="message-search"><a  href="searchMessage.htm">搜索留言</a></li>
                    
                </ul>
            </li>
    
            <li class="sub-menu">
                <a href="javascript:" id="nav-notice" >
                    <i class="icon-comments-alt"></i>
                    <span>动弹管理</span>
                </a>
                <ul class="sub">
                    <li id="notice-info"><a  href="listNotice.htm">动弹列表</a></li>
                    <li id="notice-search"><a  href="searchNotice.htm">搜索动弹</a></li>
                    
                </ul>
            </li>
            
            
            
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>