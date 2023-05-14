package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.ShareVo;
import com.journey.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门户模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("share")
@Schema(name = "门户模块")
public class ShareController {

    @Resource
    private ShareService shareService;

    @PostMapping("add")
    @PrintLog("新增分享")
    @Operation(summary = "新增分享")
    public Result addShare(@RequestBody ShareVo shareVo) {
        return shareService.saveShare(shareVo);
    }

}