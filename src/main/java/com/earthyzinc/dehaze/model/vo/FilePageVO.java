package com.earthyzinc.dehaze.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "文件视图对象")
@Data
public class FilePageVO {

    @Schema(description = "文件ID")
    private Long id;

    /**
     * 文件url
     */
    @Schema(description = "文件URL")
    private String fileUrl;

    /**
     * 文件名
     */
    @Schema(description = "文件名称")
    private String fileName;

    /**
     * 文件大小
     */
    @Schema(description = "文件大小")
    private Integer fileSize;

    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String extendName;

    /**
     * 文件路径
     */
    @Schema(description = "文件路径")
    private String filePath;

    /**
     * 文件的MD5属性
     */
    @Schema(description = "文件MD5")
    private String md5;
}
