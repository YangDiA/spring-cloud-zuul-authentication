package com.linrry.auth.zuul.reward.api.ribbonservice;

import com.linrry.auth.zuul.common.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-09-30 上午 10:41
 */
@Service("sysUserService")
public class SysUserService {

    private static final  String SYSAPI_MICROSERVICE = "http://sys-api";

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addSysUserFallback")
    public Result addSysUser(String phone,String email,String password){

        Map<String,String> postMap = new HashMap<>();
        postMap.put("phone",phone);
        postMap.put("email",email);
        postMap.put("password",password);
        postMap.put("roleName","REWARD");

        Result result =  restTemplate.postForObject(SYSAPI_MICROSERVICE+"/sys/user/addMicroUser",postMap,Result.class);

        return  result;
    }

    public Result addSysUserFallback(String phone,String email,String password){
        return Result.failure("添加系统用户失败");
    }

}
