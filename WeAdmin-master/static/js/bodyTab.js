
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
            url: "/sys-api/sys/menu/leftmenu",
             //url: "/admin/static/json/menu.json",
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
            	if(ret.code=="200"){
                 //   navBar(ret.data);
                    console.log(ret.data);
                    $("#nav").html(navBar(ret.data));
            		/*$("#nav").html(navBar(ret.data)).height($(window).height()-230);
            		element.init();  //初始化页面元素
            		$(window).resize(function(){
            			$("#nav").height($(window).height()-230);
            		})*/
                    $('.left-nav #nav li').click(function(event) {
                        if($(this).children('.sub-menu').length) {
                            if($(this).hasClass('open')) {
                                $(this).removeClass('open');
                                $(this).find('.nav_right').html('&#xe697;');
                                $(this).children('.sub-menu').stop().slideUp();
                                $(this).siblings().children('.sub-menu').slideUp();
                            } else {
                                $(this).addClass('open');
                                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                                $(this).children('.sub-menu').stop().slideDown();
                                $(this).siblings().children('.sub-menu').stop().slideUp();
                                $(this).siblings().find('.nav_right').html('&#xe697;');
                                $(this).siblings().removeClass('open');
                            }
                        } else {
                            var url = $(this).children('a').attr('_href');
                            var title = $(this).find('cite').html();
                            var index = $('.left-nav #nav li').index($(this));

                            for(var i = 0; i < $('.weIframe').length; i++) {
                                if($('.weIframe').eq(i).attr('tab-id') == index + 1) {
                                    tab.tabChange(index + 1);
                                    event.stopPropagation();
                                    return;
                                }
                            };

                            tab.tabAdd(title, url, index + 1);
                            tab.tabChange(index + 1);
                        }
                        event.stopPropagation(); //不触发任何前辈元素上的事件处理函数
                    });

                    /*
                     * @todo tab触发事件：增加、删除、切换
                     */
                    var tab = {
                        tabAdd: function(title, url, id) {
                            //判断当前id的元素是否存在于tab中
                            var li = $("#WeTabTip li[lay-id=" + id + "]").length;
                            //console.log(li);
                            if(li > 0) {
                                //tab已经存在，直接切换到指定Tab项
                                //console.log(">0");
                                element.tabChange('wenav_tab', id); //切换到：用户管理
                            } else {
                                //该id不存在，新增一个Tab项
                                //console.log("<0");
                                element.tabAdd('wenav_tab', {
                                    title: title,
                                    content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="weIframe"></iframe>',
                                    id: id
                                });
                                //当前窗口内容
                                setStorageMenu(title, url, id);

                            }
                            CustomRightClick(id); //绑定右键菜单
                            FrameWH(); //计算框架高度

                        },
                        tabDelete: function(id) {
                            element.tabDelete("wenav_tab", id); //删除
                            removeStorageMenu(id);

                        },
                        tabChange: function(id) {
                            //切换到指定Tab项
                            element.tabChange('wenav_tab', id);
                        },
                        tabDeleteAll: function(ids) { //删除所有
                            $.each(ids, function(i, item) {
                                element.tabDelete("wenav_tab", item);
                            })
                            sessionStorage.removeItem('menu');
                        }
                    };

                    /*
                     * @todo 监听右键事件,绑定右键菜单
                     * 先取消默认的右键事件，再绑定菜单，触发不同的点击事件
                     */
                    function CustomRightClick(id) {
                        //取消右键
                        $('.layui-tab-title li').on('contextmenu', function() {
                            return false;
                        })
                        $('.layui-tab-title,.layui-tab-title li').on('click', function() {
                            $('.rightMenu').hide();
                        });
                        //桌面点击右击
                        $('.layui-tab-title li').on('contextmenu', function(e) {
                            var aid = $(this).attr("lay-id"); //获取右键时li的lay-id属性
                            var popupmenu = $(".rightMenu");
                            popupmenu.find("li").attr("data-id", aid);
                            //console.log("popopmenuId:" + popupmenu.find("li").attr("data-id"));
                            l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
                            t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
                            popupmenu.css({
                                left: l,
                                top: t
                            }).show();
                            //alert("右键菜单")
                            return false;
                        });
                    }
                    $("#rightMenu li").click(function() {
                        var type = $(this).attr("data-type");
                        var layId = $(this).attr("data-id")
                        if(type == "current") {
                            //console.log("close this:" + layId);
                            tab.tabDelete(layId);
                        } else if(type == "all") {
                            //console.log("closeAll");
                            var tabtitle = $(".layui-tab-title li");
                            var ids = new Array();
                            $.each(tabtitle, function(i) {
                                ids[i] = $(this).attr("lay-id");
                            })
                            tab.tabDeleteAll(ids);
                        } else if(type == "fresh") {
                            //console.log("fresh:" + layId);
                            tab.tabChange($(this).attr("data-id"));
                            var othis = $('.layui-tab-title').find('>li[lay-id="' + layId + '"]'),
                                index = othis.parent().children('li').index(othis),
                                parents = othis.parents('.layui-tab').eq(0),
                                item = parents.children('.layui-tab-content').children('.layui-tab-item'),
                                src = item.eq(index).find('iframe').attr("src");
                            item.eq(index).find('iframe').attr("src", src);
                        } else if(type == "other") {
                            var thisId = layId;
                            $('.layui-tab-title').find('li').each(function(i, o) {
                                var layId = $(o).attr('lay-id');
                                if(layId != thisId && layId != 0) {
                                    tab.tabDelete(layId);
                                }
                            });
                        }
                        $('.rightMenu').hide();
                    });

                    /*
                     * @todo 重新计算iframe高度
                     */
                    function FrameWH() {
                        var h = $(window).height() - 164;
                        $("iframe").css("height", h + "px");
                    }
                    $(window).resize(function() {
                        FrameWH();
                    });
                    //本地存储记录所有打开的窗口
                    function setStorageMenu(title, url, id) {
                        var menu = JSON.parse(sessionStorage.getItem('menu'));
                        if(menu) {
                            var deep = false;
                            for(var i = 0; i < menu.length; i++) {
                                if(menu[i].id == id) {
                                    deep = true;
                                    menu[i].title = title;
                                    menu[i].url = url;
                                    menu[i].id = id;
                                }
                            }
                            if(!deep) {
                                menu.push({
                                    title: title,
                                    url: url,
                                    id: id
                                })
                            }
                        } else {
                            var menu = [{
                                title: title,
                                url: url,
                                id: id
                            }]
                        }
                        sessionStorage.setItem('menu', JSON.stringify(menu));
                    }
                    //本地存储记录当前打开窗口
                    function setStorageCurMenu() {
                        var curMenu = sessionStorage.getItem('curMenu');
                        var text = $('.layui-tab-title').find('.layui-this').text();
                        text = text.split('ဆ')[0];
                        var url = $('.layui-tab-content').find('.layui-show').find('.weIframe').attr('src');
                        var id = $('.layui-tab-title').find('.layui-this').attr('lay-id');
                        //console.log(text);
                        curMenu = {
                            title: text,
                            url: url,
                            id: id
                        }
                        sessionStorage.setItem('curMenu', JSON.stringify(curMenu));
                    }
                    //本地存储中移除删除的元素
                    function removeStorageMenu(id) {
                        var menu = JSON.parse(sessionStorage.getItem('menu'));
                        //var curMenu = JSON.parse(localStorage.getItem('curMenu'));
                        if(menu) {
                            var deep = false;
                            for(var i = 0; i < menu.length; i++) {
                                if(menu[i].id == id) {
                                    deep = true;
                                    menu.splice(i, 1);
                                }
                            }
                        } else {
                            return false;
                        }
                        sessionStorage.setItem('menu', JSON.stringify(menu));
                    }
            	}else{
            		layer.msg(ret.msg);
            	}
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", localStorage.getItem('Authorization'));
            }
        });
		
	}

    var bodyTab = new Tab();
    exports("bodyTab",function(option){
        return bodyTab.set(option);
    });

})