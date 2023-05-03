package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "角色VO")
public class RoleVo implements Serializable {

    @Schema(title = "角色ID")
    private Long id;

    @Schema(title = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(title = "简介")
    private String intro;

    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "更新时间")
    private Date updateTime;

}