package com.liumapp.auth.zuul.company.api.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liumapp on 9/28/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index (ModelMap model) {
        return "hello , this is company api demo";
    }

}
