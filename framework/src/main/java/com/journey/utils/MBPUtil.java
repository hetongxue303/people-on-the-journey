package com.journey.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.journey.domain.vo.SearchVo;

/**
 * mybatis-plus工具类
 *
 * @author hy
 * @version 1.0
 */
public class MBPUtil {

    public static <T> Page<T> generatePage(SearchVo searchVo, Class<T> clazz) {
        return new Page<>(searchVo.getCurrent(), searchVo.getSize());
    }

}