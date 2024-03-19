package com.earthyzinc.dehaze.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.earthyzinc.dehaze.common.base.BaseEntity;
import com.earthyzinc.dehaze.common.enums.ModelTypeEnum;
import lombok.Data;

/**
 * 模型实体对象
 *
 * @author earthy zinc
 * @since 2023/12/27
 */
@TableName(value ="sys_model")
@Data
public class SysModel extends BaseEntity {
    /**
     * 模型ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父模型ID
     */
    private Long parentId;

    /**
     * 模型类型(0-图像去雾)
     */
    private ModelTypeEnum type;
    /**
     * 模型名称
     */
    private String name;
    /**
     * 模型路径(在服务器上文件存储路径)
     */
    private String path;

    /**
     * 模型详细描述
     */
    private String description;

}
