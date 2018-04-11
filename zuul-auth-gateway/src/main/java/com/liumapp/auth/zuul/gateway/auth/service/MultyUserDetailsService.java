package com.liumapp.auth.zuul.gateway.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by liumapp on 2/2/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface MultyUserDetailsService extends UserDetailsService {

    UserDetails loadUserByEmail(String var1) throws UsernameNotFoundException;

    UserDetails loadUserByPhone(String var1) throws UsernameNotFoundException;

}
