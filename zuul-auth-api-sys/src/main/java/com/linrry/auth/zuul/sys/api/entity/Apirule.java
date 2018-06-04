package com.linrry.auth.zuul.sys.api.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2018-05-31
 */
public class Apirule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 规则名称
     */
    @TableField("ruleName")
    private String ruleName;
    /**
     * 权限Url
     */
    @TableField("ruleUrl")
    private String ruleUrl;
    /**
     * 相关分类
     */
    @TableField("cateId")
    private Integer cateId;
    /**
     * 创建时间
     */
    @TableField("createDate")
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public void setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Apirule{" +
        ", id=" + id +
        ", ruleName=" + ruleName +
        ", ruleUrl=" + ruleUrl +
        ", cateId=" + cateId +
        ", createDate=" + createDate +
        "}";
    }
}
