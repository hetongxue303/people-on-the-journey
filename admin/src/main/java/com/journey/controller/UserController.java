package com.journey.controller;

import com.journey.annotation.LogPrint;
import com.journey.domain.common.Result;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.UserVo;
import com.journey.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("user")
@Schema(name = "用户模块")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("all")
    @LogPrint("查询所有用户")
    @Operation(summary = "查询所有用户")
    public Result getUserAll() {
        return userService.selectAll();
    }

    @GetMapping("list")
    @LogPrint("分页查询")
    @Operation(summary = "分页查询")
    public Result getUserList(SearchVo searchVo) {
        return userService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加用户")
    @Operation(summary = "添加用户")
    public Result addUser(@Valid @RequestBody UserVo userVo) {
        return userService.saveUser(userVo);
    }

    @PutMapping("update")
    @LogPrint("更新用户")
    @Operation(summary = "更新用户")
    public Result modifyUser(@Valid @RequestBody UserVo userVo) {
        return userService.updateUser(userVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除用户")
    @Operation(summary = "删除用户")
    public Result removeUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("batch/delete")
    @LogPrint("批量删除用户")
    @Operation(summary = "批量删除用户")
    public Result batchRemoveUser(@RequestBody List<Long> ids) {
        return userService.batchDeleteUser(ids);
    }

}