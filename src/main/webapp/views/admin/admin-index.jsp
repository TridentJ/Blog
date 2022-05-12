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
    <body>
        <%@ include file="header.jsp" %>
        <%@ include file="nav.jsp" %>
        <section id="main-content" >
            <section class="wrapper site-min-height">
                
                <div class="row">
                    <div class="col-sm-12">
                        <section class="panel">
                            <header class="panel-heading no-border red bold">
                                首页
                            </header>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="alert alert-block alert-info fade in col-sm-offset-4 col-sm-4">
                                        <h4 class="text-center">首页在此....</h4>
                                    </div>
                                </div>

                            </div>
                        </section>
                    </div>
                </div>
                
            </section>
        </section>

        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollTo.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
        <script src="js/common-scripts.js"></script>
        <script src="js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="js/vue.min.js"></script>
        <script src="js/toastr.min.js"></script>
        
        <script>
            function setActive(){
                $("#nav-home").addClass("active");
            }
            setActive();
            
        </script>
    
    </body>
</html>