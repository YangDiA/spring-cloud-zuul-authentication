layui.extend({
	admin: '{/}../../static/js/admin'
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




	/*var  tabledata = [{
        "id": "1",
        "title": "WeAdmin的第一个版本在不断地抽空完善学习中",
        "date": "2018-02-03",
        "category": "官方动态",
        "sort": "1",
        "recommend": "checked",
        "top": "checked"
    }, {
        "id": "2",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-03",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    },{
        "id": "3",
        "title": "WeAdmin的第一个版本在不断地抽空完善学习中",
        "date": "2018-02-03",
        "category": "官方动态",
        "sort": "1",
        "recommend": "checked",
        "top": "checked"
    }, {
        "id": "4",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-03",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    }, {
        "id": "5",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-03",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    },{
        "id": "6",
        "title": "WeAdmin的第一个版本在不断地抽空完善学习中",
        "date": "2018-02-03",
        "category": "官方动态",
        "sort": "1",
        "recommend": "checked",
        "top": "checked"
    }, {
        "id": "7",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-03",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    }, {
        "id": "8",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-05",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    },{
        "id": "9",
        "title": "WeAdmin的第一个版本在不断地抽空完善学习中",
        "date": "2018-02-03",
        "category": "官方动态",
        "sort": "1",
        "recommend": "checked",
        "top": "checked"
    }, {
        "id": "10",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-08",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    }, {
        "id": "11",
        "title": "WeAdmin的测试数据一二三四五六七",
        "date": "2018-02-09",
        "category": "新闻资讯",
        "sort": "1",
        "recommend": "",
        "top": "checked"
    }];*/
	table.render({
		elem: '#articleList',
		cellMinWidth: 80,
		url:"http://localhost/personal-api/personal/list",
        method:"post",
        where:{"Authorization":localStorage.getItem('Authorization')},
        headers: {Authorization: localStorage.getItem('Authorization')},
		cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'id',title: 'ID',sort: true
			}, {
				field: 'title',title: '标题',templet: '#usernameTpl'
			}, {
				field: 'date',title: '发布时间',sort: true
			}, {
				field: 'category',title: '分类',sort: true
			}, {
				field: 'sort',title: '排序',sort: true
			}, {
				field: 'recommend',title: '推荐',templet: '#recommendTpl',unresize: true
			}, {
				field: 'top',title: '置顶',templet: '#topTpl',unresize: true
			}, {
				field: 'review',title: '审核',templet: '#reviewTpl',unresize: true
			}, {
				field: 'operate',title: '操作',toolbar: '#operateTpl',unresize: true
			}]
		],
		/*data: tabledata,*/
		event: true,
		page: true
	});
	var active = {
		getCheckData: function() { //获取选中数据
			var checkStatus = table.checkStatus('articleList'),
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
		Recommend: function() {
			var checkStatus = table.checkStatus('articleList'),
				data = checkStatus.data;
			if(data.length > 0) {
				layer.msg("您点击了推荐操作");
				for(var i = 0; i < data.length; i++) {
					console.log("a:" + data[i].recommend);
					data[i].recommend = "checked";
					console.log("aa:" + data[i].recommend);
					form.render();
				}

			} else {
				layer.msg("请先选择");
			}

			//$(".layui-table-body .layui-form-checked").parents('tr').children().children('input[name="zzz"]').attr("checked","checked");
		},
		Top: function() {
			layer.msg("您点击了置顶操作");

		},
		Review: function() {
			layer.msg("您点击了审核操作");

		},

        ReloadArticle: function(){
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
	window.member_del = function(obj, id) {
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

function searcharticle() {

        console.log("dsearcharticle"+$('start').val());


}