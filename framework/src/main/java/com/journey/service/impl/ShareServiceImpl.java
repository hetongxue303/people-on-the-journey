package com.journey.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Share;
import com.journey.domain.vo.ShareVo;
import com.journey.mapper.ShareMapper;
import com.journey.service.ShareService;
import com.journey.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveShare(ShareVo shareVo) {
        return Result.isStatus(shareMapper.insert(BeanCopyUtil.copyBean(shareVo, Share.class)
                .setUserId(Long.valueOf((String) StpUtil.getLoginId()))));
    }
}