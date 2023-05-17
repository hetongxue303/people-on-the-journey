package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.RateVo;
import com.journey.domain.vo.SearchVo;
import com.journey.service.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 评分模块
 *
 * @author hy
 * @version 1.0
 */
@RequestMapping("rate")
@RestController
@Schema(name = "评分模块")
public class RateController {

    @Resource
    private RateService rateService;


    @GetMapping("all")
    @PrintLog("查询所有评分")
    @Operation(summary = "查询所有评分")
    public Result getRateAll() {
        return rateService.selectAll();
    }

    @GetMapping("list")
    @PrintLog("分页查询")
    @Operation(summary = "分页查询")
    public Result getRateList(SearchVo searchVo) {
        return rateService.selectList(searchVo);
    }

    @PostMapping("add")
    @PrintLog("添加评分")
    @Operation(summary = "添加评分")
    public Result addRate(@Valid @RequestBody RateVo rateVo) {
        return rateService.saveRate(rateVo);
    }

    @PutMapping("update")
    @PrintLog("更新评分")
    @Operation(summary = "更新评分")
    public Result modifyRate(@Valid @RequestBody RateVo rateVo) {
        return rateService.updateRate(rateVo);
    }

    @DeleteMapping("delete/{id}")
    @PrintLog("删除评分")
    @Operation(summary = "删除评分")
    public Result removeRate(@PathVariable("id") Long id) {
        return rateService.deleteRate(id);
    }

    @DeleteMapping("batch/delete")
    @PrintLog("批量删除评分")
    @Operation(summary = "批量删除评分")
    public Result batchRemoveRate(@RequestBody List<Long> ids) {
        return rateService.batchDeleteRate(ids);
    }


}