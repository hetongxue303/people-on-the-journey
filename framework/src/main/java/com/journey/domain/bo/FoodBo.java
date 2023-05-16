package com.journey.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 美食BO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "美食BO")
public class FoodBo implements Serializable {

    @Schema(title = "美食ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "图片")
    private String image;

    @Schema(title = "介绍")
    private String intro;

}