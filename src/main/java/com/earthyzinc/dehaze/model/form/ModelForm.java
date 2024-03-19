package com.earthyzinc.dehaze.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "模型表单对象")
@Data
public class ModelForm {
    @Schema(description = "模型ID")
    private Long id;

    @Schema(description = "父模型ID")
    @NotNull(message = "父模型ID不能为空")
    private Long parentId;

    @Schema(description = "模型名称")
    private String name;

    @Schema(description = "模型存储路径")
    private String path;
}
