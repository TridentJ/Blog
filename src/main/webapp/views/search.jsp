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
        <title>搜索博客</title>
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
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
    
        <section id="container">
    
            <%@ include file="header.jsp" %>
            
            <section id="main-content">
                <section class="wrapper">
                    <section class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-offset-3 col-sm-5">
                                    
                                    <input type="text" id="search-input" value="" placeholder="搜索博客" class="form-control">
                                </div>
                                <div class="col-sm-1">
                                    <button type="button" class="btn btn-success" onclick="searchByTitle()"><i class="icon-search"></i>搜索</button>
                                </div>
                            </div>
                        </div>
                    </section>
                    
                    <div class="row">
                        <div class="col-sm-12">
                            <section class="panel">
                                <br/>
                                <h4>&nbsp;&nbsp;&nbsp;&nbsp;搜索的关键字:<span class="red" id="search-keyword"></span></h4>
                                <%--<input type="hidden" value="${title}" id="title"/>--%>
                                <div class="panel-body" >
    
                                    <table class="table" id="table-body">
                                        <tbody>
                                            <template v-for="item in myArticle">
                                                
                                                <tr>
                                                    <td><a v-bind:href="'showArticle.htm?id=' + item.id">{{item.title}}</a></td>
                                                    <td>{{item.userName}}</td>
                                                    <td>{{item.createTime}}</td>
                                                </tr>
                                            </template>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            
                        </div>
                    </div>
                    
                </section>
            </section>
        </section>
        </div>
        </div>
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollTo.min.js"></script>
        <%--<script src="js/jquery.nicescroll.js" type="text/javascript"></script>--%>
        <script src="js/common-scripts.js"></script>
        <script src="js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="js/vue.min.js"></script>
        <script src="js/toastr.min.js"></script>
        <script src="js/toastr.config.js"></script>
        
        <script>
            
            init_article = {"id":0,"title":"","subTitle":"","pic":null,"content":"","hits":0,"createdBy":"","status":0,"createTime":"2019-01-01 00:00:00"};
            
            function searchByTitle(){
                title = $("#search-input").val();
                //console.log('start:' + title);
                
                //document.getElementById("search-keyword").innerHTML = title;
                //$("#search-keyword").html("<script>alert(/xss/)<\/script>");
                
                $.ajax({
                    type:'post',
                    url:'api/searchByTitle.json',
                    data:{
                        "title":title,
                        "pageNum":1,
                        "pageSize":0
                    },
                    success:function (res) {
                        console.log('success:' + title);
                        $("#search-keyword").html(res.keyword);
                        if(res.code == 201 || res.code == 202){
                            toastr["warning"](res.msg);
                            if(typeof articleVue != 'undefined'){
                                len = articleVue.myArticle.length;
                                articleVue.myArticle.splice(0,len);
                                articleVue.myArticle.push(init_article);
                                if(!$("#table-body").hasClass("hidden")){
                                    $("#table-body").addClass("hidden");
                                }
                                
                            }
                        }else if(res.code != 0){
                            if(typeof articleVue != 'undefined'){
                                len = articleVue.myArticle.length;
                                articleVue.myArticle.splice(0,len);
                                articleVue.myArticle.push(init_article);
                                
                                if(!$("#table-body").hasClass("hidden")){
                                    $("#table-body").addClass("hidden");
                                }
                            }
                            toastr["error"]("搜索失败！");
                        }else{
                            if($("#table-body").hasClass("hidden")){
                                $("#table-body").removeClass("hidden");
                            }
                            article = res.content;
                            if(typeof articleVue == 'undefined'){
                                articleVue = new Vue({
                                    el:'#table-body',
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
            /*
            $(document).ready(function () {
                searchByTitle();
            });
            */
            $('#search-input').bind('keyup', function(event) {
                if (event.keyCode == "13") {
                    searchByTitle();
                }
            });
            /*
            $('#search_title').bind('keyup', function(event) {
                if (event.keyCode == "13") {
                    searchByTitle();
                }
            });
            */
            
            
            
        </script>
    
    </body>
</html>