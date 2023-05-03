package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Role;
import com.journey.domain.vo.RoleVo;
import com.journey.domain.vo.SearchVo;

import java.util.List;

/**
 * 角色业务
 *
 * @author hy
 * @version 1.0
 */
public interface RoleService extends IService<Role> {

    Result selectAll();

    Result selectList(SearchVo searchVo);

    Result saveRole(RoleVo roleVo);

    Result updateRole(RoleVo roleVo);

    Result deleteRole(Long id);

    Result batchDeleteRole(List<Long> ids);

}