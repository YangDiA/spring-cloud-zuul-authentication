package com.linrry.auth.zuul.company.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liumapp on 2/2/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping("manager")
public class ManagerController {

    @RequestMapping("/")
    public ResponseEntity<?> getManagerGreeting () {
        return ResponseEntity.ok("Greeting from manager");
    }

}
