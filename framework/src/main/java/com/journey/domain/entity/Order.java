package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("j_order")
@Schema(name = "订单实体")
public class Order implements Serializable {

    @TableId
    @Schema(title = "订单ID")
    private Long id;

    @Schema(title = "订单号")
    private String orderNum;

    @Schema(title = "预定人ID")
    private Long userId;

    @Schema(title = "景区ID")
    private Long sId;

    @Schema(title = "美食ID")
    private Long fId;

    @Schema(title = "旅社ID")
    private Long tId;

    @Schema(title = "备注")
    private String remark;

    @Schema(title = "状态", description = "0:已预约 1:已接受 2:已拒绝")
    private Integer status;

    @TableLogic
    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}