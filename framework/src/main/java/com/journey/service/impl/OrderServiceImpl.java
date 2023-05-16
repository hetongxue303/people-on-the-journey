package com.journey.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.bo.FoodBo;
import com.journey.domain.bo.ScenicSpotsBo;
import com.journey.domain.bo.TravelAgencyBo;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.Food;
import com.journey.domain.entity.Order;
import com.journey.domain.entity.ScenicSpots;
import com.journey.domain.entity.TravelAgency;
import com.journey.domain.vo.FoodVo;
import com.journey.domain.vo.OrderVo;
import com.journey.domain.vo.SearchVo;
import com.journey.mapper.FoodMapper;
import com.journey.mapper.OrderMapper;
import com.journey.mapper.ScenicSpotsMapper;
import com.journey.mapper.TravelAgencyMapper;
import com.journey.service.OrderService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.CommonUtil;
import com.journey.utils.MBPUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private FoodMapper foodMapper;
    @Resource
    private ScenicSpotsMapper scenicSpotsMapper;
    @Resource
    private TravelAgencyMapper travelAgencyMapper;

    @Override
    @Cacheable(cacheNames = "order[all]")
    public Result selectAll() {
        List<Order> data = orderMapper.selectList(null);
        List<OrderVo> collect = data.stream()
                .map(item -> BeanCopyUtil.copyBean(item, OrderVo.class)
                        .setScenicSpots(BeanCopyUtil.copyBean(scenicSpotsMapper.selectOne(new LambdaQueryWrapper<ScenicSpots>().eq(ScenicSpots::getId, item.getFId())), ScenicSpotsBo.class))
                        .setFood(BeanCopyUtil.copyBean(foodMapper.selectOne(new LambdaQueryWrapper<Food>().eq(Food::getId, item.getFId())), FoodBo.class))
                        .setTravelAgency(BeanCopyUtil.copyBean(travelAgencyMapper.selectOne(new LambdaQueryWrapper<TravelAgency>().eq(TravelAgency::getId, item.getTId())), TravelAgencyBo.class)))
                .collect(Collectors.toList());
        return Result.success(collect);
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Order::getId);
        Page<Order> data = orderMapper.selectPage(MBPUtil.generatePage(searchVo, Order.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), BeanCopyUtil.copyBeanList(data.getRecords(), FoodVo.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrder(OrderVo orderVo) {
        return Result.isStatus(orderMapper.insert(new Order().setOrderNum(CommonUtil.randomOrderNum(new Date()))
                .setRemark(orderVo.getRemark())
                .setSId(orderVo.getScenicSpots().getId())
                .setFId(orderVo.getFood().getId())
                .setTId(orderVo.getTravelAgency().getId())
                .setUserId(Long.valueOf(String.valueOf(StpUtil.getLoginId())))));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateOrder(OrderVo orderVo) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteOrder(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteOrder(List<Long> ids) {
        return null;
    }
}