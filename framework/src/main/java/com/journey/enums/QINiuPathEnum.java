package com.journey.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 何同学
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum QINiuPathEnum {

    AVATAR(1, "journey/images/avatar/", "头像路径"),

    FOOD(2, "journey/images/foods/", "美食路径"),

    SCENIC_SPOTS(3, "journey/images/scenic-spots/", "景区路径"),

    TRAVEL_AGENCY(4, "journey/images/travel-agency/", "旅行社路径"),

    MARKDOWN(5, "journey/others/markdown/", "md文件路径");

    private final Integer code;
    private final String path;
    private final String desc;

}