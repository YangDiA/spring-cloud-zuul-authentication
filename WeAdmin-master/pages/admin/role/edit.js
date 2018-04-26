layui.extend({
    admin: '{/}../../../static/js/admin',
    laybind: '{/}../../../static/js/laybind'
});

layui.use(['table', 'jquery', 'admin','laybind','laydate'], function() {
    var table = layui.table,
        $ = layui.jquery,
        jQuery=layui.jquery,
        admin = layui.admin,
        laybind= layui.laybind,
        laydate = layui.laydate;

    var bind =  laybinddata($('#bind'),{data:{name:"ddd"}});
    $('#tijiao').on('click', function() {
        var data = bind.getData();
        console.log(data)
    });






});



