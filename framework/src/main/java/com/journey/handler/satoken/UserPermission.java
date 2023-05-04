package com.journey.handler.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.journey.domain.entity.Role;
import com.journey.domain.entity.UserRole;
import com.journey.mapper.RoleMapper;
import com.journey.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 实现 StpInterface 下面的方法，下面的方法会在接口鉴权之前自动调用，判断角色和权限，无需我们手动调用
 *
 * @author hy
 * @version 1.0
 */
@Component
public class UserPermission implements StpInterface {

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;


    /**
     * 返回一个账号所拥有的权限码集合 即你在调用 StpUtil.login(id) 时写入的标识值。
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("user:insert");
        permissions.add("user:delete");
        permissions.add("user:update");
        permissions.add("user:select");
        return permissions;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        ArrayList<String> roles = new ArrayList<>();
        List<Long> roleIds = Optional.ofNullable(userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, Long.valueOf(String.valueOf(loginId)))))
                .orElse(new ArrayList<>())
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        Optional.ofNullable(roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getId, roleIds)))
                .orElse(new ArrayList<>())
                .forEach(item -> roles.add(item.getName()));
        return roles;
    }

}