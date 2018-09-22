package com.linrry.auth.zuul.sys.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linrry.auth.zuul.sys.api.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linrry
 * @since 2018-05-09
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Map<String, Object>> selectRoleByUserId(@Param("userId") String userId);
}
