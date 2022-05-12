<%--
  Created by IntelliJ IDEA.
  User: tridentj
  Date: 2021/04/16
  Time: 10:33
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/bootstrap-reset.css"/>
        <link rel="stylesheet" href="css/font-awesome/font-awesome.css" />
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/my.css"/>
        <link rel="stylesheet" href="css/style-responsive.css"/>
        <link rel="stylesheet" href="css/toastr.min.css"/>
        
    </head>
    <body class="full-width">
    
        <section id="container">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-8">
            <%@ include file="header.jsp" %>
            
            <section id="main-content">
                <section class="wrapper">
    
                    <div class="row">
                        <div class="col-sm-3">
                            <section class="panel">
                                <div class="twt-feed blue-bg">
                                    <h1>信息安全部</h1>
                                    <p>security@xxx.com</p>
                                    <a href="#">
                                        <img src="img/2.jpg" alt="">
                                    </a>
                                </div>
                                <div class="weather-category twt-category">
                                    <ul>
                                        <li class="active">
                                            <h5>20</h5>
                                            博客
                                        </li>
                                        <li>
                                            <h5>12</h5>
                                            关注
                                        </li>
                                        <li>
                                            <h5>246</h5>
                                            粉丝
                                        </li>
                                    </ul>
                                </div>
                                <div class="twt-write col-sm-12">
                                    <textarea class="form-control  t-text-area" rows="2" placeholder="留言联系"></textarea>
                                </div>
                                <footer class="twt-footer">
                                    <button class="btn btn-space btn-white" data-toggle="button">
                                        <i class="icon-thumbs-up"></i>
                                    </button>
                                    <button class="btn btn-space btn-white" data-toggle="button">
                                        <i class="icon-thumbs-down"></i>
                                    </button>
                                    <button class="btn btn-space btn-info pull-right" type="button">
                                        <i class="icon-comments"></i>
                                        留言
                                    </button>
                                </footer>
                            </section>
                            <section class="panel">
                                <div class="twt-feed green-bg">
                                    <h1>安全小白</h1>
                                    <p>tridentj@xxx.com</p>
                                    <a href="#">
                                        <img src="img/1.png" alt="">
                                    </a>
                                </div>
                                <div class="weather-category twt-category">
                                    <ul>
                                        <li class="active">
                                            <h5>13</h5>
                                            博客
                                        </li>
                                        <li>
                                            <h5>34</h5>
                                            关注
                                        </li>
                                        <li>
                                            <h5>657</h5>
                                            粉丝
                                        </li>
                                    </ul>
                                </div>
                                <div class="twt-write col-sm-12">
                                    <textarea class="form-control  t-text-area" rows="2" placeholder="留言联系"></textarea>
                                </div>
                                <footer class="twt-footer">
                                    <button class="btn btn-space btn-white" data-toggle="button">
                                        <i class="icon-thumbs-up"></i>
                                    </button>
                                    <button class="btn btn-space btn-white" data-toggle="button">
                                        <i class="icon-thumbs-down"></i>
                                    </button>
                                    <button class="btn btn-space btn-info pull-right" type="button">
                                        <i class="icon-comments"></i>
                                        留言
                                    </button>
                                </footer>
                            </section>
        
                        </div>
                        
                        
                        <div class="col-sm-6">
    
                            <section class="panel">
                                <header class="panel-heading">
                                    热门博客
                                    
                                </header>
                                <div class="panel-body">
                                    <div class="best-seller hidden" id="hot-blog">
    
                                        <template v-for="item in myHotArticle">
                                            <article class="media">
                                                <a class="pull-left thumb p-thumb">
                                                    <img v-bind:src="item.pic">
                                                </a>
                                                <div class="media-body">
                                                    <a v-bind:href="'showArticle.htm?id=' + item.id" class="p-head">{{item.title}}</a>
                                                    <%--<p>{{item.subTitle}}</p>--%>
                                                    <p>作者:<span class="blue">{{item.userName}}</span>&nbsp;点击量:{{item.hits}}&nbsp;时间:{{item.createTime}}</p>
                                                </div>
                                            </article>
                                        </template>
                                        
                                    </div>
                                </div>
                            </section>
                            <section class="panel">
                                <header class="panel-heading">
                                    最新博客
                                </header>
                                <div class="panel-body">
                                    <div class="best-seller hidden" id="all-blog">
    
                                        <template v-for="item in myArticle">
                                            <article class="media">
                                                <a class="pull-left thumb p-thumb">
                                                    <img v-bind:src="item.pic">
                                                </a>
                                                <div class="media-body">
                                                    <a v-bind:href="'showArticle.htm?id=' + item.id" class="p-head">{{item.title}}</a>
                                                    <p>作者:<span class="blue">{{item.userName}}</span>&nbsp;点击量:{{item.hits}}&nbsp;时间:{{item.createTime}}</p>
                                                </div>
                                            </article>
                                        </template>
                                        
                                    </div>
                                </div>
                            </section>
                        
                        </div>
                        
                        
                        <div class="col-sm-3">
                            <section class="panel">
                                <header class="panel-heading">
                                    动弹
                                    <span class="tools pull-right">
                                <a class="icon-chevron-down" href="javascript:;"></a>
                                <a class="icon-remove" href="javascript:;"></a>
                                </span>
                                </header>
                                <div class="panel-body">
                                    <div class="timeline-messages" id="list-message">
                                        
                                        <template v-for="item in myMessage">
                                            <div class="msg-time-chat">
                                                <a href="#" class="message-img"><img class="avatar" v-bind:src="item.pic" alt=""></a>
                                                <div class="message-body msg-in">
                                                    <span class="arrow"></span>
                                                    <div class="text">
                                                        <p class="attribution"><a href="#">{{item.realName}}</a>{{item.createTime}}</p>
                                                        <%--<div v-html="item.content"></div>--%>
                                                        <p v-html="item.content"><%--{{item.content}}--%></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </template>
                                        
                                    </div>
                                    <div class="chat-form">
                                        <div class="input-cont ">
                                            <input type="text" id="message-input" class="form-control col-lg-12" placeholder="发表动弹">
                                        </div>
                                        <div class="form-group">
                                            <div class="pull-right chat-features">
                                                <a href="javascript:;">
                                                    <i class="icon-camera"></i>
                                                </a>
                                                <a href="javascript:;">
                                                    <i class="icon-link"></i>
                                                </a>
                                                <a class="btn btn-danger" href="javascript:;" onclick="addMessage();">发表</a>
                                            </div>
                                        </div>
            
                                    </div>
                                </div>
                            </section>
    
                            <section class="panel">
                                <header class="panel-heading">
                                    快速链接
                                    <span class="tools pull-right">
                                <a class="icon-chevron-down" href="javascript:;"></a>
                                <a class="icon-remove" href="javascript:;"></a>
                                </span>
                                </header>
                                <div class="panel-body">
                                    <a href="search.htm" class="size-15" target="_blank">搜索内容</a><br/>
                                    <a href="upload.htm" class="size-15" target="_blank">上传附件</a><br/>
                                    <a href="file.htm" class="size-15" target="_blank">下载文件</a><br/>
                                    <%--
                                    <a href="getUserInfo.htm" class="size-15" target="_blank">查看用户</a><br/>
                                    <a href="updateUserInfo.htm" class="size-15" target="_blank">更新用户</a><br/>
                                    --%>
                                    <a href="login2.htm" class="size-15" target="_blank">登录后台2</a><br/>
                                </div>
                            </section>
                            
                        </div>
                        
                        
                        
                    </div>
                    
                    
                </section>
            </section>
                </div>
            </div>
        </section>
        
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollTo.min.js"></script>
        <%--<script src="js/jquery.nicescroll.js" type="text/javascript"></script>--%>
        <script src="js/common-scripts.js"></script>
        <script src="js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="js/vue.min.js"></script>
        <script src="js/toastr.min.js"></script>
        <script src="js/toastr.config.js"></script>
        <script src="js/base64.min.js"></script>
        
        <script>
            $('#search_title').bind('keyup', function(event) {
                if (event.keyCode == "13") {
                    //window.location.href='search.htm?title=' + Base64.encode($("#search_title").val());
                    //window.location.href='search.htm?title=' + $("#search_title").val();
                    if($("#search_title").val() == ""){
                        window.location.href='search.htm';
                    }else{
                        window.location.href='search2.htm?title=' + Base64.encode($("#search_title").val());
                    }
                    
                    //console.log(Base64.encode($("#search_title").val()))
                }
            });


            init_article = {"id":0,"title":"","subTitle":"","pic":null,"content":"","hits":0,"createdBy":"","status":0,"createTime":"2019-01-01 00:00:00"};

            function getArticleByHits(){
                
                $.ajax({
                    type:'post',
                    url:'api/getArticleByHits.json',
                    data:{
                        "pageNum":1,
                        "pageSize":6
                    },
                    success:function (res) {
                        
                        if(res.code == 201){
                            toastr["warning"](res.msg);
                            if(typeof hotArticleVue != 'undefined'){
                                len = hotArticleVue.myHotArticle.length;
                                hotArticleVue.myHotArticle.splice(0,len);
                                hotArticleVue.myHotArticle.push(init_article);
                                if(!$("#hot-blog").hasClass("hidden")){
                                    $("#hot-blog").addClass("hidden");
                                }

                            }
                        }else if(res.code != 0){
                            if(typeof hotArticleVue != 'undefined'){
                                len = hotArticleVue.myArticle.length;
                                hotArticleVue.myHotArticle.splice(0,len);
                                hotArticleVue.myHotArticle.push(init_article);
                                
                                if(!$("#hot-blog").hasClass("hidden")){
                                    $("#hot-blog").addClass("hidden");
                                }
                            }
                            toastr["error"]("读取热门博客数据失败！");
                        }else{
                            if($("#hot-blog").hasClass("hidden")){
                                $("#hot-blog").removeClass("hidden");
                            }
                            article = res.content;
                            console.log('article:' + article);
                            if(typeof hotArticleVue == 'undefined'){
                                hotArticleVue = new Vue({
                                    el:'#hot-blog',
                                    data:{
                                        myHotArticle:article
                                    }
                                });
                            }else{
                                len = hotArticleVue.myHotArticle.length;
                                hotArticleVue.myHotArticle.splice(0,len);
                                for(var i=0;i<article.length;i++){
                                    hotArticleVue.myHotArticle.push(article[i]);
                                }
                            }

                        }
                    }
                });
            }

            function getAllArticle(){

                $.ajax({
                    type:'post',
                    url:'api/getAllArticle.json',
                    data:{
                        "pageNum":1,
                        "pageSize":10
                    },
                    success:function (res) {

                        if(res.code == 201){
                            toastr["warning"](res.msg);
                            if(typeof articleVue != 'undefined'){
                                len = articleVue.myArticle.length;
                                articleVue.myArticle.splice(0,len);
                                articleVue.myArticle.push(init_article);
                                if(!$("#all-blog").hasClass("hidden")){
                                    $("#all-blog").addClass("hidden");
                                }

                            }
                        }else if(res.code != 0){
                            if(typeof articleVue != 'undefined'){
                                len = articleVue.myArticle.length;
                                articleVue.myArticle.splice(0,len);
                                articleVue.myArticle.push(init_article);

                                if(!$("#all-blog").hasClass("hidden")){
                                    $("#all-blog").addClass("hidden");
                                }
                            }
                            toastr["error"]("读取最新博客数据失败！");
                        }else{
                            if($("#all-blog").hasClass("hidden")){
                                $("#all-blog").removeClass("hidden");
                            }
                            article = res.content;
                            if(typeof articleVue == 'undefined'){
                                articleVue = new Vue({
                                    el:'#all-blog',
                                    data:{
                                        myArticle:article
                                    }
                                });
                            }else{
                                len = articleVue.myArticle.length;
                                articleVue.myArticle.splice(0,len);
                                for(var i=0;i<article.length;i++){
                                    articleVue.myArticle.push(article[i]);
                                }
                            }

                        }
                    }
                });
            }



            $(document).ready(function(){
                getArticleByHits();
                getAllArticle();
                getIndexMessage();
            });

            function addMessage(){
                content = $("#message-input").val();
                $.ajax({
                    type:'post',
                    url:'api/addMessage.json',
                    data:{
                        "content":content
                    },
                    success:function (res) {

                        if(res.code != 0){
                            toastr["warning"](res.msg);
                        }else{
                            toastr["success"]("发表成功");
                            getIndexMessage();
                        }
                    }
                });
            }
            


            function getIndexMessage(){

                $.ajax({
                    type:'post',
                    url:'api/getIndexMessage.json',
                    data:{
                    },
                    success:function (res) {

                        if(res.code != 0){
                            toastr["warning"](res.msg);
                        }else{
                            message = res.content;
                            if(typeof messageVue == 'undefined'){
                                messageVue = new Vue({
                                    el:'#list-message',
                                    data:{
                                        myMessage:message
                                    }
                                });
                            }else{
                                len = messageVue.myMessage.length;
                                messageVue.myMessage.splice(0,len);
                                for(var i=0;i<message.length;i++){
                                    messageVue.myMessage.push(message[i]);
                                }
                            }

                        }
                    }
                });
            }
            
            
            
            
        </script>
    
    </body>
</html>