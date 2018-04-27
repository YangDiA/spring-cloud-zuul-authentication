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

    var editData={roleName:"ddd",roleDesc:""};

    editData.roleName="dfa2r";
    var bind =new laybind.laybinddata($('#bind'),{data:editData});

    var active = {
        submit: function() {
            var data = bind.getData();
            console.log(data);
            console.log(editData);
        }
    };

    $('.layui-input-block .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });




});



