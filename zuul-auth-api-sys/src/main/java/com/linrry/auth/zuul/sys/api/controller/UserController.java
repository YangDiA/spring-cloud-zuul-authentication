package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.User;
import com.linrry.auth.zuul.sys.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/test")
    public Page<User> test() {
        return userService.selectPage(new Page<User>(0, 12));
    }

}

