layui.extend({
    admin: '{/}../../../static/js/admin',
    ajaxpost:'{/}../../../static/js/ajaxpost'
});

layui.use(['table', 'jquery', 'admin','laydate','ajaxpost'], function() {
    var table = layui.table,
        $ = layui.jquery,
        admin = layui.admin,
        laydate = layui.laydate,
        ajaxpost = layui.ajaxpost;


    console.log("**********"+$('#dataId').val());
    $('.layui-input-block .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    var active = {

        submit: function(){
            console.log($('#dataId').val());
            console.log($('#authId').val());
            //发异步
            ajaxpost.ajax("/sys-api/sys/role/addRoleMenu",null, {roleId:$('#dataId').val(),menuIds:$('#authId').val()},function (res) {
                if(res.code=="200"){
                    layer.alert("修改成功", {icon: 6},function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        window.parent.location.reload();

                    });

                }else{
                    layer.msg(res.msg,function(){
                    });
                }
            })

        }

    };




});


