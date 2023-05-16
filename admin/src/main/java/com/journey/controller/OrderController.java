package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.OrderVo;
import com.journey.domain.vo.SearchVo;
import com.journey.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 订单模块
 *
 * @author hy
 * @version 1.0
 */
@RequestMapping("order")
@RestController
@Schema(name = "订单模块")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("all")
    @PrintLog("查询所有订单")
    @Operation(summary = "查询所有订单")
    public Result getOrderAll() {
        return orderService.selectAll();
    }

    @GetMapping("list")
    @PrintLog("分页查询")
    @Operation(summary = "分页查询")
    public Result getOrderList(SearchVo searchVo) {
        return orderService.selectList(searchVo);
    }

    @PostMapping("add")
    @PrintLog("添加订单")
    @Operation(summary = "添加订单")
    public Result addOrder(@Valid @RequestBody OrderVo orderVo) {
        return orderService.saveOrder(orderVo);
    }

    @PutMapping("update")
    @PrintLog("更新订单")
    @Operation(summary = "更新订单")
    public Result modifyOrder(@Valid @RequestBody OrderVo orderVo) {
        return orderService.updateOrder(orderVo);
    }

    @DeleteMapping("delete/{id}")
    @PrintLog("删除订单")
    @Operation(summary = "删除订单")
    public Result removeOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }

    @DeleteMapping("batch/delete")
    @PrintLog("批量删除订单")
    @Operation(summary = "批量删除订单")
    public Result batchRemoveOrder(@RequestBody List<Long> ids) {
        return orderService.batchDeleteOrder(ids);
    }


}