package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户信息VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户信息vo")
public class UserInfoVo implements Serializable {

    @Schema(title = "用户信息ID")
    @NotBlank(message = "用户信息ID不能为空")
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