package com.linrry.auth.zuul.sys.api.service.impl;

import com.linrry.auth.zuul.sys.api.entity.Menu;
import com.linrry.auth.zuul.sys.api.mapper.MenuMapper;
import com.linrry.auth.zuul.sys.api.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Map<String, Object>> selectMenuByUid(String uid, String pid) {
        return menuMapper.selectMenuByUid(uid,pid);
    }
}
