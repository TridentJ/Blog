function setActive(){
    $("#nav-vul").addClass("active");
    $("#search-vul").addClass("active");
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

var id  = $("#vul-id").val();
function getOutVulById(){
    if(id == undefined || id == ""){
        /*$("#vul-details").addClass("hidden");
        $("#alert-div").removeClass("hidden");
        $("#alert-msg").html("<h4>出错原因：缺少参数id</h4>");*/
        toastr["error"]("获取漏洞详情失败！原因：缺少参数ID" );
    }else{
        $("vul-details").removeClass("hidden");
        $.ajax({
            type:"POST",
            url:"../api/getOutVulById.json",
            data:{
                "id":id
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                /*
                $("#vul-details").addClass("hidden");
                $("#alert-div").removeClass("hidden");
                $("#alert-msg").html("<h4>错误状态码：" + XMLHttpRequest.status + "</h4><br/><h4>" + textStatus + "</h4>");
                */
                toastr["error"]("获取漏洞详情失败！原因：" + textStatus );
            },
            success:function(res){
                if(res.code != 0){
                    /*
                    $("#vul-details").addClass("hidden");
                    $("#alert-div").removeClass("hidden");
                    $("#alert-msg").html(res.msg);
                    */
                    toastr["error"]("获取漏洞详情失败！原因：" + res.msg);
                }else{
                    $("#alert-div").addClass("hidden");
                    $("#vul-details").removeClass("hidden");
                    res.content.affect =  Base64.decode(res.content.affect);
                    res.content.content = Base64.decode(res.content.content);
                    res.content.fix  = Base64.decode(res.content.fix);
                    var vue =  new Vue({
                        el:'#vul-details',
                        data:{
                            outVul:res.content
                        }
                    });
                }
            }
        });
    }
}
getOutVulById();
