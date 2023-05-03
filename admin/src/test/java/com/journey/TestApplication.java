package com.journey;

import com.journey.domain.entity.Area;
import com.journey.domain.entity.City;
import com.journey.domain.entity.Province;
import com.journey.domain.entity.Street;
import com.journey.domain.vo.PropsVo;
import com.journey.filter.CascadeFilter;
import com.journey.mapper.AreaMapper;
import com.journey.mapper.CityMapper;
import com.journey.mapper.ProvinceMapper;
import com.journey.mapper.StreetMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hy
 * @version 1.0
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private AreaMapper areaMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private StreetMapper streetMapper;
    @Resource
    private ProvinceMapper provinceMapper;

    @Test
    void test() {
        List<Province> provinces = provinceMapper.selectList(null);
        List<City> cities = cityMapper.selectList(null);
        List<Area> areas = areaMapper.selectList(null);
        List<Street> streets = streetMapper.selectList(null);
        List<PropsVo> list = CascadeFilter.filterProvince(provinces, cities, areas, streets);
        list.forEach(System.out::println);
    }


}