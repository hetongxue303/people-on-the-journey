package com.journey.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.journey.domain.common.Result;
import com.journey.domain.entity.User;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.LoginVo;
import com.journey.handler.exception.customs.SystemException;
import com.journey.mapper.UserMapper;
import com.journey.mapper.UserinfoMapper;
import com.journey.service.LoginService;
import com.journey.service.UserService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.CommonUtil;
import com.journey.utils.RSAUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

/**
 * 登陆业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserinfoMapper userinfoMapper;
    @Resource
    private UserService userService;
    @Resource
    private RSAUtil rsaUtil;

    @Override
    public Result adminLogin(LoginVo loginVo) {
        // 判断用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(Objects.nonNull(loginVo.getUsername()), User::getUsername, loginVo.getUsername()));
        // 校验用户名和密码
        if (Objects.isNull(user) || !rsaUtil.check(loginVo.getPassword(), user.getPassword()))
            throw new SystemException("用户名或密码错误");
        // 执行登录
        StpUtil.login(user.getId());
        // 拿到token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.success(MapUtil.builder()
                .put("name", tokenInfo.getTokenName())
                .put("value", tokenInfo.getTokenValue())
                .put("timeout", tokenInfo.getTokenTimeout())
                .put("user", userService.getUserInfo(user.getId()))
                .build());
    }

    @Override
    public Result adminLogout() {
        StpUtil.logout(StpUtil.getLoginId());
        return Result.success();
    }

    @Override
    public Result userLogin(LoginVo loginVo) {
        // 判断用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(Objects.nonNull(loginVo.getUsername()), User::getUsername, loginVo.getUsername()));
        // 校验用户名和密码
        if (Objects.isNull(user) || !rsaUtil.check(loginVo.getPassword(), user.getPassword()))
            throw new SystemException("用户名或密码错误");
        // 执行登录
        StpUtil.login(user.getId());
        // 拿到token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.success(MapUtil.builder()
                .put("name", tokenInfo.getTokenName())
                .put("value", tokenInfo.getTokenValue())
                .put("timeout", tokenInfo.getTokenTimeout())
                .put("user", userService.getUserInfo(user.getId()))
                .build());
    }

    @Override
    public Result userLogout() {
        StpUtil.logout(StpUtil.getLoginId());
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result userRegister(LoginVo loginVo) {
        // 判断是否存在
        if (Objects.nonNull(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginVo.getUsername()))))
            throw new SystemException("用户名已存在");
        // 生成随机昵称 新增用户信息
        UserInfo userInfo = new UserInfo().setNickname(CommonUtil.randomCode(new Random().nextInt(10) + 5));
        int result = userinfoMapper.insert(userInfo);
        if (result > 0) {
            User user = BeanCopyUtil.copyBean(loginVo, User.class);
            user.setPassword(rsaUtil.encrypt(user.getPassword())).setUserinfoId(userInfo.getId());
            result = userMapper.insert(user);
        }
        return Result.isStatus(result);
    }
}