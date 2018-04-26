layui.extend({
    admin: '{/}../../../static/js/admin'
});

layui.use(['table', 'jquery', 'admin','laydate'], function() {
    var table = layui.table,
        $ = layui.jquery,
        jQuery=layui.jquery,
        admin = layui.admin,
        laydate = layui.laydate;


    var initData = {roleName:'张三'};
    var bind = new laybind($('#bind'), {data:initData});







});



