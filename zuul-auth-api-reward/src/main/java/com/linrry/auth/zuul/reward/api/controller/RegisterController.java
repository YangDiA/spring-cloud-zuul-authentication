package com.linrry.auth.zuul.reward.api.controller;

import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.reward.api.entity.User;
import com.linrry.auth.zuul.reward.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-10-01 下午 16:51
 */
@Controller
@RequestMapping("unchk/reward/user")
public class RegisterController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid User t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }

        return userService.addUser(t);
    }
}
