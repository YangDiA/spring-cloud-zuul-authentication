package com.linrry.auth.zuul.company.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liumapp on 2/2/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping("boss")
public class BossController {

    @Value("${custom.activeInfo}")
    private String activeInfo;

    @RequestMapping("/hello")
    public ResponseEntity<?> hello () {
        return ResponseEntity.ok("hello , and active info is : " + activeInfo);
    }

    @RequestMapping("/")
    public ResponseEntity<?> getBossGreeting () {
        return ResponseEntity.ok("Greeting from boss");
    }

}
