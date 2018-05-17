package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.Menu;
import com.linrry.auth.zuul.sys.api.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends CrudController<Menu,IMenuService> {

    @Autowired
    private IMenuService menuService;


    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String menuName) {

        Result result = Result.ok();
        EntityWrapper<Menu> ew = new EntityWrapper<Menu>();
        ew.orderBy("code", true);
        if (StringUtils.isNotBlank(menuName)) {
            ew.like("menuName", menuName);
        }
        Page<Menu> pageData = menuService.selectPage(new Page<Menu>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }

    /**
     * 获取当前用户的菜单
     * @return
     */
    @ResponseBody
    @GetMapping("/leftmenu")
    public Result leftmenu(){
        Result result = Result.ok();
       // String uid = ((SysUser)request.getSession().getAttribute("session_user")).getId();
        String uid= "1";
        List<Map<String, Object>> list = menuService.selectMenuByUid(uid,"0");
        for(Map<String, Object> m : list){
            List<Map<String, Object>> son = menuService.selectMenuByUid(uid,m.get("id").toString());

            //三级菜单
            for(Map<String, Object> s : son){
                List<Map<String, Object>> grson = menuService.selectMenuByUid(uid,s.get("id").toString());
                s.put("children",grson);
            }
            m.put("children",son);

        }
        result.setData(list);
        return result;
    }

}

