package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "用户VO")
public class UserVo {

    @Schema(title = "用户ID")
    private Long id;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "用户信息")
    private UserInfoVo userinfo;

    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "发表时间")
    private Date createTime;

    @Schema(title = "更新时间")
    private Date updateTime;

}