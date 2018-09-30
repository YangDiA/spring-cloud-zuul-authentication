package com.linrry.auth.zuul.sys.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.sys.api.entity.Role;
import com.linrry.auth.zuul.sys.api.entity.User;
import com.linrry.auth.zuul.sys.api.entity.UserRole;
import com.linrry.auth.zuul.sys.api.mapper.UserMapper;
import com.linrry.auth.zuul.sys.api.mapper.UserRoleMapper;
import com.linrry.auth.zuul.sys.api.requset.AddMicroUserDTO;
import com.linrry.auth.zuul.sys.api.service.IRoleService;
import com.linrry.auth.zuul.sys.api.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    @Transactional
    public Result addMicroUser(AddMicroUserDTO addMicroUserDTO) {
        EntityWrapper<Role> ewRole = new EntityWrapper<Role>();
        if (StringUtils.isNotBlank(addMicroUserDTO.getRoleName())) {
            ewRole.eq("name", "ROLE_"+addMicroUserDTO.getRoleName());
        }
        Role role = roleService.selectOne(ewRole);
        if (role ==null){
            return Result.failure("请先添加子系统角色");
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(addMicroUserDTO.getPassword()));
        user.setCreatAt(new Date());
        user.setType(1);
        user.setEmail(addMicroUserDTO.getEmail());
        user.setPhone(addMicroUserDTO.getPhone());
        user.setName(addMicroUserDTO.getPhone());
        userMapper.insert(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(Long.valueOf(user.getId()));
        userRole.setRoleId(Long.valueOf(role.getId()));
        baseMapper.insert(userRole);

        return Result.ok().setMsg("新增系统用户成功").setData(user);
    }
}
