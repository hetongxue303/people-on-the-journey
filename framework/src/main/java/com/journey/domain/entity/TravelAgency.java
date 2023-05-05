package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 旅行社实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("j_travel_agency")
@Schema(name = "旅行社实体")
public class TravelAgency implements Serializable {

    @TableId
    @Schema(title = "旅行社ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "图片")
    private String image;

    @Schema(title = "介绍")
    private String intro;

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