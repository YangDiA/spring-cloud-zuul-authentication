/**
 *
 * Created by 与寻比迁 on 2017/3/12.
 * 基于jquery laytpl-v1.1进行响应的数据绑定
 * v0.2.1 2017/3/24
 * 1.去掉了被修改大概率产生错误的bind_data属性
 * 2.新增get_data()方法获取完整数据，用来替代bind_data
 * 3.新增set_data()方法设置新数据 ，此方法只为规范，内部依然调用refresh方法
 *
 * v0.2.2 2017/4/7
 * 1.双向绑定细化到checked和radio 当type为这2项的input标签进行双向绑定后 绑定的是checked的值
 */


/**
 * laybind v0.2.2
 * 基于jquery laytpl-v1.1进行响应的数据绑定
 * @param selector  筛选器，支持jquery对象和jquery筛选器字符串
 * @param options  参数选项，为了便于扩展 基础数据规范为通过options.data传入
 *
 */
 function laybind(selector, options) {
    var self = this;
    self.data = {};                           //对外数据接口对象
    self.bind_els = [];                       //element数组寄存对象
    var bind_data = {};                      //数据寄存对象

    //双向绑定执行启停
    self.bind_twoway = {};
    self.startTwoway = function (list) {
        _forEach_twoway(list, function (d) {
            if (!d.listener) {
                d.el.addEventListener('change', function (e) {
                    if (e.target.twoway) {  //新增禁止双向绑定属性
                        self.data[e.target.laybind_attr] = e.target[_get_el_tag(e.target)];
                    }
                });
                d.listener = true;
            }
            d.el.twoway = true;
        })
    };
    self.stopTwoway = function (list) {
        _forEach_twoway(list, function (d) {
            d.el.twoway = false;
        })
    };

    //提供一个刷新接口
    self.refresh = function (newData, init) {
        if (init !== false) {
            [].forEach.call(self.bind_els, function (e) {
                e[_get_el_tag(e)] = '';
            });
        }
        newData = typeof newData === 'object' ? newData : bind_data;
        for (var attr in newData) {
            self.data[attr] = newData[attr];
        }
    };

    //因为对外开放bind_data一旦被直接修改大概率产生错误，改为提供一个只读的完整寄存数据获取接口
    self.getData = function () {
        return bind_data
    };
    self.setData = function (newData) {
        self.refresh(newData)
    };

    var els = selector instanceof jQuery ? selector : $(selector);
    if (els.size()) {
        self.bind_els = els.find('[laybind]').each(function (i, e) {
            var attr = e.getAttribute('laybind');
            var tpl = e.getAttribute('laybind-tpl');
            if (tpl) {
                tpl = tpl.charAt(0) === '#' ? $(tpl).html() : tpl;
            } else {
                tpl = e[_get_el_tag(e)];
            }
            e.laybind_attr = attr;
            e.laybind_tpl = tpl;
            if (_get_el_tag(e) !== 'innerHTML' && attr && e.getAttribute('laybind-oneway') === null) {
                self.bind_twoway[attr] = {el: e, listener: false};
            }

            if (attr && options.data[attr] === undefined) {
                options.data[attr] = '';   //基础数据缓存添加对应的属性名
            }
        });

        self.startTwoway();

        if (self.bind_els.length) {
            _bind(options.data, self.data);//开始绑定
        }
    }

    //初始赋值
    self.refresh(options.data);

    //执行数据绑定
    function _bind(data, self_data) {
        for (var attr in data) {
            (function (attr, bind_data, self_data) {
                Object.defineProperty(self_data, attr, {
                    set: function (value) {
                        bind_data[attr] = value; //新数据缓存
                        if (self.laytplShow !== false) {
                            _show(attr, value, bind_data);   //渲染
                        }
                    },
                    get: function () {
                        return bind_data[attr];  //取数据从内部数据缓存
                    }
                });
            })(attr, bind_data, self_data)
        }
    }

    //执行数据展示
    function _show(attr, value, data) {
        [].forEach.call(self.bind_els, function (e) {   //所有的laybind元素循环
            if (e.laybind_attr) {        //为降低渲染次数  laybind带内容的 只有更新的标签名相匹配才赋值
                if (e.laybind_attr == attr) {
                    if (!e.laybind_tpl) {
                        e[_get_el_tag(e)] = value;
                    } else {  //去掉了data[attr]判断 希望用户自己在模板判断
                        e[_get_el_tag(e)] = laytpl(e.laybind_tpl).render(data[attr]);
                    }
                }
            } else if (e.laybind_tpl) {  //laybind不带名称 并且有模板内容的 数据有更新就重新渲染
                e[_get_el_tag(e)] = laytpl(e.laybind_tpl).render(data);
            }
        });
    }

    function _forEach_twoway(list, callBack) {
        if (!list) {
            list = [];
            for (var attr in self.bind_twoway) {
                list.push(attr);
            }
        } else if (typeof list === 'string') {
            list = [list]
        }
        if (list instanceof Array) {
            list.forEach(function (attr) {
                if (self.bind_twoway[attr] !== undefined) {
                    callBack(self.bind_twoway[attr]);
                }
            })
        }
    }

    function _get_el_tag(e) {
        return ['INPUT', 'TEXTAREA'].indexOf(e.tagName) > -1 ? (['checkbox', 'radio'].indexOf(e.type) > -1 ? 'checked' : 'value') : 'innerHTML';
        // return e.tagName === 'INPUT' || e.tagName ===  'TEXTAREA' ?  'value' : 'innerHTML'
    }

    //提供一个停止渲染的属性laytplShow 因为重新启动要重新全量渲染一遍  所以要在这里监听
    Object.defineProperty(self, 'laytplShow', {
        set: function (value) {
            self._laytplShow = value;
            if (value === true) { //被设置为true 执行全量更新
                self.refresh();
            }
        },
        get: function () {
            return self._laytplShow;
        }
    });
}