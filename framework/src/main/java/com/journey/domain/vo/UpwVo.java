package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 更改密码Vo
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "更改密码Vo")
public class UpwVo implements Serializable {

    @Schema(title = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @Schema(title = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @Schema(title = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

}