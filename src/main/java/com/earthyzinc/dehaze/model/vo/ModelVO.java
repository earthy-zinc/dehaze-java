package com.earthyzinc.dehaze.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "模型视图对象")
@Data
public class ModelVO {

    @Schema(description = "模型ID")
    private Long id;

    @Schema(description = "父模型ID")
    private Long parentId;

    @Schema(description = "模型名称")
    private String name;

    @Schema(description = "模型存储路径")
    private String path;

    @Schema(description = "子模型")
    private List<ModelVO> children;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
}
