package com.journey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.journey.domain.common.Result;
import com.journey.domain.entity.Order;
import com.journey.domain.vo.OrderVo;
import com.journey.domain.vo.SearchVo;

import java.util.List;

/**
 * 订单服务
 *
 * @author hy
 * @version 1.0
 */
public interface OrderService extends IService<Order> {

    Result selectAll();

    Result selectList(SearchVo searchVo);

    Result saveOrder(OrderVo orderVo);

    Result updateOrder(OrderVo orderVo);

    Result deleteOrder(Long id);

    Result batchDeleteOrder(List<Long> ids);

}