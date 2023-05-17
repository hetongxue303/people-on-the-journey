package com.journey.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.Share;
import com.journey.domain.entity.User;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.ShareVo;
import com.journey.domain.vo.UserInfoVo;
import com.journey.mapper.ShareMapper;
import com.journey.mapper.UserMapper;
import com.journey.mapper.UserinfoMapper;
import com.journey.service.ShareService;
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
 * 分享业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

    @Resource
    private ShareMapper shareMapper;
    @Resource
    private UserinfoMapper userinfoMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Result selectAll() {
        return Result.success(shareMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<UserInfo> userinfoWrapper = new LambdaQueryWrapper<>();
        userinfoWrapper.like(Objects.nonNull(searchVo.getKeywords()), UserInfo::getNickname, searchVo.getKeywords());
        List<UserInfo> userInfos = userinfoMapper.selectList(userinfoWrapper);

        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.in(Objects.nonNull(userInfos), User::getUserinfoId, userInfos.stream()
                .map(UserInfo::getId)
                .collect(Collectors.toList()));
        List<User> users = userMapper.selectList(userWrapper);


        LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Objects.nonNull(users), Share::getUserId, users.stream()
                .map(User::getId)
                .collect(Collectors.toList()));
        wrapper.orderByDesc(Share::getId);
        Page<Share> data = shareMapper.selectPage(MBPUtil.generatePage(searchVo, Share.class), wrapper);

        return Result.success(new ResultPage(data.getTotal(), Optional.ofNullable(data.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, ShareVo.class)
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, item.getUserId()))
                                .getUserinfoId())), UserInfoVo.class)))
                .collect(Collectors.toList())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveShare(ShareVo shareVo) {
        return Result.isStatus(shareMapper.insert(BeanCopyUtil.copyBean(shareVo, Share.class)
                .setUserId(Long.valueOf((String) StpUtil.getLoginId()))));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateShare(ShareVo shareVo) {
        return Result.isStatus(shareMapper.updateById(BeanCopyUtil.copyBean(shareVo, Share.class)
                .setUserId(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserinfoId, shareVo.getUserinfo()
                        .getId())).getId())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteShare(Long id) {
        return Result.isStatus(shareMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteShare(List<Long> ids) {
        return Result.isStatus(shareMapper.deleteBatchIds(ids));
    }

    @Override
    public Result selectHomeList(SearchVo searchVo) {
        LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Share::getId);
        Page<Share> data = shareMapper.selectPage(MBPUtil.generatePage(searchVo, Share.class), wrapper);
        List<ShareVo> collects = Optional.ofNullable(data.getRecords()).orElse(new ArrayList<>()).stream().map(item -> {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, item.getUserId()));
            UserInfo userInfo = userinfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(Objects.nonNull(user), UserInfo::getId, user.getUserinfoId()));
            return BeanCopyUtil.copyBean(item, ShareVo.class)
                    .setUserinfo(BeanCopyUtil.copyBean(userInfo, UserInfoVo.class));
        }).collect(Collectors.toList());
        return Result.success(new ResultPage(data.getTotal(), collects));
    }
}