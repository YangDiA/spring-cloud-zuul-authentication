package com.linrry.auth.zuul.sys.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.linrry.auth.zuul.sys.api.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author linrry
 * @since 2018-05-16
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Map<String, Object>> selectMenuByUid(@Param("uid") String uid, @Param("pid") String pid);
}
