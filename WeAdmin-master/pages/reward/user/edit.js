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
        ajaxpost = layui.ajaxpost,
        form = layui.form;

    var editData={};

    var bind;
    var dataId = $('input[id="dataId"]').val();



    ajaxpost.ajax("/reward-api/reward/user/selectById",null, {id:dataId},function (res) {
        if(res.code=="200"){
            editData =res.data
            bind =new laybind.laybinddata($('#bind'),{data:editData});
            $("#status").val(res.data.status);
            form.render('select','selFilter');
        }else{
            layer.msg(res.msg,function(){
            });
        }
    })



    var active = {
        submit: function() {
            var data = bind.getData();
           // data.userId = dataId;
            delete  data.createTime;
            delete data.updateTime;
            data.status = $("#status").val();
            //发异步
            ajaxpost.ajax("/reward-api/reward/user/update",null, data,function (res) {
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



