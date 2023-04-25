package com.journey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.Food;
import com.journey.domain.vo.FoodVo;
import com.journey.domain.vo.SearchVo;
import com.journey.mapper.FoodMapper;
import com.journey.service.FoodService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 美食业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Resource
    private FoodMapper foodMapper;

    @Override
    public Result selectAll() {
        return Result.success(foodMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Food> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Food::getName, searchVo.getKeywords())
                .orderByDesc(Food::getId);
        Page<Food> data = foodMapper.selectPage(MBPUtil.generatePage(searchVo, Food.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveFood(FoodVo foodVo) {
        return Result.isStatus(foodMapper.insert(BeanCopyUtil.copyBean(foodVo, Food.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateFood(FoodVo foodVo) {
        return Result.isStatus(foodMapper.updateById(BeanCopyUtil.copyBean(foodVo, Food.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteFood(Long id) {
        return Result.isStatus(foodMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteFood(List<Long> ids) {
        return Result.isStatus(foodMapper.deleteBatchIds(ids));
    }

}