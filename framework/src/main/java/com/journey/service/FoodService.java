package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Food;
import com.journey.domain.vo.FoodVo;
import com.journey.domain.vo.SearchVo;

import java.util.List;

/**
 * 美食业务
 *
 * @author hy
 * @version 1.0
 */
public interface FoodService extends IService<Food> {

    Result selectAll();

    Result selectList(SearchVo searchVo);

    Result saveFood(FoodVo foodVo);

    Result updateFood(FoodVo foodVo);

    Result deleteFood(Long id);

    Result batchDeleteFood(List<Long> ids);

}