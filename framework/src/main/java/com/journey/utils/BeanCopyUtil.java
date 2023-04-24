package com.journey.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * bean拷贝工具类
 *
 * @author hy
 * @version 1.0
 */
public class BeanCopyUtil {

    private BeanCopyUtil() {
    }

    /**
     * 复制Bean对象
     *
     * @param source 源对象
     * @param clazz  复制类型
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        T result = null;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量复制Bean对象
     *
     * @param list  源对象列表
     * @param clazz 复制类型
     */
    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream().map(item -> copyBean(item, clazz)).collect(Collectors.toList());
    }

}