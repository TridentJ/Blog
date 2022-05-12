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
    <title>我的博客</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/bootstrap-reset.css"/>
    <link rel="stylesheet" href="../css/font-awesome/font-awesome.css" />
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../css/my.css"/>
    <link rel="stylesheet" href="../css/style-responsive.css"/>
    <link rel="stylesheet" href="../css/toastr.min.css"/>
</head>
<body>
<%@ include file="../header.jsp" %>
<%@ include file="../nav.jsp" %>
<section id="main-content" >
    <section class="wrapper site-min-height">
        
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading no-border red bold">
                        博客详情
                    </header>
                    <input type="hidden" id="id" value="${id}"/>
                    <div class="panel-body editable-table hidden" id="article-detail">
                        <div class="row">
                            <div class="col-sm-12">
                                
                                <h1 class="text-center"><span class="label label-info">{{article.title}}</span></h1>
                                <h3 class="text-center"><span class="label label-primary">{{article.subTitle}}</span></h3>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-4 col-sm-4">
                                <h4>作者:{{article.userName}}&nbsp;&nbsp;点击量:{{article.hits}}
                                    &nbsp;&nbsp;时间:{{article.createTime}}
                                    <template v-if="article.status === 1"><span class="label label-warning">未发布</span></template>
                                    <template v-else-if="article.status === 2"><span class="label label-info">已发布</span></template>
                                    <template v-else><span class="label label-default">-</span></template>
                                </h4>
                            </div>
                            
                        </div>
    
                        <div class="row">
                            <div class="col-sm-offset-2 col-sm-8">
                                {{article.content}}
                            </div>
                        </div>
                        
                    
                    </div>
                </section>
            </div>
        </div>
        
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">提示</h4>
                    </div>
                    <div class="modal-body">
                        <h4><i class="icon-info">&nbsp;&nbsp;</i></h4>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-success" data-dismiss="modal" type="button">确定</button>
                    </div>
                </div>
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
<script src="js/toastr.config.js"></script>

<script>
    function setActive(){
        $("#nav-article").addClass("active");
        $("#article-info").addClass("active");
    }
    setActive();

    gPageNum = parseInt($("#page-num").val());
    pageSize = 10;
    $(document).ready(function () {
        id = parseInt($("#id").val());
        if(isNaN(id)){
            $(".modal-body i").text("&nbsp;&nbsp;读取博客失败！");
            $("#myModal").modal('show');
        }else{
            $.ajax({
                type:"POST",
                url:"api/getArticleById2.json",
                dataType:"json",
                data:{
                    'id':id
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    $(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
                    $("#myModal").modal('show');
                },
                success:function(res){
                    if(res.code !=  0){
                        $(".modal-body i").html("&nbsp;&nbsp;读取博客失败！" + res.msg);
                        $("#myModal").modal('show');
                        //toastr["error"]("读取博客失败！原因:" + res.msg);
                    }else{
                        //$("#purchase").removeClass("hidden");
                        if($("#article-detail").hasClass("hidden")){
                            $("#article-detail").removeClass("hidden");
                        }
                        article = res.content;
                        articleData = new Vue({
                            el:'#article-detail',
                            data:{
                                myArticle:article
                            }
                        });
                        
                    }
                }
            });
        }
    });
    

    function editArticle(id){
        window.location.href='editArticle.htm?id=' + id;
    }

    function deleteArticle(id){
        window.location.href='deleteArticle.htm?id=' + id;
    }

</script>

</body>
</html>