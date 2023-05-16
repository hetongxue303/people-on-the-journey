package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.ShareVo;
import com.journey.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("all")
    @PrintLog("查询所有分享")
    @Operation(summary = "查询所有分享")
    public Result getShareAll() {
        return shareService.selectAll();
    }

    @GetMapping("list")
    @PrintLog("分页查询")
    @Operation(summary = "分页查询")
    public Result getShareList(SearchVo searchVo) {
        return shareService.selectList(searchVo);
    }

    @PostMapping("add")
    @PrintLog("新增分享")
    @Operation(summary = "新增分享")
    public Result addShare(@RequestBody ShareVo shareVo) {
        return shareService.saveShare(shareVo);
    }

    @PutMapping("update")
    @PrintLog("更新分享")
    @Operation(summary = "更新分享")
    public Result modifyShare(@Valid @RequestBody ShareVo shareVo) {
        return shareService.updateShare(shareVo);
    }

    @DeleteMapping("delete/{id}")
    @PrintLog("删除分享")
    @Operation(summary = "删除分享")
    public Result removeShare(@PathVariable("id") Long id) {
        return shareService.deleteShare(id);
    }

    @DeleteMapping("batch/delete")
    @PrintLog("批量删除分享")
    @Operation(summary = "批量删除分享")
    public Result batchRemoveShare(@RequestBody List<Long> ids) {
        return shareService.batchDeleteShare(ids);
    }

}