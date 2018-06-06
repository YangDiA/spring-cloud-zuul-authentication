package com.linrry.auth.zuul.sys.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.linrry.auth.zuul.sys.api.entity.RoleApirule;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色api数据权限关联表 服务类
 * </p>
 *
 * @author linrry
 * @since 2018-05-31
 */
public interface IRoleApiruleService extends IService<RoleApirule> {

    /**
     * 获取当前角色权限
     * @param id
     * @return
     */
    List<Map<String, Object>> selectDataAuthByRoleId(String id);

     void addRoleApirule(String roleId, String apiruleIds);

}
