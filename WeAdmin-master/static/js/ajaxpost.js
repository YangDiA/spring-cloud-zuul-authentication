layui.define(['jquery'], function(exports){
    var $ = layui.jquery;
    var obj = {
        ajax: function (url,  dataType, data, callback) {
            $.ajax({
                url: url,
                headers:{Authorization: localStorage.getItem('Authorization')},
                type: "post",
                dataType: dataType,
                data: data,
                success: callback
            });
        }
    };
    //输出接口
    exports('ajaxpost', obj);
});