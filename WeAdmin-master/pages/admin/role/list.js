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
        "id" : "2a9b728a431246b08f853c2529e6ba84",
        "roleName" : "测试角色",
        "roleDesc" : "测试",
        "roleState" : 0,
        "createTime" : "2017-02-28 15:15:41"
    }, {
        "id" : "3bd9f9e5fa8a4e0587a78cf697e4a9ce",
        "roleName" : "只读角色",
        "roleDesc" : "只读角色",
        "roleState" : 1,
        "createTime" : "2017-07-06 14:35:37"
    }, {
        "id" : "737933bffef640329a4f864c4e2746ba",
        "roleName" : "超级管理员",
        "roleDesc" : "超级管理员",
        "roleState" : 1,
        "createTime" : "2016-12-14 10:22:34"
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
