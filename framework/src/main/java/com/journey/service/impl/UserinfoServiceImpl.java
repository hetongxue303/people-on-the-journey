package com.journey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.UserInfoVo;
import com.journey.mapper.UserinfoMapper;
import com.journey.service.UserinfoService;
import com.journey.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户信息业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, UserInfo> implements UserinfoService {

    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateUserinfo(UserInfoVo loginVo) {
        return Result.isStatus(userinfoMapper.updateById(BeanCopyUtil.copyBean(loginVo, UserInfo.class)));
    }

}