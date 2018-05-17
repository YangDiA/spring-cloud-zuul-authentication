package com.linrry.auth.zuul.sys.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.linrry.auth.zuul.sys.api.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 获取当前角色权限
     * @param id
     * @return
     */
    List<Map<String, Object>> selectAuthByRoleId(String id);

    void addRoleMenu(String roleId, String menuIds);
}
