package com.journey.domain.vo;

import com.journey.domain.bo.FoodBo;
import com.journey.domain.bo.ScenicSpotsBo;
import com.journey.domain.bo.TravelAgencyBo;
import com.journey.domain.bo.UserBo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(name = "订单VO")
public class OrderVo implements Serializable {

    @Schema(title = "订单ID")
    private Long id;

    @Schema(title = "订单号")
    private String orderNum;

    @Schema(title = "预定人信息")
    private UserBo user;

    @Schema(title = "景区信息")
    private ScenicSpotsBo scenicSpots;

    @Schema(title = "美食信息")
    private FoodBo food;

    @Schema(title = "旅社信息")
    private TravelAgencyBo travelAgency;

    @Schema(title = "备注")
    private String remark;

    @Schema(title = "状态", description = "0:已预约 1:已接受 2:已拒绝")
    private Integer status;

    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "更新时间")
    private Date updateTime;

}