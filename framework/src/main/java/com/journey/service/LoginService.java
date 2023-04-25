package com.journey.service;

import com.journey.domain.common.Result;
import com.journey.domain.vo.LoginVo;

/**
 * 登陆业务
 *
 * @author hy
 * @version 1.0
 */
public interface LoginService {

    Result adminLogin(LoginVo loginVo);

    Result adminLogout();
}