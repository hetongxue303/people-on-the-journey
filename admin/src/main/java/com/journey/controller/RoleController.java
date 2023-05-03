package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.vo.RoleVo;
import com.journey.domain.vo.SearchVo;
import com.journey.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色模块
 *
 * @author hy
 * @version 1.0
 */
@RequestMapping("role")
@RestController
@Schema(name = "角色模块")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("all")
    @PrintLog("查询所有角色")
    @Operation(summary = "查询所有角色")
    public Result getRoleAll() {
        return roleService.selectAll();
    }

    @GetMapping("list")
    @PrintLog("分页查询")
    @Operation(summary = "分页查询")
    public Result getRoleList(SearchVo searchVo) {
        return roleService.selectList(searchVo);
    }

    @PostMapping("add")
    @PrintLog("添加角色")
    @Operation(summary = "添加角色")
    public Result addRole(@Valid @RequestBody RoleVo RoleVo) {
        return roleService.saveRole(RoleVo);
    }

    @PutMapping("update")
    @PrintLog("更新角色")
    @Operation(summary = "更新角色")
    public Result modifyRole(@Valid @RequestBody RoleVo RoleVo) {
        return roleService.updateRole(RoleVo);
    }

    @DeleteMapping("delete/{id}")
    @PrintLog("删除角色")
    @Operation(summary = "删除角色")
    public Result removeRole(@PathVariable("id") Long id) {
        return roleService.deleteRole(id);
    }

    @DeleteMapping("batch/delete")
    @PrintLog("批量删除角色")
    @Operation(summary = "批量删除角色")
    public Result batchRemoveRole(@RequestBody List<Long> ids) {
        return roleService.batchDeleteRole(ids);
    }


}