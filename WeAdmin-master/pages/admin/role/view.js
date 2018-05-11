layui.extend({
    admin: '{/}../../../static/js/admin',
    ajaxpost:'{/}../../../static/js/ajaxpost'
});

layui.use(['table', 'jquery', 'admin','laydate','laytpl','ajaxpost'], function() {
    var table = layui.table,
        $ = layui.jquery,
        admin = layui.admin,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        ajaxpost = layui.ajaxpost;



    var data = { //数据
        /*"title":"Layui常用模块",
        "roleName":"roleName角色名称123",
        "roleDesc":"roleDesc描述"*/

    }
    var dataId = $('input[name="dataId"]').val();
    ajaxpost.ajax("http://localhost/sys-api/sys/role/selectById",null, {id:dataId},function (res) {
        if(res.code=="200"){
            data =res.data
            var getTpl = demo.innerHTML
                ,view = document.getElementById('view');
            laytpl(getTpl).render(data, function(html){
                view.innerHTML = html;
            });
        }else{
            layer.msg(res.msg,function(){
            });
        }
    })









});

