package com.linrry.auth.zuul.reward.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 代理商表
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String eamail;
    /**
     * 会员状态(0正常，1冻结，2失效)
     */
    private Integer status;
    /**
     * 推荐会员ID
     */
    private Integer recommendId;
    /**
     * 会员级别
     */
    private Integer level;
    /**
     * 账户金额
     */
    private Integer amount;
    private Integer freezeAmount;
    /**
     * 代理商收货地址
     */
    private String address;
    /**
     * 银行卡号
     */
    private Integer bankId;
    /**
     * 开户行地址
     */
    private String bankAddress;
    /**
     * 创建时间
     */
    private Date createTime;
    private Date updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEamail() {
        return eamail;
    }

    public void setEamail(String eamail) {
        this.eamail = eamail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(Integer freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
        ", userId=" + userId +
        ", name=" + name +
        ", password=" + password +
        ", phone=" + phone +
        ", eamail=" + eamail +
        ", status=" + status +
        ", recommendId=" + recommendId +
        ", level=" + level +
        ", amount=" + amount +
        ", freezeAmount=" + freezeAmount +
        ", address=" + address +
        ", bankId=" + bankId +
        ", bankAddress=" + bankAddress +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
