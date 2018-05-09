package com.linrry.auth.zuul.sys.api.service.impl;

import com.linrry.auth.zuul.sys.api.entity.User;
import com.linrry.auth.zuul.sys.api.mapper.UserMapper;
import com.linrry.auth.zuul.sys.api.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
