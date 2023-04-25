package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.bo.UserInfoBo;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.dto.UserDto;
import com.journey.domain.entity.User;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.UserVo;
import com.journey.mapper.UserMapper;
import com.journey.mapper.UserinfoMapper;
import com.journey.service.UserService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserinfoMapper userinfoMapper;


    @Override
    public Result selectAll() {
        return Result.success(Optional.ofNullable(userMapper.selectList(null))
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, UserDto.class)
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectById(item.getUserinfoId()), UserInfoBo.class)))
                .collect(Collectors.toList()));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), User::getUsername, searchVo.getKeywords())
                .orderByDesc(User::getId);
        Page<User> data = userMapper.selectPage(MBPUtil.generatePage(searchVo, User.class), wrapper);
        List<UserDto> list = Optional.ofNullable(data.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, UserDto.class)
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectById(item.getUserinfoId()), UserInfoBo.class)))
                .collect(Collectors.toList());
        return Result.success(new ResultPage(data.getTotal(), list));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveUser(UserVo userVo) {
        return Result.isStatus(userMapper.insert(BeanCopyUtil.copyBean(userVo, User.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateUser(UserVo userVo) {
        return Result.isStatus(userMapper.updateById(BeanCopyUtil.copyBean(userVo, User.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteUser(Long id) {
        return Result.isStatus(userMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteUser(List<Long> ids) {
        return Result.isStatus(userMapper.deleteBatchIds(ids));
    }

}