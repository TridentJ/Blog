<%--
  Created by IntelliJ IDEA.
  User: tridentj
  Date: 2021/09/16
  Time: 18:25
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改邮箱</title>
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
                    <br/>
                    <br/>
                    <br/>
                    <div class="row">
                        <div class="col-sm-offset-3 col-sm-6">
                            <form class="form-horizontal" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="mail" v-model="userInfo.mail">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">年龄</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="age" v-model="userInfo.age">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-sm-10">
                                        <button type="button" class="btn btn-primary col-sm-3" onclick="updateUserInfo();"><i class="icon-save"></i> 提交</button>
                                        <button type="button" class="btn btn-primary col-sm-3 col-sm-offset-1" onclick="updateUserInfo2();"><i class="icon-save"></i> 提交json</button>
                                        <button type="button" class="btn btn-primary col-sm-3 col-sm-offset-1" onclick="test();"><i class="icon-save"></i> 测试</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
    
                    <br/>
                    <div class="row">
                        <div class="col-sm-offset-3 col-sm-6">
                            <form class="form-horizontal" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">json</label>
                                    <div class="col-sm-10">
                                        <textarea id="user-json" rows="10" cols="50"></textarea>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class=" col-sm-10">
                                        <button type="button" class="btn btn-primary col-sm-offset-4 col-sm-3" onclick="updateUserInfo3();"><i class="icon-save"></i> 提交json</button>
                                    </div>
                                </div>
                            </form>
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
                $.ajax({
                    type:"POST",
                    url:"api/updateUserInfo.json",
                    data:{
                        /*"id":3,*/
                        "mail":String($("#mail").val()),
                        "age":$("#age").val(),
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        toastr["error"]("网络出错！请求失败");
                    },
                    success:function(res){
                        if(res.code !== 0){
                            toastr["error"]("修改失败！原因:" + res.msg);

                        }else{
                            toastr["success"]("更新成功！");
                        }
                    }
                });
            }

            function test(){
                var mail = String($("#mail").val())
                var age = $("#age").val()
                var json = {"mail":mail,"age":age}
                console.log(JSON.stringify(json))
            }
            
            function updateUserInfo2(){
                var mail = String($("#mail").val())
                var age = $("#age").val()
                var json = {"mail":mail,"age":age}
                $.ajax({
                    type:"POST",
                    url:"api/updateUserInfo2.json",
                    contentType : "application/json;charset=utf-8",
                    dataType : "json",
                    data:JSON.stringify(json),
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        toastr["error"]("网络出错！请求失败");
                    },
                    success:function(res){
                        if(res.code !== 0){
                            toastr["error"]("修改失败！原因:" + res.msg);

                        }else{
                            toastr["success"]("更新成功！");
                        }
                    }
                });
            }

            function updateUserInfo3(){
                var json = $("#user-json").val()
                $.ajax({
                    type:"POST",
                    url:"api/updateUserInfo2.json",
                    contentType : "application/json;charset=utf-8",
                    dataType : "json",
                    data:JSON.stringify(json),
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        toastr["error"]("网络出错！请求失败");
                    },
                    success:function(res){
                        if(res.code !== 0){
                            toastr["error"]("修改失败！原因:" + res.msg);

                        }else{
                            toastr["success"]("更新成功！");
                        }
                    }
                });
            }
            
        </script>
    
    </body>
</html>