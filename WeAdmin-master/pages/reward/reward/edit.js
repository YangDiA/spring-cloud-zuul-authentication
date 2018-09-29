layui.extend({
    admin: '{/}../../../static/js/admin',
    laybind: '{/}../../../static/js/laybind',
    ajaxpost:'{/}../../../static/js/ajaxpost'
});

layui.use(['table', 'jquery', 'admin','laybind','laydate','ajaxpost'], function() {
    var table = layui.table,
        $ = layui.jquery,
        jQuery=layui.jquery,
        admin = layui.admin,
        laybind= layui.laybind,
        laydate = layui.laydate,
        ajaxpost = layui.ajaxpost;
    laydate.render({
        elem: '#senddate' //指定元素
        ,format: 'yyyyMMdd'
    });

    var editData={};

    var bind;
    var dataId = $('input[name="dataId"]').val();
    ajaxpost.ajax("/reward-api/reward/reward/selectById",null, {id:dataId},function (res) {
        if(res.code=="200"){
            editData =res.data
            bind =new laybind.laybinddata($('#bind'),{data:editData});
        }else{
            layer.msg(res.msg,function(){
            });
        }
    })



    var active = {
        submit: function() {
            var data = bind.getData();
            delete  data.createTime;
            data.senddate = $("#senddate").val();
            //发异步
            ajaxpost.ajax("/reward-api/reward/reward/update",null, data ,function (res) {
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

    $('.layui-input-block .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });




});



