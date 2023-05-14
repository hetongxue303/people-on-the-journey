package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.service.PortalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("portal")
@Schema(name = "门户模块")
public class PortalController {

    @Resource
    private PortalService portalService;

    @GetMapping("search/list")
    @PrintLog("获取搜索列表")
    @Operation(summary = "获取搜索列表")
    public Result getSearchList() {
        return portalService.selectSearchList();
    }

}