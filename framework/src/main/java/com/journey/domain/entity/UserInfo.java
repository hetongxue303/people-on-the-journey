package com.journey.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_userinfo")
@Schema(name = "用户信息实体")
public class UserInfo implements Serializable {

    @TableId
    @Schema(title = "用户信息ID")
    private Long id;

    @Schema(title = "昵称")
    private String nickname;

    @Schema(title = "性别")
    private Integer gender;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "简介")
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