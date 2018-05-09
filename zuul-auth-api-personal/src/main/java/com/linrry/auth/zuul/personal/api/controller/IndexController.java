package com.linrry.auth.zuul.personal.api.controller;

import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.personal.api.request.ListArticle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liumapp on 3/5/18 2:48 PM.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RestController
@RequestMapping("/personal")
public class IndexController {

    @Value("${custom.activeInfo:dd}")
    private String activeInfo;

    @RequestMapping("/")
    public String index (ModelMap model) {
        return "Hello , this is Personal api demo ";
    }

    @RequestMapping("/hello")
    public ResponseEntity<?> hello () {
        return ResponseEntity.ok("hello , and active info is : " + activeInfo);
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(HttpServletRequest request, ListArticle listArticle) {
        Result result = Result.ok();

        List<Object> list = new ArrayList<>();
        if(listArticle.getPage() ==1){
            for (int i=0;i<10;i++){

                Map<String,Object> map = new HashMap<>();
                map.put("id",i+1);
                map.put("title","标题"+i);
                map.put("date", "2018-02-03");
                map.put("category","新闻资讯");
                map.put( "sort", "1");
                if(i%2==0){
                    map.put("recommend","checked");
                }else {
                    map.put("recommend","");
                }

                map.put("top","checked");
                list.add(map);
            }

        }
        if(listArticle.getPage() > 1){
            for (int i=0;i<5;i++){

                Map<String,Object> map = new HashMap<>();
                map.put("id",i+10);
                map.put("title","标题"+(i+10));
                map.put("date", "2018-02-03");
                map.put("category","新闻资讯");
                map.put( "sort", "1");
                map.put("recommend","");
                map.put("top","checked");
                list.add(map);
            }

        }

        result.setData(list);

        result.setCount(15);
        result.setMsg("dddddddddddddddd");
        return ResponseEntity.ok(result);
    }


}
