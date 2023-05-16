package com.journey.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 旅社BO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "旅社BO")
public class TravelAgencyBo implements Serializable {

    @Schema(title = "旅行社ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "图片")
    private String image;

    @Schema(title = "介绍")
    private String intro;

}