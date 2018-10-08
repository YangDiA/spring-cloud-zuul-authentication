package com.linrry.auth.zuul.reward.api.controller;


import com.alibaba.fastjson.JSON;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.reward.api.entity.Smslog;
import com.linrry.auth.zuul.reward.api.enums.EnumSmsType;
import com.linrry.auth.zuul.reward.api.request.SendSmsDTO;
import com.linrry.auth.zuul.reward.api.service.ISmslogService;
import com.linrry.auth.zuul.reward.api.utils.SendSmsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-10-01
 */
@Controller
@RequestMapping("/unchk/reward/smslog")
public class SmslogController extends CrudController<Smslog ,ISmslogService> {


    @Autowired
    private ISmslogService smslogService;

    @Autowired
    private SendSmsUtils sendSmsUtils;

    /**
     * 注册 登录  找回密码 银行绑卡 发送短信验证码
     * @param t
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendByType")
    public Result add(@Valid SendSmsDTO t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }

        String templateCode = EnumSmsType.toMap().get(t.getType());

        if (StringUtils.isBlank(templateCode)){
            return Result.failure("发送验证码类型错误");
        }

        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        Map<String,Object> sendSmsData = new HashMap<>();
        sendSmsData.put("code",verifyCode);
        Result resultSendSms = sendSmsUtils.sendSms(t.getPhone(), templateCode, JSON.toJSONString(sendSmsData));

        Smslog smslog = new Smslog();
        smslog.setContent(JSON.toJSONString(sendSmsData));
        smslog.setPhone(t.getPhone());
        smslog.setTemplateCode(templateCode);
        smslog.setType(t.getType());
        smslog.setCreateTime(new Date());
        smslog.setBusinessId(resultSendSms.getData()+"");
        smslogService.insert(smslog);
        return Result.ok("短信验证码发送成功");
    }
}

