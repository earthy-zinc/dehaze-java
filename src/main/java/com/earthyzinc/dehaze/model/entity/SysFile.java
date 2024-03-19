package com.earthyzinc.dehaze.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.earthyzinc.dehaze.common.base.BaseEntity;
import lombok.Data;

@TableName(value = "sys_file")
@Data
public class SysFile extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件类型
     */
    private String extendName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件的MD5属性
     */
    private String md5;

}
