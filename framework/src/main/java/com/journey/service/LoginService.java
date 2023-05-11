package com.journey.service;

import com.journey.domain.common.Result;
import com.journey.domain.vo.LoginVo;
import com.journey.domain.vo.UpwVo;

import javax.validation.constraints.NotBlank;

/**
 * 登陆业务
 *
 * @author hy
 * @version 1.0
 */
public interface LoginService {

    Result adminLogin(LoginVo loginVo);

    Result adminLogout();

    Result userLogin(LoginVo loginVo);

    Result userLogout();

    Result userRegister(LoginVo loginVo);

    Result updatePassword(UpwVo upwVo);

    Result getUserinfo(@NotBlank(message = "您还未登录") Object loginId);
}