package com.linrry.auth.zuul.reward.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-10-01 下午 12:27
 */
public enum  EnumSmsType {

    REGISTER(0,"注册","SMS_146808449"), //尊敬的用户：您的注册验证码为:${code}，请勿泄露。
    LOGIN(1,"登录","SMS_146808485"), //尊敬的用户：您的登录验证码为:${code}，请勿泄露。
    FINDPASSWORD(2,"找回密码","SMS_146808486"), //尊敬的用户：您的找回验证码为:${code}，请勿泄露。
    BANDDING_CARD(3,"银行绑卡","SMS_146803476"),//尊敬的用户：您的绑卡验证码为:${code}，请勿泄露。
    REWARD_ECOMMIISSION(4,"奖励提成","SMS_146803484");//尊敬的用户：您的获得奖励金额为${amount}元，请前往账户查看。


    // 成员变量
    private int type;
    private String name;
    private String templateCode;

    EnumSmsType(int type,String name,String templateCode ) {
        this.type =type;
        this.name = name;
        this.templateCode = templateCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        EnumSmsType[] enums = EnumSmsType.values();
        for (int i = 0; i < enums.length; i++) {
            map.put( enums[i].getType(),enums[i].getTemplateCode());
        }
        return map;
    }
}
