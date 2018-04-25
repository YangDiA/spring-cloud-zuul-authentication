/*
	@Author: 请叫我马哥
	@Time: 2017-04
	@Tittle: tab
	@Description: 点击对应按钮添加新窗口
*/
var tabFilter,menu=[],liIndex,curNav,delMenu;
layui.define(["element","jquery","layer"],function(exports){

	var element = layui.element,
		$ = layui.jquery,
		layer = layui.layer;

    Tab = function(){
        this.tabConfig = {
            closed : true,
            openTabNum : 10,
            tabFilter : "bodyTab"
        }
    };

	//显示左侧菜单
	//if($(".navBar").html() == '')
	{
		var _this = this;
		var l = layer.load(1);
        $.ajax({
            type: "GET",
            //url: "/menu/leftmenu",
             url: "/admin/static/json/menu.json",
            dataType: "JSON",
            data: {},
            timeout: 50000,
            async:false,
            error: function(XMLHttpRequest, status, thrownError) {
                layer.close(l);
                layer.msg('网络繁忙，请稍后重试...');
                return false;
            },
            success: function(ret) {
            	layer.close(l);
            	if(ret.status==200){
            		$(".navBar").html(navBar(ret.data)).height($(window).height()-230);
            		element.init();  //初始化页面元素
            		$(window).resize(function(){
            			$(".navBar").height($(window).height()-230);
            		})
            	}else{
            		layer.msg(ret.msg);
            	}
            }
        });
		
	}

    var bodyTab = new Tab();
    exports("bodyTab",function(option){
        return bodyTab.set(option);
    });

})