package com.journey.controller;

import com.journey.annotation.LogPrint;
import com.journey.domain.common.Result;
import com.journey.domain.vo.FoodVo;
import com.journey.domain.vo.SearchVo;
import com.journey.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 美食模块
 *
 * @author hy
 * @version 1.0
 */
@RequestMapping("food")
@RestController
@Schema(name = "美食模块")
public class FoodController {

    @Resource
    private FoodService foodService;

    @GetMapping("all")
    @LogPrint("查询所有美食")
    @Operation(summary = "查询所有美食")
    public Result getFoodAll() {
        return foodService.selectAll();
    }

    @GetMapping("list")
    @LogPrint("分页查询")
    @Operation(summary = "分页查询")
    public Result getFoodList(SearchVo searchVo) {
        return foodService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加美食")
    @Operation(summary = "添加美食")
    public Result addFood(@Valid @RequestBody FoodVo foodVo) {
        return foodService.saveFood(foodVo);
    }

    @PutMapping("update")
    @LogPrint("更新美食")
    @Operation(summary = "更新美食")
    public Result modifyFood(@Valid @RequestBody FoodVo foodVo) {
        return foodService.updateFood(foodVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除美食")
    @Operation(summary = "删除美食")
    public Result removeFood(@PathVariable("id") Long id) {
        return foodService.deleteFood(id);
    }

    @DeleteMapping("batch/delete")
    @LogPrint("批量删除美食")
    @Operation(summary = "批量删除美食")
    public Result batchRemoveFood(@RequestBody List<Long> ids) {
        return foodService.batchDeleteFood(ids);
    }


}