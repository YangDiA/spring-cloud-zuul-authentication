package com.linrry.auth.zuul.sys.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linrry.auth.zuul.sys.api.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Map<String, Object>> selectAuthByRoleId(@Param("roleId") String roleId);
}
