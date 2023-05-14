package com.journey.service.impl;

import com.journey.domain.common.Result;
import com.journey.mapper.FoodMapper;
import com.journey.mapper.ScenicSpotsMapper;
import com.journey.mapper.TravelAgencyMapper;
import com.journey.service.PortalService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 门户业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class PortalServiceImpl implements PortalService {

    @Resource
    private ScenicSpotsMapper scenicSpotsMapper;
    @Resource
    private FoodMapper foodMapper;
    @Resource
    private TravelAgencyMapper travelAgencyMapper;

    @Override
    @Cacheable(cacheNames = "portal[search]")
    public Result selectSearchList() {
        return null;
    }
}