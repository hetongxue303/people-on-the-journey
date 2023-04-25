package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录Vo
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "登录VO")
public class LoginVo implements Serializable {

    @Schema(title = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(title = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}