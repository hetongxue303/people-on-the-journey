package com.journey.controller;

import com.journey.annotation.LogPrint;
import com.journey.domain.common.Result;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.TravelAgencyVo;
import com.journey.service.TravelAgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 旅行社模块
 *
 * @author hy
 * @version 1.0
 */
@RequestMapping("travel/agency")
@RestController
@Schema(name = "旅行社模块")
public class TravelAgencyController {

    @Resource
    private TravelAgencyService travelAgencyService;

    @GetMapping("all")
    @LogPrint("查询所有旅行社")
    @Operation(summary = "查询所有旅行社")
    public Result getTravelAgencyAll() {
        return travelAgencyService.selectAll();
    }

    @GetMapping("list")
    @LogPrint("分页查询")
    @Operation(summary = "分页查询")
    public Result getTravelAgencyList(SearchVo searchVo) {
        return travelAgencyService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加旅行社")
    @Operation(summary = "添加旅行社")
    public Result addTravelAgency(@Valid @RequestBody TravelAgencyVo travelAgencyVo) {
        return travelAgencyService.saveTravelAgency(travelAgencyVo);
    }

    @PutMapping("update")
    @LogPrint("更新旅行社")
    @Operation(summary = "更新旅行社")
    public Result modifyTravelAgency(@Valid @RequestBody TravelAgencyVo travelAgencyVo) {
        return travelAgencyService.updateTravelAgency(travelAgencyVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除旅行社")
    @Operation(summary = "删除旅行社")
    public Result removeTravelAgency(@PathVariable("id") Long id) {
        return travelAgencyService.deleteTravelAgency(id);
    }

    @DeleteMapping("batch/delete")
    @LogPrint("批量删除旅行社")
    @Operation(summary = "批量删除旅行社")
    public Result batchRemoveTravelAgency(@RequestBody List<Long> ids) {
        return travelAgencyService.batchDeleteTravelAgency(ids);
    }

}