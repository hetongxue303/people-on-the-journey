package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Share;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.ShareVo;

import java.util.List;

/**
 * 分享业务
 *
 * @author hy
 * @version 1.0
 */
public interface ShareService extends IService<Share> {

    Result selectAll();

    Result selectList(SearchVo searchVo);

    Result saveShare(ShareVo shareVo);

    Result updateShare(ShareVo shareVo);

    Result deleteShare(Long id);

    Result batchDeleteShare(List<Long> ids);

    Result selectHomeList(SearchVo searchVo);
}