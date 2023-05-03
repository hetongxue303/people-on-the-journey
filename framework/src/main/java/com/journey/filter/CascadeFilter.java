package com.journey.filter;

import com.journey.domain.entity.Area;
import com.journey.domain.entity.City;
import com.journey.domain.entity.Province;
import com.journey.domain.entity.Street;
import com.journey.domain.vo.PropsVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 级联过滤器
 *
 * @author hy
 * @version 1.0
 */
public class CascadeFilter {

    public static List<PropsVo> filterProvince(List<Province> provinces, List<City> cities, List<Area> areas, List<Street> streets) {
        ArrayList<PropsVo> result = new ArrayList<>();
        provinces.forEach(item -> result.add(new PropsVo(item.getId(), item.getName(), filterCity(item.getId(), cities, areas, streets))));
        return result;
    }

    public static List<PropsVo> filterCity(Integer pid, List<City> cities, List<Area> areas, List<Street> streets) {
        ArrayList<PropsVo> result = new ArrayList<>();
        cities.stream()
                .filter(item -> Objects.deepEquals(item.getPid(), pid))
                .collect(Collectors.toList())
                .forEach(item -> result.add(new PropsVo(item.getId(), item.getName(), filterArea(item.getId(), areas, streets))));
        return result;
    }

    public static List<PropsVo> filterArea(Integer pid, List<Area> areas, List<Street> streets) {
        ArrayList<PropsVo> result = new ArrayList<>();
        areas.stream()
                .filter(item -> Objects.deepEquals(item.getPid(), pid))
                .collect(Collectors.toList())
                .forEach(item -> result.add(new PropsVo(item.getId(), item.getName(), filterStreet(item.getId(), streets))));
        return result;
    }

    public static List<PropsVo> filterStreet(Integer pid, List<Street> streets) {
        ArrayList<PropsVo> result = new ArrayList<>();
        streets.stream()
                .filter(item -> Objects.deepEquals(item.getPid(), pid))
                .collect(Collectors.toList())
                .forEach(item -> result.add(new PropsVo(item.getId(), item.getName(), null)));
        return result;
    }

}