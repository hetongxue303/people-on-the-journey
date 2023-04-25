package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.ScenicSpots;
import com.journey.domain.vo.ScenicSpotsVo;
import com.journey.domain.vo.SearchVo;

import java.util.List;

/**
 * 景点业务
 *
 * @author hy
 * @version 1.0
 */
public interface ScenicSpotsService extends IService<ScenicSpots> {
    Result selectAll();

    Result saveScenicSpots(ScenicSpotsVo scenicSpotsVo);

    Result selectList(SearchVo searchVo);

    Result updateScenicSpots(ScenicSpotsVo scenicSpotsVo);

    Result deleteScenicSpots(Long id);

    Result batchDeleteScenicSpots(List<Long> ids);
}