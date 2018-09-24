package com.linrry.auth.zuul.reward.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 团队建设月奖表
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 奖励ID
     */
    @TableId(value = "reward_id", type = IdType.AUTO)
    private Integer rewardId;
    /**
     * 会员ID
     */
    private Integer userId;
    /**
     * 奖励金额
     */
    private Integer rewardAmount;
    /**
     * 团队上月总利润
     */
    private Integer allAmount;
    /**
     * 会员级别
     */
    private Integer userLevel;
    /**
     * 费率
     */
    private Integer rewardRate;
    /**
     * 发奖日期
     */
    private Integer senddate;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Integer getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Integer allAmount) {
        this.allAmount = allAmount;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(Integer rewardRate) {
        this.rewardRate = rewardRate;
    }

    public Integer getSenddate() {
        return senddate;
    }

    public void setSenddate(Integer senddate) {
        this.senddate = senddate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Reward{" +
        ", rewardId=" + rewardId +
        ", userId=" + userId +
        ", rewardAmount=" + rewardAmount +
        ", allAmount=" + allAmount +
        ", userLevel=" + userLevel +
        ", rewardRate=" + rewardRate +
        ", senddate=" + senddate +
        ", createTime=" + createTime +
        "}";
    }
}
