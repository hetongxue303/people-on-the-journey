package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.Role;
import com.journey.domain.vo.RoleVo;
import com.journey.domain.vo.SearchVo;
import com.journey.mapper.RoleMapper;
import com.journey.service.RoleService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 角色业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result selectAll() {
        return Result.success(roleMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Role::getName, searchVo.getKeywords())
                .orderByDesc(Role::getId);
        Page<Role> data = roleMapper.selectPage(MBPUtil.generatePage(searchVo, Role.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), BeanCopyUtil.copyBeanList(data.getRecords(), RoleVo.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveRole(RoleVo roleVo) {
        return Result.isStatus(roleMapper.insert(BeanCopyUtil.copyBean(roleVo, Role.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateRole(RoleVo roleVo) {
        return Result.isStatus(roleMapper.updateById(BeanCopyUtil.copyBean(roleVo, Role.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteRole(Long id) {
        return Result.isStatus(roleMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteRole(List<Long> ids) {
        return Result.isStatus(roleMapper.deleteBatchIds(ids));
    }

}