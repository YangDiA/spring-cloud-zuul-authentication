package com.linrry.auth.zuul.reward.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.reward.api.entity.User;
import com.linrry.auth.zuul.reward.api.mapper.UserMapper;
import com.linrry.auth.zuul.reward.api.ribbonservice.SysUserService;
import com.linrry.auth.zuul.reward.api.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result addUser(User user) {

        //添加系统用户
        Result result = sysUserService.addSysUser(user.getPhone(),user.getEamail(),user.getPassword());
        if (!result.checkOk()){
            return result;
        }
        //设置默认值
        user.setCreateTime(new Date());
        user.setAmount(0);
        user.setLevel(1);
        user.setFreezeAmount(0);
        user.setUpdateTime(new Date());
        user.setStatus(0);
        if (StringUtils.isBlank(user.getName())){
            user.setName(user.getPhone());
        }

        Map<String ,Object> sysUser =  (Map<String, Object>) result.getData();

        user.setUserId(Integer.valueOf(sysUser.get("id")+""));
        user.setPassword(sysUser.get("password")+"");
        baseMapper.insert(user);

        return Result.ok("新增成功");
    }
}
