layui.extend({
	admin: '{/}../../../static/js/admin'
});

layui.use(['table', 'jquery', 'admin','laydate'], function() {
	var table = layui.table,
		$ = layui.jquery,
		admin = layui.admin,
		laydate = layui.laydate;
		laydate.render({
			elem: '#start' //指定元素
		});
		laydate.render({
			elem: '#end' //指定元素
		});

	//console.log("ddd");




	var  tabledata = [ {
        "id" : "230e099407244982895ad972929d7228",
        "userName" : "test123",
        "password" : "7FEF6171469E80D32C0559F88B377245",
        "userState" : 1,
        "createTime" : "2017-07-06 14:37:07",
        "userDesc" : "",
        "userImg" : "/upload/2017-07-06/xktaxi7agg7q3b5ks23rvart84xpfa26.jpg"
    }, {
        "id" : "8ec475bfc69041a4a3984c5510f7982b",
        "userName" : "admin",
        "password" : "7FEF6171469E80D32C0559F88B377245",
        "userState" : 1,
        "createTime" : "2017-07-05 17:13:45",
        "userDesc" : "超级管理员",
        "userImg" : "/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg"
    }, {
        "id" : "be9cb9ae66b64c54a85abee36c274a55",
        "userName" : "test2",
        "password" : "7FEF6171469E80D32C0559F88B377245",
        "userState" : 1,
        "createTime" : "2017-07-05 17:15:07",
        "userDesc" : "测试用户",
        "userImg" : "/upload/2017-07-05/gwh9rtgdr5ykm3wk2etilrazzgwf3k0d.jpg"
    } ];
	table.render({
		elem: '#list',
		cellMinWidth: 80,
		/*url:"http://localhost/personal-api/personal/list",
        method:"post",
        where:{"Authorization":localStorage.getItem('Authorization')},
        headers: {Authorization: localStorage.getItem('Authorization')},*/
		cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'userImg',title: '头像',sort: true
			}, {
				field: 'userName',title: '用户名'
			}, {
				field: 'createTime',title: '创建日期',sort: true
			},{
                field: 'userDesc',title: '描述',sort: true
            }, {
                field: 'userState',title: '状态',templet: '#userStateTpl',unresize: true
            },{
				field: 'id',title: '操作',toolbar: '#operateTpl',unresize: true,
			}]
		],
		data: tabledata,
		event: true,
		page: true
	});
	var active = {
		getCheckData: function() { //获取选中数据
			var checkStatus = table.checkStatus('list'),
				data = checkStatus.data;
			//console.log(data);
			//layer.alert(JSON.stringify(data));
			if(data.length > 0) {
				layer.confirm('确认要删除吗？' + JSON.stringify(data), function(index) {
					layer.msg('删除成功', {
						icon: 1
					});
					//找到所有被选中的，发异步进行删除
					$(".layui-table-body .layui-form-checked").parents('tr').remove();
				});
			} else {
				layer.msg("请先选择需要删除的内容！");
			}

		},


        Reload: function(){
            var start = $('#start');
            table.reload('list', {
                where: {
                	token:localStorage.getItem('token'),
                    start: start.val(),
					end:$('#end').val(),
                    keyword:$('#keyword').val()

                },
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        }

	};



	/*用户-删除*/
	window.del = function(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			//发异步删除数据
			$(obj).parents("tr").remove();
			layer.msg('已删除!', {
				icon: 1,
				time: 1000
			});
		});
	}

});

function delAll(argument) {
	var data = tableCheck.getData();
	layer.confirm('确认要删除吗？' + data, function(index) {
		//捉到所有被选中的，发异步进行删除
		layer.msg('删除成功', {
			icon: 1
		});
		$(".layui-form-checked").not('.header').parents('tr').remove();
	});
}
