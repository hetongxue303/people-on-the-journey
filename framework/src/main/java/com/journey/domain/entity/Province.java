package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省份实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("province")
@Schema(name = "省份实体")
public class Province implements Serializable {

    @TableId
    @Schema(title = "ID")
    private Integer id;

    @Schema(title = "省份名称")
    private String name;

}