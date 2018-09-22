package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.User;
import com.linrry.auth.zuul.sys.api.service.IUserRoleService;
import com.linrry.auth.zuul.sys.api.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends CrudController<User,IUserService> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/test")
    public Page<User> test() {
        return userService.selectPage(new Page<User>(0, 12));
    }

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String userName) {

        Result result = Result.ok();
        EntityWrapper<User> ew = new EntityWrapper<User>();
        if (StringUtils.isNotBlank(userName)) {
            ew.like("userName", userName);
        }
        Page<User> pageData = userService.selectPage(new Page<User>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }

    @ResponseBody
    @RequestMapping("/selectById")
    public Result selectById(String id){
        if(StringUtils.isEmpty(id)){
            return Result.failure("参数{id}不能为空");
        }
        User t =  userService.selectById(id);
        return Result.ok("获取成功").setData(t);
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid User t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        User user = new User();
        user.setId(t.getId());
        user.setName(t.getName());
        user.setPhone(t.getPhone());
        user.setEmail(t.getEmail());
        userService.updateById(user);
        return Result.ok("更新成功");
    }
    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid User user,BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatAt(new Date());
        user.setType(1);
        userService.insert(user);
        return Result.ok("新增成功");
    }

    /**
     * 角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/userrole")
    public Result userrole(String id){
        Result result = Result.ok();
        List<Map<String, Object>> userRoleList =  userRoleService.selectRoleByUserId(id);
        result.setData(userRoleList);
        return result;
    }

    /**
     * 增加用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUserRole")
    public Result addUserRole(String userId,String roleIds){
        Result result = Result.ok();
        //roleMenuService.addRoleMenu(roleId,menuIds);
        userRoleService.addUserRole(userId,roleIds);
        return result;
    }

}

