function setActive(){
    $("#nav-vul").addClass("active");
    $("#search-vul").addClass("active");
    $("#search-result").removeClass("hidden");
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

$(function () {
    $('#search-time-start').datepicker({
        language:"zh-CN",
        autoclose: true,
        clearBtn: true, //清除按钮
        todayBtn: false, //今日按钮
        format: "yyyy-mm-dd"
    });
    $('#search-time-end').datepicker({
        language:"zh-CN",
        autoclose: true,
        clearBtn: true, //清除按钮
        todayBtn: false, //今日按钮
        format: "yyyy-mm-dd"
    });
});