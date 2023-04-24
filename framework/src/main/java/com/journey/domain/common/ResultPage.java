package com.journey.domain.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页查询结果类
 *
 * @author hy
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "分页查询结果类")
public class ResultPage implements Serializable {

    @Schema(title = "总条数")
    private Long total;
    @Schema(title = "数据列表")
    private Object records;

}