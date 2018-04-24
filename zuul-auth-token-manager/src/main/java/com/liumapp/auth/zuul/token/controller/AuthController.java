package com.liumapp.auth.zuul.token.controller;

import com.liumapp.auth.zuul.common.Result;
import com.liumapp.auth.zuul.token.request.JwtAuthenticationRequest;
import com.liumapp.auth.zuul.token.response.JwtAuthenticationResponse;
import com.liumapp.auth.zuul.token.service.MultyUserDetailsService;
import com.liumapp.auth.zuul.token.user.JwtUser;
import com.liumapp.auth.zuul.token.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * generate & refresh token
 * for personal User and company User
 * Created by liumapp on 2/8/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping(value = "/unchk")
public class AuthController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${custom.activeInfo:hello-user}")
    private String activeInfo;

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MultyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/auth")
    public String index () {
        return "hello , this is auth zuul gateway .";
    }


    @RequestMapping(value = "/${jwt.route.authentication.path}/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        logger.info("this is company login , and active info is : " + activeInfo);
        authenticationRequest.setUsername(authenticationRequest.getPhone());
        // Perform the security
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }


        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByPhone(authenticationRequest.getPhone());

        Result resut = Result.ok();

        //密码检验
        if(!passwordEncoder.matches(authenticationRequest.getPassword(),userDetails.getPassword())){
            resut.setCode("1001");
            resut.setMsg("密码错误");
            return ResponseEntity.ok(resut);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        System.out.println(token);
        resut.setData(token);
        // Return the token
        return ResponseEntity.ok(resut);
    }

    /**
     * 刷新token
     * @param request
     * @return
     */
    @RequestMapping(value = "/${jwt.route.authentication.refresh}/token", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAndAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String phone = jwtTokenUtil.getPhoneFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(phone);
        Result resut = Result.ok();
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            resut.setData(token);
            return ResponseEntity.ok(resut);
        } else {
            resut.setCode("1001");
            resut.setMsg("刷新失败");
            return ResponseEntity.ok(resut);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/${jwt.route.authentication.path}/checktoken", method = RequestMethod.POST)
    public ResponseEntity<?> checkAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        //String phone = jwtTokenUtil.getPhoneFromToken(token);
       // JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(phone);
        Result resut = Result.ok();
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {//没过期
            resut.setData(true);
            resut.setMsg("未过期");
            return ResponseEntity.ok(resut);
        } else {
            resut.setData(false);
            resut.setMsg("token过期");
            return ResponseEntity.ok(resut);
        }
    }



    @RequestMapping(value = "/${jwt.route.authentication.path}/company", method = RequestMethod.POST)
    public ResponseEntity<?> createCompanyAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        logger.info("this is company login , and active info is : " + activeInfo);
        authenticationRequest.setUsername(authenticationRequest.getEmail());
        // Perform the security
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByEmail(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/${jwt.route.authentication.path}/personal", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonalAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        logger.info("this is personal login , and active info is : " + activeInfo);
        authenticationRequest.setUsername(authenticationRequest.getPhone());
        // Perform the security
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByPhone(authenticationRequest.getPhone());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/${jwt.route.authentication.refresh}/company", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetCompanyAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String email = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByEmail(email);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/${jwt.route.authentication.refresh}/personal", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetPersonalAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String phone = jwtTokenUtil.getPhoneFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(phone);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
