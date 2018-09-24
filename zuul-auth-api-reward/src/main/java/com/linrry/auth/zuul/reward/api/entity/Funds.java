package com.linrry.auth.zuul.reward.api.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 资金流水表
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
public class Funds implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 奖励ID
     */
    @TableId(value = "funds_id", type = IdType.AUTO)
    private Integer fundsId;
    /**
     * 会员ID
     */
    private Integer userId;
    /**
     * 金额
     */
    private Integer amount;
    /**
     * 类型（1充值，2提现，3推荐奖金，4团队奖金）
     */
    private Integer type;
    /**
     * 流水状态（1等待审核，2审核成功）
     */
    private Integer status;
    /**
     * 被推荐会员ID
     */
    private Integer recommendedId;
    /**
     * 描述
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getFundsId() {
        return fundsId;
    }

    public void setFundsId(Integer fundsId) {
        this.fundsId = fundsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Funds{" +
        ", fundsId=" + fundsId +
        ", userId=" + userId +
        ", amount=" + amount +
        ", type=" + type +
        ", status=" + status +
        ", recommendedId=" + recommendedId +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
