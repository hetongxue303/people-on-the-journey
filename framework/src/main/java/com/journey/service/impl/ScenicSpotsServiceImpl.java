package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.ScenicSpots;
import com.journey.domain.vo.ScenicSpotsVo;
import com.journey.domain.vo.SearchVo;
import com.journey.mapper.ScenicSpotsMapper;
import com.journey.service.ScenicSpotsService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 景点业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class ScenicSpotsServiceImpl extends ServiceImpl<ScenicSpotsMapper, ScenicSpots> implements ScenicSpotsService {

    @Resource
    private ScenicSpotsMapper scenicSpotsMapper;

    @Override
    public Result selectAll() {
        return Result.success(scenicSpotsMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<ScenicSpots> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), ScenicSpots::getName, searchVo.getKeywords())
                .orderByDesc(ScenicSpots::getId);
        Page<ScenicSpots> data = scenicSpotsMapper.selectPage(MBPUtil.generatePage(searchVo, ScenicSpots.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveScenicSpots(ScenicSpotsVo scenicSpotsVo) {
        return Result.isStatus(scenicSpotsMapper.insert(BeanCopyUtil.copyBean(scenicSpotsVo, ScenicSpots.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateScenicSpots(ScenicSpotsVo scenicSpotsVo) {
        return Result.isStatus(scenicSpotsMapper.updateById(BeanCopyUtil.copyBean(scenicSpotsVo, ScenicSpots.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteScenicSpots(Long id) {
        return Result.isStatus(scenicSpotsMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteScenicSpots(List<Long> ids) {
        return Result.isStatus(scenicSpotsMapper.deleteBatchIds(ids));
    }

}