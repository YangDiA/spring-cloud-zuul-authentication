package com.linrry.auth.zuul.reward.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-09-22 下午 21:49
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration

public class SecurityConf {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
