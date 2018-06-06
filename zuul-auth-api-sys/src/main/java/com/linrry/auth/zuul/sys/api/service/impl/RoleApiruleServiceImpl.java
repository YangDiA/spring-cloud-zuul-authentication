package com.linrry.auth.zuul.sys.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.linrry.auth.zuul.common.util.BaseUtil;
import com.linrry.auth.zuul.sys.api.entity.RoleApirule;
import com.linrry.auth.zuul.sys.api.mapper.RoleApiruleMapper;
import com.linrry.auth.zuul.sys.api.service.IRoleApiruleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色api数据权限关联表 服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-31
 */
@Service
public class RoleApiruleServiceImpl extends ServiceImpl<RoleApiruleMapper, RoleApirule> implements IRoleApiruleService {

    @Override
    public List<Map<String, Object>> selectDataAuthByRoleId(String id) {
        return baseMapper.selectDataAuthByRoleId(id);
    }

    @Override
    public void addRoleApirule(String roleId, String apiruleIds) {
        baseMapper.delete(new EntityWrapper<RoleApirule>().eq("roleId",roleId));
        if (StringUtils.isNotBlank(apiruleIds)) {
            List<String> menuIdList = Splitter.on(",").splitToList(apiruleIds);
            for (String ruleId : menuIdList) {
                RoleApirule roleRule = new RoleApirule();
                roleRule.setId(BaseUtil.uuid());
                roleRule.setRuleId(ruleId);
                roleRule.setRoleId(roleId);
                baseMapper.insert(roleRule);
            }
        }
    }
}
