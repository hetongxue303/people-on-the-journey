package com.journey.controller;

import com.journey.domain.common.Result;
import com.journey.domain.vo.LoginVo;
import com.journey.service.LoginService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 后台模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@Schema(name = "后台模块")
public class AdminController {

    @Resource
    private LoginService loginService;

    @PostMapping("/admin/login")
    public Result adminLogin(@Valid @RequestBody LoginVo loginVo) {
        return loginService.adminLogin(loginVo);
    }

}