package com.journey.controller;

import com.journey.annotation.PrintLog;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Area;
import com.journey.domain.entity.City;
import com.journey.domain.entity.Province;
import com.journey.domain.entity.Street;
import com.journey.filter.CascadeFilter;
import com.journey.mapper.AreaMapper;
import com.journey.mapper.CityMapper;
import com.journey.mapper.ProvinceMapper;
import com.journey.mapper.StreetMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公共模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("cascade")
@Schema(name = "公共模块")
public class CommonController {

    @Resource
    private AreaMapper areaMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private StreetMapper streetMapper;
    @Resource
    private ProvinceMapper provinceMapper;

    @GetMapping("tree")
    @PrintLog("获取省份菜单树")
    @Operation(summary = "获取省份菜单树")
    public Result getCascadeTree() {
        List<Province> provinces = provinceMapper.selectList(null);
        List<City> cities = cityMapper.selectList(null);
        List<Area> areas = areaMapper.selectList(null);
        List<Street> streets = streetMapper.selectList(null);
        return Result.success(CascadeFilter.filterProvince(provinces, cities, areas, streets));
    }

}