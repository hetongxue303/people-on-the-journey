package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 城市实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("city")
@Schema(name = "城市实体")
public class City implements Serializable {

    @TableId
    @Schema(title = "ID")
    private Integer id;

    @Schema(title = "省份ID")
    private Integer pid;

    @Schema(title = "城市名称")
    private String name;

}