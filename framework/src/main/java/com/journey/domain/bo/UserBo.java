package com.journey.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户BO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "用户BO")
public class UserBo implements Serializable {

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "用户信息ID")
    private Long userinfoId;

    @Schema(title = "用户名")
    private String username;

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