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
      "id" : "1",
      "menuName" : "系统管理",
      "pid" : "0",
      "url" : "",
      "icon" : "&#xe6b8;",
      "sort" : 0,
      "deep" : 1,
      "code" : "01",
      "resource" : ""
    }, {
      "id" : "2",
      "menuName" : "用户管理",
      "pid" : "1",
      "url" : "/user/list",
      "icon" : "&#xe697;",
      "sort" : 1,
      "deep" : 2,
      "code" : "0101",
      "resource" : "user"
    }, {
      "id" : "d2bc30feb5474a1bb7e02d48d39a3f8a",
      "menuName" : "查看用户列表",
      "pid" : "2",
      "url" : "",
      "icon" : "",
      "sort" : 0,
      "deep" : 3,
      "code" : "010100",
      "resource" : "listUser"
    }, {
      "id" : "a73802e513cc4465883530ec8074abbb",
      "menuName" : "新增用户",
      "pid" : "2",
      "url" : "",
      "icon" : "",
      "sort" : 1,
      "deep" : 3,
      "code" : "010101",
      "resource" : "addUser"
    }, {
      "id" : "4253190001c1480fb0d631d64d150535",
      "menuName" : "编辑用户",
      "pid" : "2",
      "url" : "",
      "icon" : "",
      "sort" : 2,
      "deep" : 3,
      "code" : "010102",
      "resource" : "editUser"
    }, {
      "id" : "649b484b58414d91aefa5a26143e6557",
      "menuName" : "删除用户",
      "pid" : "2",
      "url" : "",
      "icon" : "",
      "sort" : 3,
      "deep" : 3,
      "code" : "010103",
      "resource" : "deleteUser"
    }, {
      "id" : "9c51e94cef99435780fb72bdb923a2ab",
      "menuName" : "更新用户状态",
      "pid" : "2",
      "url" : "",
      "icon" : "",
      "sort" : 4,
      "deep" : 3,
      "code" : "010104",
      "resource" : "updateStateUser"
    }, {
      "id" : "3",
      "menuName" : "角色管理",
      "pid" : "1",
      "url" : "/role/list",
      "icon" : "&#xe6a7;",
      "sort" : 2,
      "deep" : 2,
      "code" : "0102",
      "resource" : "role"
    }, {
      "id" : "1db9105008404a3485b6fac30dba3c0e",
      "menuName" : "查看角色列表",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 0,
      "deep" : 3,
      "code" : "010200",
      "resource" : "listRole"
    }, {
      "id" : "60dda993d87647f5989c15f14f866df9",
      "menuName" : "新增角色",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 1,
      "deep" : 3,
      "code" : "010201",
      "resource" : "addRole"
    }, {
      "id" : "686630c7cb624cc786dcdc9958971a6b",
      "menuName" : "编辑角色",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 2,
      "deep" : 3,
      "code" : "010202",
      "resource" : "editRole"
    }, {
      "id" : "b4e7232189b14cf3ba160cf7b0d3bf37",
      "menuName" : "删除角色",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 3,
      "deep" : 3,
      "code" : "010203",
      "resource" : "deleteRole"
    }, {
      "id" : "a5ebf29168234406939856bc6890c86b",
      "menuName" : "角色授权",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 4,
      "deep" : 3,
      "code" : "010204",
      "resource" : "authRole"
    }, {
      "id" : "f899f3d3baec4571b1c786717f9906fd",
      "menuName" : "批量删除角色",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 5,
      "deep" : 3,
      "code" : "010205",
      "resource" : "deleteBatchRole"
    }, {
      "id" : "3987d383a7a74b45902e14e027d9b56e",
      "menuName" : "更新角色状态",
      "pid" : "3",
      "url" : "",
      "icon" : "",
      "sort" : 6,
      "deep" : 3,
      "code" : "010206",
      "resource" : "updateStateRole"
    } ];
	table.render({
		elem: '#menuList',
		cellMinWidth: 80,
		/*url:"http://localhost/personal-api/personal/list",
        method:"post",
        where:{"Authorization":localStorage.getItem('Authorization')},
        headers: {Authorization: localStorage.getItem('Authorization')},*/
		cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'code',title: '编码',sort: true
			}, {
				field: 'menuName',title: '菜单名称',templet: '#usernameTpl'
			}, {
				field: 'url',title: 'URL',sort: true
			}, {
				field: 'resource',title: '动作',sort: true
			}, {
				field: 'sort',title: '排序',sort: true
			}, {
				field: 'icon',title: '图标',templet:function(d){
                   // return '<i class="layui-icon">#'+d.icon+'</i>'
                    return '<i class="layui-icon">&#xe60c;</i> '
                }
			}, {
				field: 'deep',title: '深度',sort: true
			},  {
				field: 'id',title: '操作',toolbar: '#operateTpl',unresize: true,
			}]
		],
		data: tabledata,
		event: true,
		page: true
	});
	var active = {
		getCheckData: function() { //获取选中数据
			var checkStatus = table.checkStatus('menuList'),
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
				layer.msg("请先选择需要删除的文章！");
			}

		},


        Reload: function(){
            var start = $('#start');
            table.reload('articleList', {
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

	$('.demoTable .layui-btn').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
    $('.we-search .layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

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
