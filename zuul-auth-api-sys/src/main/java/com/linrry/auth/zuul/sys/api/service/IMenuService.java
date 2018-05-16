package com.linrry.auth.zuul.sys.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.linrry.auth.zuul.sys.api.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 查询用户菜单
     * @param uid 用户ID
     * @param pid 上级菜单ID
     * @return
     */
    List<Map<String, Object>> selectMenuByUid(String uid, String pid);

}
