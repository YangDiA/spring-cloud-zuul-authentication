package com.linrry.auth.zuul.token.controller;

import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.token.service.MultyUserDetailsService;
import com.linrry.auth.zuul.token.user.JwtUser;
import com.linrry.auth.zuul.token.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-09-29 下午 19:45
 */
@RestController
@RequestMapping(value = "/unchk")
public class TokenController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MultyUserDetailsService userDetailsService;


    @ResponseBody
    @RequestMapping("/${jwt.route.authentication.path}/getUserByToken")
    public Result getUserByToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String phone = jwtTokenUtil.getPhoneFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(phone);

        return Result.ok("获取成功").setData(user);
    }
}
