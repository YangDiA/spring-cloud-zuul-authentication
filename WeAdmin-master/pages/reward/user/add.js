layui.extend({
    admin: '{/}../../../static/js/admin',
    ajaxpost:'{/}../../../static/js/ajaxpost'
});
layui.use(['form','layer','jquery','admin','ajaxpost'], function(){
    var form = layui.form,
        $ = layui.jquery,
        jQuery=layui.jquery,
        admin = layui.admin,
        layer = layui.layer,
        ajaxpost = layui.ajaxpost;
    //自定义验证规则
    form.verify({
        nikename: function(value){
            if(value.length < 5){
                return '昵称至少得5个字符啊';
            }
        }
        ,password: [/(.+){6,12}$/, '密码必须6到12位']
        ,repass: function(value){
            if($('#L_pass').val()!=$('#L_repass').val()){
                return '两次密码不一致';
            }
        }
    });

    //监听提交
    form.on('submit(add)', function(data){
        console.log(data.field);
        /*console.log(data.field);
        //发异步
        layer.alert("增加成功", {icon: 6},function () {
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
        });*/

        $('#add').attr('disabled', true);

        //发异步
        ajaxpost.ajax("/reward-api/reward/user/add",null, data.field,function (res) {
            console.log(data.field);
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