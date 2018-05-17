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

		laydate.render({
			elem: '#start' //指定元素
		});
		laydate.render({
			elem: '#end' //指定元素
		});

	//console.log("ddd");





	table.render({
		elem: '#list',
		cellMinWidth: 80,
		url:"/sys-api/sys/role/page",
        method:"post",
        headers: {Authorization: localStorage.getItem('Authorization')},
        response: {
            statusCode: 200 //成功的状态码，默认：0
        },
        cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'roleName',title: '名称',sort: true
			}, {
				field: 'roleDesc',title: '描述'
			}, {
				field: 'roleState',title: '状态',templet: '#roleStateTpl',unresize: true
			}, /*{
				field: 'icon',title: '图标',templet:function(d){
                    return '<i class="layui-icon">&#xe60c;</i> '
                }
			},*/  {
				field: 'id',title: '操作',toolbar: '#operateTpl',unresize: true,
			}]
		],
		/*data: tabledata,*/
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
                    ajaxpost.ajax("/sys-api/sys/role/delete",null, {id:ids.substring(0,ids.length-1)},function (res) {
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
            var roleName = $('#roleNameSearch');
            table.reload('list', {
                where: {

                    roleName:$('#roleNameSearch').val()

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
            ajaxpost.ajax("/sys-api/sys/role/delete",null, {id:id},function (res) {
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


