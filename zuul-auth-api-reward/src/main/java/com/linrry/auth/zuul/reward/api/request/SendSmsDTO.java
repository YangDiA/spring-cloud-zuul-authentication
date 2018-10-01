package com.linrry.auth.zuul.reward.api.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-10-01 下午 12:33
 */
public class SendSmsDTO {

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @Min(value = 0,message = "短信类型不能小于0")
    @Max(value = 4,message = "短信类型不能大于4")
    private int type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
