package com.linrry.auth.zuul.sys.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.linrry.auth.zuul.sys.api.entity.RoleMenu;
import com.linrry.auth.zuul.sys.api.mapper.RoleMenuMapper;
import com.linrry.auth.zuul.sys.api.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Map<String, Object>> selectAuthByRoleId(String id) {
        return roleMenuMapper.selectAuthByRoleId(id);
    }
}
