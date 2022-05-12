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
                        我的博客
                    </header>
                    <div class="panel-body editable-table hidden" id="list-result">
                        
                        <input type="hidden" id="page-num" value="${pageNum}"/>
                        <table class="table table-striped" id="table-purchase">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>标题</th>
                                <th>副标题</th>
                                <th>封面图</th>
                                <th>点击量</th>
                                <th>状态</th>
                                <th>发布时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="table-body">
                            <template v-for="item in myArticle">
                                <tr>
                                    <td>{{item.id}}</td>
                                    <td><a v-bind:href="'showArticle.htm?id=' + item.id" target="_blank">{{item.title}}</a></td>
                                    <td>{{item.subTitle}}</td>
                                    <td>{{item.pic}}</td>
                                    <td>{{item.hits}}</td>
                                    <td v-if="item.status === 1"><button type="button" class="btn btn-warning btn-xs">未发布</button></td>
                                    <td v-else-if="item.status === 2"><button type="button" class="btn btn-info btn-xs">已发布</button></td>
                                    <td v-else><button type="button" class="btn btn-default btn-xs">-</button></td>
                                    <td>{{item.createTime}}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs" title="查看" :onclick="'javascript:showArticle(' + item.id + ');'"><i class="icon-search"></i> </button>
                                        <button type="button" class="btn btn-primary btn-xs" title="修改" :onclick="'javascript:editArticle(' + item.id + ');'"><i class="icon-pencil"></i> </button>
                                        <button type="button" class="btn btn-danger btn-xs" title="删除" :onclick="'javascript:deleteArticle(' + item.id + ',1);'"><i class="icon-trash"></i> </button>
                                    </td>
                                </tr>
                            </template>
                            </tbody>
                        </table>
                        
                        <div class="row" id="pagination">
                            <div class="col-sm-2">
                                <span id="pagination-header">当前{{start}}-{{end}}条，共{{all}}条记录</span>
                            </div>
                            <div class="col-sm-offset-7 col-sm-3">
                                <ul class="pagination pagination-sm pull-right" id="pagination-body">
                                    <template v-if="myNum === 1">
                                        <li class="disabled"><a href="#">«</a></li>
                                        <li class="active"><a href="#">1</a></li>
                                        <li><a href="javascript:getArticleByUserId(2)">2</a></li>
                                        <li><a href="javascript:getArticleByUserId(3)">3</a></li>
                                        <li><a href="javascript:getArticleByUserId(4)">4</a></li>
                                        <li><a href="javascript:getArticleByUserId(5)">5</a></li>
                                        <li><a href="javascript:getArticleByUserId(2)">»</a></li>
                                    </template>
                                    <template v-else-if="myNum === 2">
                                        <li><a href="javascript:getArticleByUserId(1)">«</a></li>
                                        <li><a href="javascript:getArticleByUserId(1)">1</a></li>
                                        <li class="active"><a href="#">2</a></li>
                                        <li><a href="javascript:getArticleByUserId(3)">3</a></li>
                                        <li><a href="javascript:getArticleByUserId(4)">4</a></li>
                                        <li><a href="javascript:getArticleByUserId(5)">5</a></li>
                                        <li><a href="javascript:getArticleByUserId(3)">»</a></li>
                                    </template>
                                    <template v-else>
                                        <li><a :href="'javascript:getArticleByUserId(' + pageNumI1 + ')'">«</a></li>
                                        <li><a :href="'javascript:getArticleByUserId('+ pageNumI2 + ')'">{{pageNumI2}}</a></li>
                                        <li><a :href="'javascript:getArticleByUserId('+ pageNumI1 + ')'">{{pageNumI1}}</a></li>
                                        <li class="active"><a href="#">{{myNum}}</a></li>
                                        <li><a :href="'javascript:getArticleByUserId('+ pageNumA1 + ')'">{{pageNumA1}}</a></li>
                                        <li><a :href="'javascript:getArticleByUserId('+ pageNumA2 + ')'">{{pageNumA2}}</a></li>
                                        <li><a :href="'javascript:getArticleByUserId('+ pageNumA1 + ')'">»</a></li>
                                    </template>
                                </ul>
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

