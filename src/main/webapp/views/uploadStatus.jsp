<%--
  Created by IntelliJ IDEA.
  User: tridentj
  Date: 2021/09/16
  Time: 10:33
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>上传附件</title>
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
    
                            <input type="hidden" id="status" value="${status}"/>
                            <div class="row">
                                <div id="message"  class="col-sm-offset-4 col-sm-3">
                                
                                </div>
                            </div>
                            
                            
                        </div>
                    </section>
                    
                    
                    
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
            $(document).ready(function () {
                status = $("#status").val();
                if(status == 1){
                    $("#message").html("<h2>上传成功</h2>");
                }else if(status == 2){
                    $("#message").html("<h2>上传失败</h2>");
                }else if(status == 3){
                    $("#message").html("<h2>序列化成功</h2>");
                }else if(status == 4){
                    $("#message").html("<h2>序列化失败</h2>");
                }else if(status == 5){
                    $("#message").html("<h2>文件已存在</h2>");
                }
                
            });
            
            
            
        </script>
    
    </body>
</html>