package com.journey.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 门户VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "门户VO")
public class IndexVo implements Serializable {

    @Schema(title = "前十景点")
    private List<ScenicSpotsVo> scenicSpots;

    @Schema(title = "前十美食")
    private List<FoodVo> foods;

    @Schema(title = "前十旅社")
    private List<TravelAgencyVo> travelAgencies;

}