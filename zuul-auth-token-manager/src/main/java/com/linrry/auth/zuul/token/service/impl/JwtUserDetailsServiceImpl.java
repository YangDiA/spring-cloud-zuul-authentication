package com.linrry.auth.zuul.token.service.impl;

import com.linrry.auth.zuul.token.domain.User;
import com.linrry.auth.zuul.token.domain.UserExample;
import com.linrry.auth.zuul.token.mapper.UserMapper;
import com.linrry.auth.zuul.token.service.MultyUserDetailsService;
import com.linrry.auth.zuul.token.user.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by liumapp on 2/2/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 * the username here can be email and also can be phone number
 * that dependent on user type
 */
@Service
public class JwtUserDetailsServiceImpl implements MultyUserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param username
     * @return UserDetail
     * @throws UsernameNotFoundException not found user
     */
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            userDetails = this.loadUserByEmail(username);
        } catch (Exception e1 ) {
            try {
                userDetails = this.loadUserByPhone(username);
            } catch (Exception e2) {
                throw e2;
            }
        }

        return userDetails;
    }

    @Override
    public UserDetails loadUserByEmail (String email) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andEmailEqualTo(email);

        List<User> tmp =  userMapper.selectByExample(userExample);
        LinkedList<User> users = new LinkedList<User>(tmp);

        User user = null;
        try {
            user = users.pop();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("not found user by email : " + email);
        }

        return JwtUserFactory.create(user);

    }

    @Override
    public UserDetails loadUserByPhone (String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andPhoneEqualTo(phone);

        List<User> tmp = userMapper.selectByExample(userExample);
        LinkedList<User> users = new LinkedList<User>(tmp);

        User user = null;
        try {
            user = users.pop();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("not found user by phone " + phone);
        }

        return JwtUserFactory.create(user);
    }

}
