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
 * 街道实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("street")
@Schema(name = "街道实体")
public class Street implements Serializable {

    @TableId
    @Schema(title = "ID")
    private Integer id;

    @Schema(title = "区域ID")
    private Integer pid;

    @Schema(title = "街道名称")
    private String name;

}