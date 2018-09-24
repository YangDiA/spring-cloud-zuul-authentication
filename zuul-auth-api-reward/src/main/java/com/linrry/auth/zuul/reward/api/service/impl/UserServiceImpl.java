package com.linrry.auth.zuul.reward.api.service.impl;

import com.linrry.auth.zuul.reward.api.entity.User;
import com.linrry.auth.zuul.reward.api.mapper.UserMapper;
import com.linrry.auth.zuul.reward.api.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理商表 服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
