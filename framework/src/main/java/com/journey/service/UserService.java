package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.User;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.UserVo;

import java.util.List;

/**
 * 用户业务
 *
 * @author hy
 * @version 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     */
    Result selectAll();

    /**
     * 分页查询用户
     *
     * @param searchVo 查询条件
     */
    Result selectList(SearchVo searchVo);

    /**
     * 新增用户
     *
     * @param userVo 用户信息
     */
    Result saveUser(UserVo userVo);

    /**
     * 更新用户
     *
     * @param userVo 用户信息
     */
    Result updateUser(UserVo userVo);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    Result deleteUser(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户ID列表
     */
    Result batchDeleteUser(List<Long> ids);

    Result getUserInfo(Long id);
}