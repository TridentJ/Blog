
function setActive(){
    $("#nav-purchase").addClass("active");
    $("#purchase-cargo-add").addClass("active");
}
setActive();


remarksUE = UE.getEditor("remarks",{
    toolbars: [
        ['fullscreen', 'source']
    ],
    autoHeightEnabled: false,
    readonly:true
});

gPageNum = 1;
pageSize = 10;
$(document).ready(function () {

    /*EditableTable.init();*/
    globalSelectCargoId = 0;
    globalPurchaseId = $("#purchaseId").val();
    if(globalPurchaseId == 0){
        $.ajax({
            type:"POST",
            url:"api/getAllPurchase.json",
            dataType:"json",
            data:{
                'pageNum':gPageNum,
                'pageSize':pageSize
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //$(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
                //$("#myModal").modal('show');
                toastr["error"]("网络出错！请求失败！");
            },
            success:function(res){
                if(res.code !=  0){
                    //$(".modal-body i").html("&nbsp;&nbsp;获取采购订单信息失败！" + res.msg);
                    //$("#myModal").modal('show');
                    toastr["error"]("获取采购订单信息失败！" + res.msg);
                }else{
                    //$("#list-result").removeClass("hidden");
                    //customer = res.content;
                    vBody = new Vue({
                        el:'#table-body',
                        data:{
                            myPurchase:res.content
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
                }
            }
        });
        $("#purchaseModal").modal('show');
    }else if(globalPurchaseId > 0){
        //写入订单信息
        //console.log(globalPurchaseId);
        getPurchaseById(globalPurchaseId);
    }else{
        $(".modal-body i").html("&nbsp;&nbsp;所选择的采购订单不存在！");
        $("#myModal").modal('show');
    }

});




function getAllPurchase(pageNum){
    gPageNum = pageNum;
    $.ajax({
        type:"POST",
        url:"api/getAllPurchase.json",
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
            if(res.code !=  0){
                $(".modal-body i").html("&nbsp;&nbsp;获取采购订单信息失败！" + res.msg);
                $("#myModal").modal('show');
            }else{
                //$("#list-result").removeClass("hidden");
                purchase = res.content;
                len = vBody.myPurchase.length;
                vBody.myPurchase.splice(0,len);
                for(var i=0;i<purchase.length;i++){
                    vBody.myPurchase.push(purchase[i]);
                }
                vPag.start = res.start;
                vPag.end = res.end;
                vPag.all = res.num;
                vPag.myNum = res.pageNum;
                $("#page-num").val(res.pageNum);
                if(res.end == res.num){
                    toastr["success"]("已到达最后一页！");
                }
            }
        }
    });
}

function getPurchaseById(purchaseId){
    $.ajax({
        type:"POST",
        url:"api/getPurchaseById.json",
        dataType:"json",
        data:{
            'id':purchaseId
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            $(".modal-body i").text("&nbsp;&nbsp;网络出错！请求失败");
            $("#myModal").modal('show');
        },
        success:function(res){
            if(res.code !=  0){
                $("#my-modal-body i").text("&nbsp;&nbsp;获取采购订单信息失败！" + res.msg);
                $("#myModal").modal('show');
            }else{

                purchaseShow = res.content;
                purchaseDataShow = new Vue({
                    el:'#purchaseShow',
                    data:{
                        myPurchaseShow:purchaseShow
                    }
                });
                getPurchaseCargoByPurchaseId(purchaseId);
                remarksUE.ready(function () {
                    //remarksUE.setHeight(250);
                    remarksUE.setContent(res.content.remarks);
                });
                $("#purchaseId").val(purchaseId);
                globalPurchaseId = purchaseId;
            }
        }
    });
}

function showCargo(){
    $.ajax({
        type:"POST",
        url:"api/getAllSimpleCargo.json",
        dataType:"json",
        data:{
            'pageNum':1,
            'pageSize':10
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //$(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
            //$("#myModal").modal('show');
            toastr["error"]("网络出错！请求失败！");
        },
        success:function(res){
            if(res.code !=  0){
                //$(".modal-body i").html("&nbsp;&nbsp;获取采购订单信息失败！" + res.msg);
                //$("#myModal").modal('show');
                toastr["error"]("获取采购订单信息失败！" + res.msg);
            }else{

                if(typeof(vCargoBody)=="undefined"){
                    vCargoBody = new Vue({
                        el:'#cargo-table-body',
                        data:{
                            myCargo:res.content
                        }
                    });
                    vCargoPag = new Vue({
                        el:'#pagination-cargo',
                        data:{
                            start:res.start,
                            end:res.end,
                            all:res.num,
                            myNum:1
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
                    cargoInfo = res.content;
                    cargoLen = vCargoBody.myCargo.length;
                    vCargoBody.myCargo.splice(0,cargoLen);
                    for(var i=0;i<cargoInfo.length;i++){
                        vCargoBody.myCargo.push(cargoInfo[i])
                    }
                    vCargoPag.start = res.start;
                    vCargoPag.end = res.end;
                    vCargoPag.all = res.num;
                    vCargoPag.myNum = res.pageNum;
                    if(res.end == res.num){
                        toastr["success"]("已到达最后一页！");
                    }
                }
                //$("#cargo-page-id").val(id);
                $("#cargoInfoModal").modal('show');
            }
        }
    });
}

function getAllCargo(pageNum){
    //cargoPageNum = pageNum;
    $.ajax({
        type:"POST",
        url:"api/getAllSimpleCargo.json",
        dataType:"json",
        data:{
            'pageNum':pageNum,
            'pageSize':10
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //$(".modal-body i").html("&nbsp;&nbsp;网络出错！请求失败");
            //$("#myModal").modal('show');
            toastr["error"]("网络出错！请求失败！");
        },
        success:function(res){
            if(res.code !=  0){
                //$(".modal-body i").html("&nbsp;&nbsp;获取采购订单信息失败！" + res.msg);
                //$("#myModal").modal('show');
                toastr["error"]("获取采购订单信息失败！" + res.msg);
            }else{
                cargoInfo = res.content;
                cargoLen = vCargoBody.myCargo.length;
                vCargoBody.myCargo.splice(0,cargoLen);
                for(var i=0;i<cargoInfo.length;i++){
                    vCargoBody.myCargo.push(cargoInfo[i])
                }
                vCargoPag.start = res.start;
                vCargoPag.end = res.end;
                vCargoPag.all = res.num;
                vCargoPag.myNum = res.pageNum;
                if(res.end == res.num){
                    toastr["success"]("已到达最后一页！");
                }
            }
        }
    });
}

function getPurchaseCargoByPurchaseId(purchaseId){
    $.ajax({
        type:"POST",
        url:"api/getPurchaseCargoByPurchaseId.json",
        dataType:"json",
        data:{
            'pageNum':1,
            'pageSize':0,
            'purchaseId':purchaseId
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败！");
        },
        success:function(res){
            if(res.code ==  0){
                //console.log(res)
                if(typeof(vCargoListBody)=="undefined"){
                    vCargoListBody = new Vue({
                        el:'#cargo-list-table',
                        data:{
                            savedPurchaseCargo:res.content
                        }
                    });

                }else{
                    cargoListInfo = res.content;
                    cargoListLen = vCargoListBody.savedPurchaseCargo.length;
                    vCargoBody.myCargo.splice(0,cargoListLen);
                    for(var i=0;i<cargoListInfo.length;i++){
                        vCargoListBody.savedPurchaseCargo.push(cargoInfo[i])
                    }

                }
            }else if(res.code ==  110){
                //console.log(res)
                if(typeof(vCargoListBody)=="undefined"){
                    vCargoListBody = new Vue({
                        el:'#cargo-list-table',
                        data:{
                            savedPurchaseCargo:null
                        }
                    });
                }else{
                    cargoListInfo = res.content;
                    cargoListLen = vCargoListBody.savedPurchaseCargo.length;
                    vCargoBody.myCargo.splice(0,cargoListLen);
                    for(var i=0;i<cargoListInfo.length;i++){
                        vCargoListBody.savedPurchaseCargo.push(cargoInfo[i])
                    }

                }
                toastr["info"](res.msg);
            }else{
                toastr["error"]("获取采购订单信息失败！" + res.msg);
            }
        }
    });
}


function selectCargo(cargoId){

    gobalCargoId = cargoId;
    if(globalSelectCargoId != 0){
        console.log('selectCargo id:' + globalSelectCargoId);
        var tableRowId = 'purchaseCargoId-' + globalSelectCargoId;
    }else{
        var tableRowId = 'purchaseCargoId-default';
    }
    //var id = $("#updatePurchaseCargoId").val();
    //console.log('selectCargo id:' + id);

    $.ajax({
        type:"POST",
        url:"api/getCargoById.json",
        data:{
            "id":gobalCargoId,
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("查询货物信息失败！原因:" + res.msg);
            }else{
                var data = res.content;
                $("#" + tableRowId + " td:eq(0)").html("<input type='text' class=\"form-control\" onclick='showCargo()' value=\"" + data.name + "|" + data.brand + "|" + data.type + "\"/>");

            }
        }
    });

    $("#cargoInfoModal").modal('hide');
}

function selectCargoRow(cargoId){

    gobalCargoId = cargoId;
    //var id = $("#updatePurchaseCargoId").val();
    //console.log('selectCargo id:' + id);
    var tableRowId = 'purchaseCargoId-default';
    $.ajax({
        type:"POST",
        url:"api/getCargoById.json",
        data:{
            "id":gobalCargoId,
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("查询货物信息失败！原因:" + res.msg);
            }else{
                var data = res.content;
                $("#" + tableRowId + " td:eq(0)").html("<input type='text' class=\"form-control\" onclick='showCargo(" + id + ")' value=\"" + data.name + "|" + data.brand + "|" + data.type + "\"/>");

            }
        }
    });

    $("#cargoInfoModal").modal('hide');
}


function editPurchaseCargo(id){
    globalSelectCargoId = id;
    var tableRowId = 'purchaseCargoId-' + id;
    //$("#updatePurchaseCargoId").val(id);
    //console.log(tableRowId);
    //var content = $("#" + tableRowId).html();
    //console.log(content);
    var info = $("#" + tableRowId + " td:eq(0)").text();
    var price = $("#" + tableRowId + " td:eq(1)").text();
    var num = $("#" + tableRowId + " td:eq(2)").text();
    var express = $("#" + tableRowId + " td:eq(3)").text();
    var status = $("#" + tableRowId + " td:eq(4) button").text();
    if(status == '正常'){
        status = 1;
    }else{
        status = 0;
    }
    var remarks = $("#" + tableRowId + " td:eq(5)").text();
    $("#" + tableRowId + " td:eq(0)").html("<input type='text' class=\"form-control\" onclick='showCargo()' value=\"" + info + "\"/>");
    $("#" + tableRowId + " td:eq(1)").html("<input type='text' class=\"form-control\" id='price' value=\"" + price + "\"/>");
    $("#" + tableRowId + " td:eq(2)").html("<input type='text' class=\"form-control\" id='num' value=\"" + num + "\"/>");
    $("#" + tableRowId + " td:eq(3)").html("<input type='text' class=\"form-control\" id='express' value=\"" + express + "\"/>");
    $("#" + tableRowId + " td:eq(4)").html("<select id=\"status\" class=\"form-control\" value=\"" + status +"\">\n" +
        "                                                <option value=\"0\">冻结</option>\n" +
        "                                                <option value=\"1\" selected>正常</option>\n" +
        "                                            </select>");
    $("#" + tableRowId + " td:eq(5)").html("<input type='text' class=\"form-control\" id='remarks' value=\"" + remarks + "\"/>");
    $("#" + tableRowId + " td:eq(6)").html("<button type=\"button\" class=\"btn btn-primary btn-xs\" onclick='updatePurchaseCargo("+ id +")'>保存</button><button type=\"button\" class=\"btn btn-danger btn-xs\" onclick=\"cancelPurchaseCargo(" + id +")\">取消</button>");
    
}



function deletePurchaseCargo(id){
    var tableRowId = 'purchaseCargoId-' + id;
    //$("#" + tableRowId).html("");

    $.ajax({
        type:"POST",
        url:"api/deletePurchaseCargo.json",
        data:{
            "id":id,
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("删除货物失败！原因:" + res.msg);
            }else{
                //var data = res.content;
                toastr["info"]("删除成功！");
                $("#" + tableRowId).html("");
            }
        }
    });

}

function cancelPurchaseCargo(id){
    var tableRowId = 'purchaseCargoId-' + id;
    var info = $("#" + tableRowId + " td:eq(0) input").val();
    var price = $("#" + tableRowId + " td:eq(1) input").val();
    var num = $("#" + tableRowId + " td:eq(2) input").val();
    var express = $("#" + tableRowId + " td:eq(3) input").val();
    var status = $("#" + tableRowId + " td:eq(4) select").val();
    var remarks = $("#" + tableRowId + " td:eq(5) input").val();
    $("#" + tableRowId + " td:eq(0)").html(info);
    $("#" + tableRowId + " td:eq(1)").html(price);
    $("#" + tableRowId + " td:eq(2)").html(num);
    $("#" + tableRowId + " td:eq(3)").html(express);
    if(status == 1){
        $("#" + tableRowId + " td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">正常</button>");
    }else{
        $("#" + tableRowId + " td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">冻结</button>");
    }
    $("#" + tableRowId + " td:eq(6)").html("<button type=\"button\" class=\"btn btn-primary btn-xs\" title=\"修改\" onclick=\"javascript:editPurchaseCargo(" + id + ");\"><i class=\"icon-pencil\"></i> </button>\n" +
        "                                   <button type=\"button\" class=\"btn btn-danger btn-xs\" title=\"删除\" onclick=\"javascript:deletePurchaseCargo(" + id + ");\"><i class=\"icon-remove\"></i> </button>");
    $("#" + tableRowId + " td:eq(5)").html(remarks);

}




function selectPurchase(purchaseId){
    //console.log(purchaseId);
    $("#purchaseModal").modal('hide');
    getPurchaseById(purchaseId);
}

function updatePurchaseCargo(id){
    var tableRowId = 'purchaseCargoId-' + id;
    //var id = $("#" + tableRowId + " input:eq(0)").val();
    //var cargoId = $("#" + tableRowId + " input:eq(1)").val();
    console.log('id:' + id);
    console.log('gobalCargoId:' + gobalCargoId);
    var info = $("#" + tableRowId + " td:eq(0) input").val();
    var price = $("#" + tableRowId + " td:eq(1) input").val();
    var num = $("#" + tableRowId + " td:eq(2) input").val();
    var express = $("#" + tableRowId + " td:eq(3) input").val();
    var status = $("#" + tableRowId + " td:eq(4) select").val();
    var remarks = $("#" + tableRowId + " td:eq(5) input").val();

    $.ajax({
        type:"POST",
        url:"api/editPurchaseCargo.json",
        data:{
            "id":id,
            'cargoId':gobalCargoId,
            'num':num,
            'price':price,
            'express':express,
            'status':status,
            'remarks':remarks
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("更新订单货物失败！原因:" + res.msg);
            }else{
                toastr["info"]("更新货物信息成功！");
                $("#" + tableRowId + " td:eq(0)").html(info);
                $("#" + tableRowId + " td:eq(1)").html(price);
                $("#" + tableRowId + " td:eq(2)").html(num);
                $("#" + tableRowId + " td:eq(3)").html(express);
                if(status == 1){
                    $("#" + tableRowId + " td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">正常</button>");
                }else{
                    $("#" + tableRowId + " td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">冻结</button>");
                }
                $("#" + tableRowId + " td:eq(6)").html("<button type=\"button\" class=\"btn btn-primary btn-xs\" title=\"修改\" onclick=\"javascript:editPurchaseCargo(" + id + ");\"><i class=\"icon-pencil\"></i> </button>\n" +
                    "                                   <button type=\"button\" class=\"btn btn-danger btn-xs\" title=\"删除\" onclick=\"javascript:deletePurchaseCargo(" + id + ");\"><i class=\"icon-remove\"></i> </button>");
                $("#" + tableRowId + " td:eq(5)").html(remarks);
            }
        }
    });

}

function addPurchaseCargoRow() {
    $("#purchaseCargoId-default td:eq(0)").html("<input type='text' class=\"form-control\" onclick='showCargo()' value=\"\"/>");
    $("#purchaseCargoId-default td:eq(1)").html("<input type='text' class=\"form-control\" id='price' value=\"\"/>");
    $("#purchaseCargoId-default td:eq(2)").html("<input type='text' class=\"form-control\" id='num' value=\"\"/>");
    $("#purchaseCargoId-default td:eq(3)").html("<input type='text' class=\"form-control\" id='express' value=\"\"/>");
    $("#purchaseCargoId-default td:eq(4)").html("<select id=\"status\" class=\"form-control\" value=\"\">\n" +
        "                                                <option value=\"0\">冻结</option>\n" +
        "                                                <option value=\"1\" selected>正常</option>\n" +
        "                                            </select>");
    $("#purchaseCargoId-default td:eq(5)").html("<input type='text' class=\"form-control\" id='remarks' value=\"\"/>");
    $("#purchaseCargoId-default td:eq(6)").html("<button type=\"button\" class=\"btn btn-primary btn-xs\" onclick='addPurchaseCargo()'>保存</button><button type=\"button\" class=\"btn btn-danger btn-xs\" onclick=\"cancelPurchaseCargoRow()\">取消</button>");

}

function addPurchaseCargo(){

    var info = $("#purchaseCargoId-default td:eq(0) input").val();
    var price = $("#purchaseCargoId-default td:eq(1) input").val();
    var num = $("#purchaseCargoId-default td:eq(2) input").val();
    var express = $("#purchaseCargoId-default td:eq(3) input").val();
    var status = $("#purchaseCargoId-default td:eq(4) select").val();
    var remarks = $("#purchaseCargoId-default td:eq(5) input").val();

    console.log('addPurchaseCargo remarks:' + remarks);
    console.log('addPurchaseCargo express:' + express);

    $.ajax({
        type:"POST",
        url:"api/addPurchaseCargo.json",
        data:{
            'purchaseId':globalPurchaseId,
            'cargoId':gobalCargoId,
            'num':num,
            'price':price,
            'express':String(express),
            'status':status,
            'remarks':String(remarks)
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("添加订单货物失败！原因:" + res.msg);
            }else{
                var newId = res.content;
                $("#purchaseCargoId-default td:eq(0)").html(info);
                $("#purchaseCargoId-default td:eq(1)").html(price);
                $("#purchaseCargoId-default td:eq(2)").html(num);
                $("#purchaseCargoId-default td:eq(3)").html(express);
                if(status == 1){
                    $("#purchaseCargoId-default td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">正常</button>");
                }else{
                    $("#purchaseCargoId-default td:eq(4)").html("<button type=\"button\" class=\"btn btn-info btn-xs\">冻结</button>");
                }
                $("#purchaseCargoId-default td:eq(6)").html("<button type=\"button\" class=\"btn btn-primary btn-xs\" title=\"修改\" onclick=\"javascript:editPurchaseCargo(" + newId + ");\"><i class=\"icon-pencil\"></i> </button>\n" +
                    "                                   <button type=\"button\" class=\"btn btn-danger btn-xs\" title=\"删除\" onclick=\"javascript:deletePurchaseCargo(" + newId + ");\"><i class=\"icon-remove\"></i> </button>");
                $("#purchaseCargoId-default td:eq(5)").html(remarks);
                $("#purchaseCargoId-default").attr('id',"purchaseCargoId-" + newId);
                $("#cargo-list-table").append("<tr id=\"purchaseCargoId-default\">\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td></td>\n" +
                    "                                                    <td><button class=\"btn btn-sm btn-primary\" type=\"button\" onclick=\"addPurchaseCargoRow();\">添加</button></td>\n" +
                    "                                                    \n" +
                    "                                                </tr>")
            }
        }
    });

}

function cancelPurchaseCargoRow() {
    $("#purchaseCargoId-default td:eq(0)").html("");
    $("#purchaseCargoId-default td:eq(1)").html("");
    $("#purchaseCargoId-default td:eq(2)").html("");
    $("#purchaseCargoId-default td:eq(3)").html("");
    $("#purchaseCargoId-default td:eq(4)").html("");
    $("#purchaseCargoId-default td:eq(5)").html("");
    $("#purchaseCargoId-default td:eq(6)").html("<button class=\"btn btn-sm btn-primary\" type=\"button\" onclick=\"addPurchaseCargoRow();\">添加</button>");

}


function showDeleteDialog(id){
    gobalDeletePurchaseCargoId = id;
    $("#myDeleteModal").modal('show');
}

function goDelete() {
    $("#myDeleteModal").modal('hide');
    deletePurchaseCargo(gobalDeletePurchaseCargoId);
}

function myDeleteHidden(){
    $("#myDeleteModal").modal('hide');
}

function myHidden(){
    $("#myModal").modal('hide');
}


function addpurchase(){

    $.ajax({
        type:"POST",
        url:"api/addPurchase.json",
        data:{
            "supplierId":String(purchaseVue.purchase.supplierId),
            "selfOrderId":String(purchaseVue.purchase.selfOrderId),
            "supplierOrderId":String(purchaseVue.purchase.supplierOrderId),
            "signTime":$("#signTime").val(),
            "taxPoint":String(purchaseVue.purchase.taxPoint),
            "payMethod":String(purchaseVue.purchase.payMethod),
            "payFlag":purchaseVue.purchase.payFlag,
            "payment":String(purchaseVue.purchase.payment),
            "total":String(purchaseVue.purchase.total),
            "voucher":String(purchaseVue.purchase.voucher),
            "transport":String(purchaseVue.purchase.transport),
            "contactsId":String(purchaseVue.purchase.contactsId),
            "status":String(purchaseVue.purchase.status),
            "remarks":remarks.getContent()
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            toastr["error"]("网络出错！请求失败");
        },
        success:function(res){
            if(res.code != 0){
                toastr["error"]("添加采购订单失败！原因:" + res.msg);
            }else{
                $(".modal-body i").html("&nbsp;&nbsp;添加采购订单成功！");
                $("#myModal").modal('show');

            }
        }
    });
}



