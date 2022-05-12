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
                            
                            <form name="Form2" action="fileUpload.htm" method="post"  enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-sm-offset-4 col-sm-3">
                                        
                                        <input type="file" id="file" value="" name="file" class="form-control">
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="submit" class="btn btn-success"><i class="icon-search"></i>上传</button>
                                    </div>
                                </div>
                            </form>
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
            
        
            
            
            
        </script>
    
    </body>
</html>