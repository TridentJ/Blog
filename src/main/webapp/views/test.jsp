<%--
  Created by IntelliJ IDEA.
  User: tridentj
  Date: 2021/04/16
  Time: 10:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>测试API</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style type="text/css">
            .title {
                color:red;
                font-size:32px;
            }
            .second-title {
                color:blueviolet;
                font-size:24px;
            }
        </style>
    </head>
    <body>
        <div clas="row">
            <div class="col-md-12">
                <label class="title">文章模块</label><br/>
                ----------------------------------------------------------<br/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="second-title">根据id获取文章信息</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <input type="text" id="get-id">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-primary col-md-4" onclick="getBankById()">提交</button>
                    </div>
                </div>
                ----------------------------------------------------------<br/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="second-title">根据title搜索文章</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <input type="text" id="title" name="title">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-primary col-md-4" onclick="searchByTitle()">搜索</button>
                    </div>
                </div>
                <%--
                ----------------------------------------------------------<br/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="second-title">获取文章列表</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <button type="button" class="btn btn-primary col-md-4" onclick="getAllBank()">获取列表</button>
                    </div>
                </div>
    
                ----------------------------------------------------------<br/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="second-title">修改银行名称</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <input type="text" id="upate-id" placeholder="id">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <input type="text" id="upate-name" placeholder="新名称">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <button type="button" class="btn btn-primary col-md-4" onclick="editBank()">修改</button>
                    </div>
                </div>
                --%>
    
                <div clas="row">
                    <div class="col-md-12">
                        <label class="title">fastjson</label><br/>
                        ----------------------------------------------------------<br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="second-title">提交Json</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <textarea rows="10" id="update-json">
                                </textarea>
                            </div>
                            <div class="col-md-4"><button type="button" class="btn btn-primary col-md-4" onclick="updateUserInfo3()">提交</button>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
                
            </div>
        </div>
        
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            
            function getBankById(){
                $.ajax({
                    type:'post',
                    url:'api/getArticleById.json',
                    data:{
                        "id":$("#get-id").val()
                    },
                    success:function (res) {
                        console.log(res)
                    }
                });
            }
            function searchByTitle(){
                $.ajax({
                    type:'post',
                    url:'api/searchByTitle.json',
                    data:{
                        "title":$("#title").val(),
                        "pageNum":1,
                        "pageSize":0
                    },
                    success:function (res) {
                        console.log(res)
                    }
                });
            }
            function getAllBank(){
                $.ajax({
                    type:'post',
                    url:'getAllBank.json',
                    data:{
                        "pageNum":1,
                        "pageSize":10
                    },
                    success:function (res) {
                        console.log(res)
                    }
                });
            }
            function editBank(){
                $.ajax({
                    type:'post',
                    url:'editBank.json',
                    data:{
                        "id":$("#upate-id").val(),
                        "name":$("#upate-name").val()
                    },
                    success:function (res) {
                        console.log(res)
                    }
                });
            }

            function updateUserInfo3(){
                //var mail = String($("#mail").val())
                //var json = {"mail":mail,"age":age}
                var json = $("#update-json").val()
                $.ajax({
                    type:"POST",
                    url:"api/updateUserInfo4.json",
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
