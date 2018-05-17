package com.linrry.auth.zuul.sys.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.linrry.auth.zuul.common.util.BaseUtil;
import com.linrry.auth.zuul.sys.api.entity.RoleMenu;
import com.linrry.auth.zuul.sys.api.mapper.RoleMenuMapper;
import com.linrry.auth.zuul.sys.api.service.IRoleMenuService;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public List<Map<String, Object>> selectAuthByRoleId(String id) {
        return baseMapper.selectAuthByRoleId(id);
    }

    @Override
    public void addRoleMenu(String roleId, String menuIds) {
        baseMapper.delete(new EntityWrapper<RoleMenu>().eq("roleId",roleId));
        if (StringUtils.isNotBlank(menuIds)) {
            List<String> menuIdList = Splitter.on(",").splitToList(menuIds);
            for (String menuId : menuIdList) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setId(BaseUtil.uuid());
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                baseMapper.insert(roleMenu);
            }
        }
    }
}