<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.scrollTo.min.js"></script>
<script src="../js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="../js/common-scripts.js"></script>
<script src="../js/jquery.dcjqaccordion.2.7.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/toastr.min.js"></script>
<script src="js/toastr.config.js"></script>

<script src="../js/toastr.config.js"></script>

<script>
    function setActive(){
        $("#nav-article").addClass("active");
        $("#article-info").addClass("active");
    }
    setActive();

    gPageNum = parseInt($("#page-num").val());
    pageSize = 10;
    $(document).ready(function () {
        $.ajax({
            type:"POST",
            url:"api/getArticleByUserId.json",
            dataType:"json",
            data:{
                'pageNum':gPageNum,
                'pageSize':pageSize
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
                $("#myModal").modal('show');
            },
            success:function(res){
                if(res.code ===  201){
                    toastr["info"](res.msg);
                }else if(res.code  != 0){
                    $(".modal-body i").html("&nbsp;&nbsp;读取博客列表失败！" + res.msg);
                    $("#myModal").modal('show');
                }else{
                    if($("#list-result").hasClass("hidden")){
                        $("#list-result").removeClass("hidden");
                    }
                    
                    if(typeof vBody === 'undefined'){
                        vBody = new Vue({
                            el:'#table-body',
                            data:{
                                myArticle:res.content
                            }
                        });
                        vPag = new Vue({
                            el:'#pagination',
                            data:{
                                start:res.start,
                                end:res.end,
                                all:res.num,
                                myNum:gPageNum
                            },
                            computed:{
                                pageNumA1:function(){
                                    return this.myNum + 1;
                                },
                                pageNumA2:function(){
                                    return this.myNum + 2;
                                },
                                pageNumI1:function(){
                                    return this.myNum - 1;
                                },
                                pageNumI2:function(){
                                    return this.myNum - 2;
                                }
                            },
                        });
                    }else{
                        article = res.content;
                        len = vBody.myArticle.length;
                        vBody.myArticle.splice(0,len);
                        for(var i=0;i<article.length;i++){
                            vBody.myArticle.push(article[i]);
                        }
                    }
                }
            }
        });
    });
    

    function getArticleByUserId(pageNum){
        gPageNum = pageNum;
        $.ajax({
            type:"POST",
            url:"api/getArticleByUserId.json",
            dataType:"json",
            data:{
                'pageNum':gPageNum,
                'pageSize':pageSize
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                $(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
                $("#myModal").modal('show');
            },
            success:function(res){
                if(res.code ===  201){
                    toastr["info"](res.msg);
                }else if(res.code  != 0){
                    $(".modal-body i").html("&nbsp;&nbsp;读取博客列表失败！" + res.msg);
                    $("#myModal").modal('show');
                }else{
                    if($("#list-result").hasClass("hidden")){
                        $("#list-result").removeClass("hidden");
                    }
                    article = res.content;
                    len = vBody.myArticle.length;
                    vBody.myArticle.splice(0,len);
                    for(var i=0;i<article.length;i++){
                        vBody.myArticle.push(article[i]);
                    }
                    vPag.start = res.start;
                    vPag.end = res.end;
                    vPag.all = res.num;
                    vPag.myNum = res.pageNum;
                    $("#page-num").val(res.pageNum);
                    if(res.end === res.num){
                        toastr["success"]("已到达最后一页！");
                    }
                    
                }
            }
        });
        
    }

    function showArticle(id){
        window.location.href='showArticle.htm?id=' + id;
    }

    function editArticle(id){
        window.location.href='editArticle.htm?id=' + id;
    }

    function deleteArticle(id){
        window.location.href='deleteArticle.htm?id=' + id;
    }

</script>

</body>
</html>