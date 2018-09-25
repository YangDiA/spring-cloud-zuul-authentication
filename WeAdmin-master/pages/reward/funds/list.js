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



    function dateFormat(v){
        var date = new Date(v);
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        m = m<10?'0'+m:m;
        var d = date.getDate();
        d = d<10?("0"+d):d;
        var h = date.getHours();
        h = h<10?("0"+h):h;
        var M = date.getMinutes();
        M = M<10?("0"+M):M;
        var str = y+"-"+m+"-"+d+" "+h+":"+M;
        return str;
    }

    var  tabledata = [ {

    } ];
    table.render({
        elem: '#list',
        cellMinWidth: 80,
        url:"/reward-api/reward/funds/page",
        method:"post",
        headers: {Authorization: localStorage.getItem('Authorization')},
        response: {
            statusCode: 200 //成功的状态码，默认：0
        },
        cols: [
            [{
                type: 'checkbox'
            }, {
                field: 'fundsId',title: '流水ID',sort: true
            }, {
                field: 'userId',title: '会员ID',sort: true
            }, {
                field: 'amount',title: '金额',sort: true
            }, {
                field: 'type',title: '类型',sort: true,templet:function (row) {
                    var s = "";
                    if(row.status==1){
                        s = "充值";
                    }else if(row.status==2){
                        s = "提现";
                    }else if(row.status==3){
                        s = "推荐奖金";
                    }else{
                        s = "团队奖金";
                    }
                    return s;
                }

            }, {
                field: 'status',title: '状态',sort: true,templet:function (row) {
                    var s = "";
                    if(row.status==1){
                        s = "等待审核";
                    }else if(row.status==2){
                        s = "审核成功";
                    }else{
                        s = " ";
                    }
                    return s;
                }
            }, {
                field: 'recommendedId',title: '被推荐会员ID',sort: true
            }, {
                field: 'remark',title: '描述',sort: true
            }, {
                field: 'createTime',title: '创建时间',sort: true,templet:function (row) {
                    return dateFormat(row.createTime);
                }
            },  {
                field: 'id',title: '操作',toolbar: '#operateTpl',unresize: true,
            }]
        ],
        data: tabledata,
        event: true,
        page: true
    });


    $('.weadmin-block .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    $('.we-search .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    var active = {
        getCheckData: function() { //获取选中数据
            var checkStatus = table.checkStatus('list'),
                data = checkStatus.data;

            //layer.alert(JSON.stringify(data));
            if(data.length > 0) {
                var ids ="";
                for(var i=0;i<data.length;i++){
                    ids += data[i].id+",";
                }

                layer.confirm('确认要删除吗？删除后不能找回', function(index) {
                    //console.log(ids.substring(0,ids.length-1));
                    //找到所有被选中的，发异步进行删除
                    ajaxpost.ajax("/reward-api/reward/funds/delete",null, {id:ids.substring(0,ids.length-1)},function (res) {
                        if(res.code=="200"){

                            layer.msg('删除成功', {
                                icon: 1
                            });

                            location.replace(location.href);
                            // $(".layui-table-body .layui-form-checked").parents('tr').remove();
                        }else{
                            layer.msg(res.msg,function(){
                            });
                        }
                    })


                });
            } else {
                layer.msg("请先选择需要删除的内容！");
            }

        },


        Reload: function(){
            table.reload('list', {

                where: {

                    userId:$('#userIdSearch').val()

                },
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        }

    };



    /*用户-删除*/
    window.del = function(obj, id) {

        console.log(id);
        layer.confirm('确认要删除吗？删除后不能找回', function(index) {
            //发异步删除数据

            //找到所有被选中的，发异步进行删除
            ajaxpost.ajax("/reward-api/reward/funds/delete",null, {id:id},function (res) {
                if(res.code=="200"){

                    layer.msg('删除成功', {
                        icon: 1
                    });

                    location.replace(location.href);
                    // $(".layui-table-body .layui-form-checked").parents('tr').remove();
                }else{
                    layer.msg(res.msg,function(){
                    });
                }
            })


        });
    }


});
