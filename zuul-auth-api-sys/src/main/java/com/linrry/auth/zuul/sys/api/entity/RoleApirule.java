package com.linrry.auth.zuul.sys.api.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 角色api数据权限关联表
 * </p>
 *
 * @author linrry
 * @since 2018-05-31
 */
public class RoleApirule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 角色主键
     */
    @TableField("roleId")
    private String roleId;
    /**
     * api规则权限主键
     */
    @TableField("ruleId")
    private String ruleId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return "RoleApirule{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", ruleId=" + ruleId +
        "}";
    }
}
