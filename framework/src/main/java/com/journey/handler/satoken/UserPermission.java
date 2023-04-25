package com.journey.handler.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现 StpInterface 下面的方法，下面的方法会在接口鉴权之前自动调用，判断角色和权限，无需我们手动调用
 *
 * @author hy
 * @version 1.0
 */
@Component
public class UserPermission implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合 即你在调用 StpUtil.login(id) 时写入的标识值。
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
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
    public List<String> getRoleList(Object o, String s) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("admin");
        return roles;
    }

}