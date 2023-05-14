package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Share;
import com.journey.domain.vo.ShareVo;

/**
 * 分享业务
 *
 * @author hy
 * @version 1.0
 */
public interface ShareService extends IService<Share> {

    Result saveShare(ShareVo shareVo);

}