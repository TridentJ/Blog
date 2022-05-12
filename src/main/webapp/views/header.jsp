<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<header class="header white-bg">
    <div class="navbar-header">
        
        <a href="index.htm" class="logo"><img src="img/logo_t1.png" class="header-img"/>&nbsp;<span>XXXX</span> 漏洞演示平台</a>
        <div class="horizontal-menu navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.htm">首页</a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#">博客专题&nbsp;<b class=" icon-angle-down"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">热门博客</a></li>
                        <li><a href="#">最新博客</a></li>
                    </ul>
                </li>
                <li><a href="file.htm">知识库</a></li>
                <li><a href="#">公告</a></li>
                <%--<li><a href="#">动弹</a></li>--%>
                <li><a href="#">留言板</a></li>
                <li><a href="admin/adminIndex.htm">管理后台</a></li>
            
            </ul>
        </div>
        
        <div class="top-nav">
            <ul class="nav pull-right top-menu">
                <li>
                    <input type="text" id="search_title" class="form-control search" placeholder="搜索博客">
                </li>
            </ul>
        </div>
        
    </div>
</header>