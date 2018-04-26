function navBar(data){
    console.log(data.length);
    var ulHtml ='';
   // var ulHtml = '<ul id="nav">';
    for(var i=0;i<data.length;i++){
        if(data[i].spread){
            ulHtml += '<li>';
        }else{
            ulHtml += '<li>';
        }
        //二级菜单
        if(data[i].children != undefined && data[i].children.length > 0){
            ulHtml += '<a href="javascript:;">';
            if(data[i].icon != undefined && data[i].icon != ''){

                ulHtml += '<i class="iconfont"> '+data[i].icon+'</i>';

            }
            ulHtml += '<cite>'+data[i].title+'</cite>';
            if(data[i].icon != undefined && data[i].icon != ''){
                ulHtml += '<i class="iconfont nav_right">&#xe697;</i>';
            }
            ulHtml += '</a>'
            ulHtml += '<ul class="sub-menu">';
            for(var j=0;j<data[i].children.length;j++){

                // ulHtml += '<li><a _href="'+data[i].children[j].href+'">';
                if(data[i].children[j].children!= undefined && data[i].children[j].children.length > 0){
                    ulHtml += '<li><a href="javascript:;">';
                }else{
                    ulHtml += '<li><a _href="'+data[i].children[j].href+'">';
                }

                if(data[i].children[j].icon != undefined && data[i].children[j].icon != ''){

                    ulHtml += '<i class="iconfont"> '+data[i].children[j].icon+'</i>';
                }
                ulHtml += '<cite>'+data[i].children[j].title+'</cite>';

                if(data[i].children[j].children!= undefined && data[i].children[j].children.length > 0){
                    ulHtml +=' <i class="iconfont nav_right">&#xe697;</i>';
                    ulHtml +='</a>';
                }else{
                    ulHtml +='</a></li>';
                }


                //三级菜单循环
                if(data[i].children[j].children!= undefined && data[i].children[j].children.length > 0){
                    /**ulHtml += '<a href="javascript:;">';
                     if(data[i].children[j].icon != undefined && data[i].children[j].icon != ''){

							ulHtml += '<i class="iconfont"> '+data[i].children[j].icon+'</i>';

						}**/
                    ulHtml += '<ul class="sub-menu">';
                    for(var k=0;k<data[i].children[j].children.length;k++){

                        ulHtml += '<li><a _href="'+data[i].children[j].children[k].href+'">';
                        if(data[i].children[j].children[k] != undefined && data[i].children[j].children[k] != ''){

                            ulHtml += '<i class="iconfont"> '+data[i].children[j].children[k].icon+'</i>';
                        }
                        ulHtml += '<cite>'+data[i].children[j].children[k].title+'</cite></a></li>';
                    }
                    ulHtml += "</ul> </li>"
                }else{
                    continue;
                }
            }
            ulHtml += "</ul>"
        }

        ulHtml += '</li>'
    }
    //ulHtml += '</ul>';

    ulHtml +=' <script type="text/javascript"> ';

	ulHtml +=' </script>';
   // console.log(ulHtml);
    return ulHtml;
}
