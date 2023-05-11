package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.UserInfoVo;
import com.journey.service.UserinfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户信息模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("userinfo")
@Schema(name = "用户信息模块")
public class UserinfoController {

    @Resource
    private UserinfoService userinfoService;

    @PutMapping("update")
    @PrintLog("更新用户信息")
    @Operation(summary = "更新用户信息")
    public Result updateUserinfo(@Valid @RequestBody UserInfoVo loginVo) {
        return userinfoService.updateUserinfo(loginVo);
    }

}