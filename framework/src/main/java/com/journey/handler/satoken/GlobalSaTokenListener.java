package com.journey.handler.satoken;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import org.springframework.stereotype.Component;

/**
 * sa-token全局监听器
 *
 * @author hy
 * @version 1.0
 */
@Component
public class GlobalSaTokenListener implements SaTokenListener {

    @Override
    public void doLogin(String s, Object o, String s1, SaLoginModel saLoginModel) {
        // 每次登陆时触发
        System.out.println("登录了...");
    }

    @Override
    public void doLogout(String s, Object o, String s1) {
        //每次注销时触发
        System.out.println("注销了...");
    }

    @Override
    public void doKickout(String s, Object o, String s1) {
        // 每次被踢下线时触发
        System.out.println("踢下线...");
    }

    @Override
    public void doReplaced(String s, Object o, String s1) {
        // 每次被顶下线时触发
        System.out.println("顶下线...");
    }

    @Override
    public void doDisable(String s, Object o, String s1, int i, long l) {
        // 每次被封禁时触发
        System.out.println("被封禁...");
    }

    @Override
    public void doUntieDisable(String s, Object o, String s1) {
        // 每次被解封时触发
        System.out.println("被解封...");
    }

    @Override
    public void doOpenSafe(String s, String s1, String s2, long l) {
        // 每次二级认证时触发
        System.out.println("二级认证...");
    }

    @Override
    public void doCloseSafe(String s, String s1, String s2) {
        // 每次退出二级认证时触发
        System.out.println("退出二级认证...");
    }

    @Override
    public void doCreateSession(String s) {
        // 每次创建Session时触发
        System.out.println("创建session...");
    }

    @Override
    public void doLogoutSession(String s) {
        // 每次注销Session时触发
        System.out.println("注销session...");
    }

    @Override
    public void doRenewTimeout(String s, Object o, long l) {
        // 每次Token续期时触发
        System.out.println("token续期...");
    }

}