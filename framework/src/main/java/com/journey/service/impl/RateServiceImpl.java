package com.journey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Rate;
import com.journey.domain.vo.RateVo;
import com.journey.domain.vo.SearchVo;
import com.journey.mapper.RateMapper;
import com.journey.service.RateService;
import com.journey.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hy
 * @version 1.0
 */
@Service
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements RateService {

    @Resource
    private RateMapper rateMapper;

    @Override
    public Result selectAll() {
        return null;
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveRate(RateVo rateVo) {
        return Result.isStatus(rateMapper.insert(BeanCopyUtil.copyBean(rateVo, Rate.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateRate(RateVo rateVo) {
        return Result.isStatus(rateMapper.updateById(BeanCopyUtil.copyBean(rateVo, Rate.class)));
    }

    @Override
    public Result deleteRate(Long id) {
        return null;
    }

    @Override
    public Result batchDeleteRate(List<Long> ids) {
        return null;
    }
}