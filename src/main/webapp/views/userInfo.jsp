<%--
  Created by IntelliJ IDEA.
  User: jiyufei
  Date: 2017/12/6
  Time: 10:33
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户信息</title>
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
                
                    <div id="user-info">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <h3><span class="label label-primary">昵称</span>&nbsp;&nbsp;{{userInfo.name}}</h3>
                                <h3><span class="label label-success">姓名</span>&nbsp;&nbsp;{{userInfo.realName}}</h3>
                                <h3><span class="label label-info">邮箱</span>&nbsp;&nbsp;{{userInfo.mail}}</h3>
                                <h3><span class="label label-warning">年龄</span>&nbsp;&nbsp;{{userInfo.age}}</h3>
                                <br/>
                                <button type="button" class="btn btn-primary col-sm-5" onclick="updateUserInfo();"><i class="icon-save"></i> 修改</button>
                            </div>
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
        <script src="js/common-scripts.js"></script>
        <script src="js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="js/vue.min.js"></script>
        <script src="js/toastr.min.js"></script>
        <script src="js/toastr.config.js"></script>
        
        <script>
            
            function updateUserInfo(){
                window.location.href='updateUserInfo.htm';
            }
            
            $(document).ready(function () {
                
                $.ajax({
                    type:"POST",
                    url:"api/getUserInfo.json",
                    dataType:"json",
                    data:{
                    
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        toastr["error"]("网络错误！");
                    },
                    success:function(res){
                        if(res.code !==  0){
                            toastr["error"]("请求出错！" + res.msg);
                        }else{
                            userInfo = res.content;
                            userData = new Vue({
                                el:'#user-info',
                                data:{
                                    myUserInfo:userInfo
                                }
                            });
                        }
                        
                    }
                });
                
            });
        </script>
    
    </body>
</html>