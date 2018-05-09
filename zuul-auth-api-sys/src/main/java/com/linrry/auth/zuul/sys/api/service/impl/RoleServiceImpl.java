package com.linrry.auth.zuul.sys.api.service.impl;

import com.linrry.auth.zuul.sys.api.entity.Role;
import com.linrry.auth.zuul.sys.api.mapper.RoleMapper;
import com.linrry.auth.zuul.sys.api.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
