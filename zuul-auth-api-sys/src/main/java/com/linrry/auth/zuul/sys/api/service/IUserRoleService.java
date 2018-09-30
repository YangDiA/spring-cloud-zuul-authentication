package com.linrry.auth.zuul.sys.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.sys.api.entity.UserRole;
import com.linrry.auth.zuul.sys.api.requset.AddMicroUserDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linrry
 * @since 2018-05-09
 */
public interface IUserRoleService extends IService<UserRole> {

    List<Map<String, Object>> selectRoleByUserId(String userId);

    void addUserRole(String userId, String roleIds);

    Result addMicroUser(AddMicroUserDTO addMicroUserDTO);
}
