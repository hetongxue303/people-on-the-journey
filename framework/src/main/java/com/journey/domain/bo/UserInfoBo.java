package com.journey.domain.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_userinfo")
@Schema(name = "用户信息BO")
public class UserInfoBo implements Serializable {

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

}