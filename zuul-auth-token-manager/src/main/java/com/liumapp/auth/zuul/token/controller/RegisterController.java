package com.liumapp.auth.zuul.token.controller;

import com.liumapp.auth.zuul.token.domain.User;
import com.liumapp.auth.zuul.token.domain.UserRole;
import com.liumapp.auth.zuul.token.mapper.UserMapper;
import com.liumapp.auth.zuul.token.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by liumapp on 2/9/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping(value = "/unchk/auth/register")
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @RequestMapping(value = "/personal")
    public String addPersonalUser (@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreatat(new Date());
        user.setType((byte) 1);
        user.setIsenabled((byte) 1);
        userMapper.insert(user);

        //set role
        UserRole userRole = new UserRole();
        userRole.setUserid(user.getId());
        userRole.setRoleid((long) 1);
        userRoleMapper.insert(userRole);

        return "success";
    }

}
