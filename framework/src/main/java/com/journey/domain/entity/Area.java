package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 区域实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("area")
@Schema(name = "区域实体")
public class Area implements Serializable {

    @TableId
    @Schema(title = "ID")
    private Integer id;

    @Schema(title = "城市ID")
    private Integer pid;

    @Schema(title = "区域名称")
    private String name;

}