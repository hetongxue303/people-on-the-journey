package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.UserInfoVo;

/**
 * 用户信息业务
 *
 * @author hy
 * @version 1.0
 */
public interface UserinfoService extends IService<UserInfo> {

    Result updateUserinfo(UserInfoVo loginVo);

}