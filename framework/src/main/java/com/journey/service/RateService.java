package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Rate;
import com.journey.domain.vo.RateVo;
import com.journey.domain.vo.SearchVo;

import java.util.List;

/**
 * @author hy
 * @version 1.0
 */
public interface RateService extends IService<Rate> {
    Result selectAll();

     Result selectList(SearchVo searchVo);

     Result saveRate(RateVo rateVo);

     Result updateRate(RateVo rateVo);

     Result deleteRate(Long id);

     Result batchDeleteRate(List<Long> ids);
}