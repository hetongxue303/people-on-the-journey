package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 景点表
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("j_scenic_spots")
@Schema(name = "景点表")
public class ScenicSpots implements Serializable {

    @TableId
    @Schema(title = "景点ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "介绍")
    private String intro;

    @TableLogic
    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "发表时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}