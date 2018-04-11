package com.liumapp.auth.zuul.gateway.auth.user;

import com.liumapp.auth.zuul.gateway.domain.*;
import com.liumapp.auth.zuul.gateway.mapper.RoleMapper;
import com.liumapp.auth.zuul.gateway.mapper.UserRoleMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liumapp on 2/2/18.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public final class JwtUserFactory {

    private UserRoleMapper userRoleMapper;

    private RoleMapper roleMapper;

    private static JwtUserFactory instance;

    public static synchronized JwtUserFactory getInstance () {
        if (instance == null) {
            instance = new JwtUserFactory();
        }
        return instance;
    }

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                JwtUserFactory.getInstance().decideUsername(user),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                mapToGrantedAuthorities(JwtUserFactory.getInstance().getAuthorities(user)),
                JwtUserFactory.getInstance().convert(user.getIsenabled()),
                user.getType()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }

    private List<Role> getAuthorities (User user) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUseridEqualTo(user.getId());

        List<UserRole> tmp = userRoleMapper.selectByExample(userRoleExample);
        LinkedList<UserRole> userRoles = new LinkedList<UserRole>(tmp);

        List<Role> roles = getRoles();
        List<Role> result = new LinkedList<Role>();

        while (userRoles.size() > 0) {
            UserRole userRole = userRoles.pop();
            for (int i = 0 ; i < roles.size() ; i++) {
                Role role = roles.get(i);
                if (role.getId().equals(userRole.getRoleid()))
                    result.add(role);
            }
        }

        return result;
    }

    private List<Role> getRoles () {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIsNotNull();
        return roleMapper.selectByExample(roleExample);
    }

    private boolean convert (byte isenabled) {
        return isenabled == 1 ;
    }

    private String decideUsername (User user) {
        switch (user.getType()) {
            case 1 :
                return user.getPhone();
            case 2 :
                return user.getEmail();
            default:
                return null;
        }
    }

    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}