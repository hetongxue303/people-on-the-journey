package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.TravelAgency;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.TravelAgencyVo;

import java.util.List;

/**
 * 旅行社业务
 *
 * @author hy
 * @version 1.0
 */
public interface TravelAgencyService extends IService<TravelAgency> {

    Result selectAll();

    Result selectList(SearchVo searchVo);

    Result saveTravelAgency(TravelAgencyVo travelAgencyVo);

    Result updateTravelAgency(TravelAgencyVo travelAgencyVo);

    Result deleteTravelAgency(Long id);

    Result batchDeleteTravelAgency(List<Long> ids);
    
}