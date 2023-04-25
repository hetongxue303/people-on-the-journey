package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.TravelAgency;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.TravelAgencyVo;
import com.journey.mapper.TravelAgencyMapper;
import com.journey.service.TravelAgencyService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 旅行社业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class TravelAgencyServiceImpl extends ServiceImpl<TravelAgencyMapper, TravelAgency> implements TravelAgencyService {

    @Resource
    private TravelAgencyMapper travelAgencyMapper;

    @Override
    public Result selectAll() {
        return Result.success(travelAgencyMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<TravelAgency> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), TravelAgency::getName, searchVo.getKeywords())
                .orderByDesc(TravelAgency::getId);
        Page<TravelAgency> data = travelAgencyMapper.selectPage(MBPUtil.generatePage(searchVo, TravelAgency.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveTravelAgency(TravelAgencyVo travelAgencyVo) {
        return Result.isStatus(travelAgencyMapper.insert(BeanCopyUtil.copyBean(travelAgencyVo, TravelAgency.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateTravelAgency(TravelAgencyVo travelAgencyVo) {
        return Result.isStatus(travelAgencyMapper.updateById(BeanCopyUtil.copyBean(travelAgencyVo, TravelAgency.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteTravelAgency(Long id) {
        return Result.isStatus(travelAgencyMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteTravelAgency(List<Long> ids) {
        return Result.isStatus(travelAgencyMapper.deleteBatchIds(ids));
    }

}