function setActive(){
    $("#nav-vul").addClass("active");
    $("#list-vul").addClass("active");

}
setActive();

toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "4000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};



gPageNum = parseInt($("#page-num").val());
pageSize = 10;
$.ajax({
    type:"POST",
    url:"../api/getAllOutVul.json",
    /*dataType:"json",*/
    data:{
        'pageNum':gPageNum,
        'pageSize':pageSize
    },
    error:function(XMLHttpRequest, textStatus, errorThrown){
        $("#alert-div").removeClass("hidden");
        $("#alert-msg").html("<h4>错误状态码：" + XMLHttpRequest.status + "</h4><br/><h4>" + textStatus + "</h4>");
    },
    success:function(res){
        if(res.code !=  0){
            $("#alert-div").removeClass("hidden");
            $("#alert-msg").html(res.msg);
        }else{

            $("#table-list").removeClass("hidden");
            vul = res.list;
            //$("#page-num").val == pageNum;
            vBody = new Vue({
                el:'#table-body',
                data:{
                    myVul:vul
                }
            });
            vPag = new Vue({
                el:'#pagination',
                data:{
                    start:res.start,
                    end:res.end,
                    all:res.all,
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
        }
    }
});


function getAllOutVul(pageNum){
    gPageNum = pageNum;
    $.ajax({
        type:"POST",
        url:"../api/getAllOutVul.json",
        data:{
            'pageNum':gPageNum,
            'pageSize':pageSize
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            $("#table-list").addClass("hidden");
            $("#alert-div").removeClass("hidden");
            $("#alert-msg").html("<h4>错误状态码：" + XMLHttpRequest.status + "</h4><br/><h4>" + textStatus + "</h4>");
        },
        success:function(res){
            if(res.code !=  0){
                $("#table-list").addClass("hidden");
                $("#alert-div").removeClass("hidden");
                $("#alert-msg").html(res.msg);
            }else{
                $("#alert-div").addClass("hidden");
                $("#table-list").removeClass("hidden");
                vul = res.list;
                len = vBody.myVul.length;
                vBody.myVul.splice(0,len);
                for(var i=0;i<vul.length;i++){
                    vBody.myVul.push(vul[i]);
                }
                vPag.start = res.start;
                vPag.end = res.end;
                vPag.all = res.all;
                vPag.myNum = res.pageNum;
                $("#page-num").val(res.pageNum);
                if(res.end == res.all){
                    toastr["success"]("已到达最后一页！");
                }
            }
        }
    });
}


function popModal(id){
    $("#myModal").modal('show');
    $("button#btn-delete").click(function () {
        $.ajax({
            type:"POST",
            url:"../api/deleteOutVulById.json",
            data:{
                "id":id
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                toastr["error"]("ajax请求失败！" + textStatus);
            },
            success:function (res) {
                if(res.code == 0){
                    toastr["success"]("删除成功！");
                    getAllOutVul(gPageNum);
                }else{
                    toastr["error"]("删除失败！" + res.msg);
                }

            }
        });
    })
}

function changeStatus(id){
    $.ajax({
        type:"POST",
        url:"../api/changeStatusOutVul.json",
        data:{
            "id":id
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("变更漏洞状态ajax请求失败！" + textStatus);
        },
        success:function (res) {
            if(res.code == 0){
                toastr["success"]("变更漏洞状态成功！");
                getAllOutVul(gPageNum);
            }else{
                toastr["error"]("变更漏洞状态失败！" + res.msg);
            }

        }
    });
}

function editOutVul(id){
    window.location.href='editVul.htm?id='+id;
}