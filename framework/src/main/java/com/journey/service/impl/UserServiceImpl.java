package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.dto.UserDto;
import com.journey.domain.entity.User;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.UserInfoVo;
import com.journey.domain.vo.UserVo;
import com.journey.handler.exception.customs.SystemException;
import com.journey.mapper.UserMapper;
import com.journey.mapper.UserinfoMapper;
import com.journey.service.UserService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import com.journey.utils.RSAUtil;
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
    @Resource
    private RSAUtil rsaUtil;


    @Override
    public Result selectAll() {
        return Result.success(Optional.ofNullable(userMapper.selectList(null))
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, UserDto.class)
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectById(item.getUserinfoId()), UserInfoVo.class)))
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
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectById(item.getUserinfoId()), UserInfoVo.class)))
                .collect(Collectors.toList());
        return Result.success(new ResultPage(data.getTotal(), list));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveUser(UserVo userVo) {
        // 添加用户信息
        UserInfo userinfo = BeanCopyUtil.copyBean(userVo.getUserinfo(), UserInfo.class);
        int result = userinfoMapper.insert(userinfo);
        // 新增用户
        if (result == 0)
            throw new SystemException("新增用户失败");
        User user = BeanCopyUtil.copyBean(userVo, User.class);
        user.setUserinfoId(userinfo.getId());
        user.setPassword(rsaUtil.encrypt(user.getPassword()));
        return Result.isStatus(userMapper.insert(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateUser(UserVo userVo) {
        // 修改用户信息
        UserInfo userinfo = BeanCopyUtil.copyBean(userVo.getUserinfo(), UserInfo.class);
        int result = userinfoMapper.updateById(userinfo);
        // 更新用户
        if (result == 0)
            throw new SystemException("更新用户失败");
        User user = BeanCopyUtil.copyBean(userVo, User.class);
        user.setPassword(rsaUtil.encrypt(user.getPassword()));
        return Result.isStatus(userMapper.updateById(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteUser(Long id) {
        int result = userinfoMapper.deleteById(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, id))
                .getUserinfoId());
        if (result == 0)
            throw new SystemException("删除用户失败");
        return Result.isStatus(userMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteUser(List<Long> ids) {
        int result = userinfoMapper.deleteBatchIds(userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getId, ids))
                .stream()
                .map(User::getUserinfoId)
                .collect(Collectors.toList()));
        if (result == 0)
            throw new SystemException("删除用户失败");
        return Result.isStatus(userMapper.deleteBatchIds(ids));
    }

}