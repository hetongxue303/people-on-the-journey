package com.journey.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.LoginVo;
import com.journey.domain.vo.UpwVo;
import com.journey.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户安全模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("user")
@Schema(name = "用户安全模块")
public class UserAuthController {

    @Resource
    private LoginService loginService;

    @PostMapping("login")
    @PrintLog("用户登陆")
    @Operation(summary = "用户登陆")
    public Result login(@Valid @RequestBody LoginVo loginVo) {
        return loginService.userLogin(loginVo);
    }

    @PostMapping("register")
    @PrintLog("用户注册")
    @Operation(summary = "用户注册")
    public Result register(@Valid @RequestBody LoginVo loginVo) {
        return loginService.userRegister(loginVo);
    }

    @PostMapping("logout")
    @PrintLog("用户注销")
    @Operation(summary = "用户注销")
    public Result logout() {
        return loginService.userLogout();
    }

    @PutMapping("update/password")
    @PrintLog("用户密码修改")
    @Operation(summary = "用户密码修改")
    public Result updatePassword(@Valid @RequestBody UpwVo upwVo) {
        return loginService.updatePassword(upwVo);
    }

    @GetMapping("userinfo")
    @PrintLog("获取个人信息")
    @Operation(summary = "获取个人信息")
    public Result getUserinfo() {
        return loginService.getUserinfo(StpUtil.getLoginId());
    }

}