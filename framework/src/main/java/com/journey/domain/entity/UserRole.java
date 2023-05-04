package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
@Schema(name = "角色实体")
public class UserRole implements Serializable {

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "角色ID")
    private Long roleId;

}