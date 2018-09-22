package com.linrry.auth.zuul.sys.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.linrry.auth.zuul.sys.api.entity.UserRole;
import com.linrry.auth.zuul.sys.api.mapper.UserRoleMapper;
import com.linrry.auth.zuul.sys.api.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<Map<String, Object>> selectRoleByUserId(String userId) {
        return baseMapper.selectRoleByUserId(userId);
    }

    @Override
    public void addUserRole(String userId, String roleIds) {
        baseMapper.delete(new EntityWrapper<UserRole>().eq("userId",userId));
        if (StringUtils.isNotBlank(roleIds)) {
            List<String> roleIdList = Splitter.on(",").splitToList(roleIds);
            for (String roleId : roleIdList) {
                UserRole userRole = new UserRole();
                //userRole.setId(BaseUtil.uuid());
                userRole.setUserId(Long.valueOf(userId));
                userRole.setRoleId(Long.valueOf(roleId));
                baseMapper.insert(userRole);
            }
        }
    }
}
