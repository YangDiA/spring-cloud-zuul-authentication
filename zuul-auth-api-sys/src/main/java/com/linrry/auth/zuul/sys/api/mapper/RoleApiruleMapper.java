package com.linrry.auth.zuul.sys.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linrry.auth.zuul.sys.api.entity.RoleApirule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色api数据权限关联表 Mapper 接口
 * </p>
 *
 * @author linrry
 * @since 2018-05-31
 */
public interface RoleApiruleMapper extends BaseMapper<RoleApirule> {
    List<Map<String, Object>> selectDataAuthByRoleId(@Param("roleId") String roleId);
}
