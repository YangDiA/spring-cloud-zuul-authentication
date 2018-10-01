package com.linrry.auth.zuul.reward.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author linrry
 * @since 2018-10-01
 */
public class Smslog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 短信类型(0 登录，1注册，2 找回密码，3银行绑定 ，4奖励提成)
     */
    private Integer type;
    /**
     * 短信模板编号
     */
    private String templateCode;
    /**
     * 发送内网
     */
    private String content;
    /**
     * 手机号
     */
    private String phone;
    private Date createTime;
    /**
     * 短信发送业务id
     */
    private String businessId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "Smslog{" +
        ", id=" + id +
        ", type=" + type +
        ", templateCode=" + templateCode +
        ", content=" + content +
        ", phone=" + phone +
        ", createTime=" + createTime +
        ", businessId=" + businessId +
        "}";
    }
}
