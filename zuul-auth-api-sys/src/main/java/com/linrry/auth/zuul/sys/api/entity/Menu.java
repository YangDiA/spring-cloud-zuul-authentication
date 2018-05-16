package com.linrry.auth.zuul.sys.api.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 菜单名称
     */
    @TableField("menuName")
    private String menuName;
    /**
     * 父级菜单ID
     */
    private String pid;
    /**
     * 菜单连接地址
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 深度
     */
    private Integer deep;
    /**
     * 编码
     */
    private String code;
    /**
     * 数据权限
     */
    @TableField("dataResource")
    private String dataResource;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataResource() {
        return dataResource;
    }

    public void setDataResource(String dataResource) {
        this.dataResource = dataResource;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", id=" + id +
        ", menuName=" + menuName +
        ", pid=" + pid +
        ", url=" + url +
        ", icon=" + icon +
        ", sort=" + sort +
        ", deep=" + deep +
        ", code=" + code +
        ", dataResource=" + dataResource +
        "}";
    }
}
