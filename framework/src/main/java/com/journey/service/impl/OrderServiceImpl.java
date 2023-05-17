package com.journey.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.journey.domain.bo.FoodBo;
import com.journey.domain.bo.ScenicSpotsBo;
import com.journey.domain.bo.TravelAgencyBo;
import com.journey.domain.bo.UserBo;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.*;
import com.journey.domain.vo.FoodVo;
import com.journey.domain.vo.OrderVo;
import com.journey.domain.vo.SearchVo;
import com.journey.handler.exception.customs.SystemException;
import com.journey.mapper.*;
import com.journey.service.OrderService;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.CommonUtil;
import com.journey.utils.MBPUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private RateMapper rateMapper;
    @Resource
    private UserinfoMapper userinfoMapper;

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
        return Result.isStatus(orderMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteOrder(List<Long> ids) {
        return Result.isStatus(orderMapper.deleteBatchIds(ids));
    }

    @Override
    public Result selectById(Long id, SearchVo searchVo) {
        Long loginId = Long.valueOf(String.valueOf(StpUtil.getLoginId()));
        if (!Objects.equals(loginId, id))
            throw new SystemException("用户不正确");
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Order::getId).eq(Order::getUserId, id);
        Page<Order> data = orderMapper.selectPage(MBPUtil.generatePage(searchVo, Order.class), wrapper);
        List<OrderVo> collects = Optional.ofNullable(data.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, OrderVo.class)
                        .setRate(Optional.ofNullable(rateMapper.selectOne(new LambdaQueryWrapper<Rate>().eq(Rate::getOrderId, item.getTId())))
                                .orElse(new Rate())
                                .getNum())
                        .setUser(BeanCopyUtil.copyBean(userinfoMapper.selectById(userMapper.selectById(item.getUserId())
                                        .getUserinfoId()), UserBo.class)
                                .setUsername(userMapper.selectById(item.getUserId()).getUsername())
                                .setUserId(userMapper.selectById(item.getUserId()).getId())
                                .setUserinfoId(userMapper.selectById(item.getUserId()).getUserinfoId()))
                        .setScenicSpots(BeanCopyUtil.copyBean(scenicSpotsMapper.selectById(item.getSId()), ScenicSpotsBo.class))
                        .setFood(BeanCopyUtil.copyBean(foodMapper.selectById(item.getFId()), FoodBo.class))
                        .setTravelAgency(BeanCopyUtil.copyBean(travelAgencyMapper.selectById(item.getTId()), TravelAgencyBo.class)))
                .collect(Collectors.toList());
        return Result.success(new ResultPage(data.getTotal(), collects));
    }
}