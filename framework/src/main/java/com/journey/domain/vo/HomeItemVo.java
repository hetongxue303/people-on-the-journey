package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 主页VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "主页VO")
public class HomeItemVo implements Serializable {

    @Schema(title = "用户信息")
    private UserInfoVo userinfo;

    @Schema(title = "分享信息")
    private ShareVo share;

}