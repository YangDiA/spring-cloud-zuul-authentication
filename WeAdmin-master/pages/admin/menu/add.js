layui.extend({
    admin: '{/}../../../static/js/admin',
    ajaxpost:'{/}../../../static/js/ajaxpost'
});
layui.use(['form', 'jquery','layer','admin','ajaxpost'], function(){
    var form = layui.form,
        admin = layui.admin,
        $ = layui.jquery,
        layer = layui.layer,
        ajaxpost = layui.ajaxpost;

    //自定义验证规则
    form.verify({
        code: function(value){
            if(value ==''){
                return '编码不能为空';
            }
        },
        menuName :function(value){
            if(value ==''){
                return '菜单名称不能为空';
            }
        }

    });

    //监听提交
    form.on('submit(add)', function(data){
        console.log(data.field);
        $('#add').attr('disabled', true);

        //发异步
        ajaxpost.ajax("/sys-api/sys/menu/add",null, data.field,function (res) {
            if(res.code=="200"){

                layer.msg("增加成功", {icon: 6,time:500},function () {
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


        return false;
    });

});