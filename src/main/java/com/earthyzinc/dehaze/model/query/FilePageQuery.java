package com.earthyzinc.dehaze.model.query;

import com.earthyzinc.dehaze.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FilePageQuery extends BasePageQuery {
    @Schema(description="关键字(文件名称，文件ID等)")
    private String keywords;
}
