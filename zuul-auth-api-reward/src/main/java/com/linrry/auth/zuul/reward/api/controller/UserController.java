package com.linrry.auth.zuul.reward.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.reward.api.entity.User;
import com.linrry.auth.zuul.reward.api.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 代理商表 前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
@Controller
@RequestMapping("/reward/user")
public class UserController extends CrudController<User,IUserService> {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String userName) {

        Result result = Result.ok();
        EntityWrapper<User> ew = new EntityWrapper<User>();
        if (StringUtils.isNotBlank(userName)) {
            ew.like("name", userName);
        }
        Page<User> pageData = userService.selectPage(new Page<User>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid User t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }

        return userService.addUser(t);
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid User t,BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        t.setUpdateTime(new Date());
        t.setCreateTime(null);
        userService.updateById(t);
        return Result.ok("更新成功");
    }
}

