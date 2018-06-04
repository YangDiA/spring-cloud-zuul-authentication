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

    ajaxpost.ajax("/sys-api/sys/cate/page",null, {},function (res) {

        if(res.code=="200"){
            $.each(res.data,function(index,item){
                $("#cateNameSelect").append("<option value="+item.id+">"+item.cateName+"</option>");
            })
            form.render();
        }else{

        }
    })

    //监听提交
    form.on('submit(add)', function(data){
        // console.log(data.field);
        $('#add').attr('disabled', true);

        //发异步
        ajaxpost.ajax("/sys-api/sys/apirule/add",null, data.field,function (res) {
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