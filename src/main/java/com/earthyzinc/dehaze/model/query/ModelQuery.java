package com.earthyzinc.dehaze.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="模型分页查询对象")
@Data
public class ModelQuery {

    @Schema(description="关键字(模型名称)")
    private String keywords;
}
