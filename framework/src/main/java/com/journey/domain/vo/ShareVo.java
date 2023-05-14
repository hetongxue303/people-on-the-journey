package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(name = "分享VO")
public class ShareVo implements Serializable {

    @Schema(title = "ID")
    private Long id;

    @Schema(title = "用户信息")
    private UserInfoVo userinfo;

    @Schema(title = "图片")
    private String image;

    @Schema(title = "内容")
    private String content;

    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "发布时间")
    private Date createTime;

    @Schema(title = "更新时间")
    private Date updateTime;

}