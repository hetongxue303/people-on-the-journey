package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.ScenicSpotsVo;
import com.journey.domain.vo.SearchVo;
import com.journey.service.ScenicSpotsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 景点模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("scenic/spots")
@Schema(name = "景点模块")
public class ScenicSpotsController {

    @Resource
    private ScenicSpotsService scenicSpotsService;

    @GetMapping("all")
    @PrintLog("查询所有景点")
    @Operation(summary = "查询所有景点")
    public Result getScenicSpotsAll() {
        return scenicSpotsService.selectAll();
    }

    @GetMapping("list")
    @PrintLog("分页查询")
    @Operation(summary = "分页查询")
    public Result getScenicSpotsList(SearchVo searchVo) {
        System.out.println("searchVo = " + searchVo);
        return scenicSpotsService.selectList(searchVo);
    }

    @PostMapping("add")
    @PrintLog("添加景点")
    @Operation(summary = "添加景点")
    public Result addScenicSpots(@Valid @RequestBody ScenicSpotsVo scenicSpotsVo) {
        return scenicSpotsService.saveScenicSpots(scenicSpotsVo);
    }

    @PutMapping("update")
    @PrintLog("更新景点")
    @Operation(summary = "更新景点")
    public Result modifyScenicSpots(@Valid @RequestBody ScenicSpotsVo scenicSpotsVo) {
        return scenicSpotsService.updateScenicSpots(scenicSpotsVo);
    }

    @DeleteMapping("delete/{id}")
    @PrintLog("删除景点")
    @Operation(summary = "删除景点")
    public Result removeScenicSpots(@PathVariable("id") Long id) {
        return scenicSpotsService.deleteScenicSpots(id);
    }

    @DeleteMapping("batch/delete")
    @PrintLog("批量删除景点")
    @Operation(summary = "批量删除景点")
    public Result batchRemoveScenicSpots(@RequestBody List<Long> ids) {
        return scenicSpotsService.batchDeleteScenicSpots(ids);
    }


}