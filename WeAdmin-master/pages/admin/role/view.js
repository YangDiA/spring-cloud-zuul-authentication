layui.extend({
    admin: '{/}../../../static/js/admin'
});

layui.use(['table', 'jquery', 'admin','laydate','laytpl'], function() {
    var table = layui.table,
        $ = layui.jquery,
        admin = layui.admin,
        laydate = layui.laydate,
        laytpl = layui.laytpl;


    var role = {"roleName":"eee"};



    var data = { //数据
        "title":"Layui常用模块",
        "roleName":"roleName角色名称"

    }
    var getTpl = demo.innerHTML
        ,view = document.getElementById('view');
    laytpl(getTpl).render(data, function(html){
        view.innerHTML = html;
    });





});

