package com.earthyzinc.dehaze.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.earthyzinc.dehaze.common.base.IBaseEnum;
import lombok.Getter;

public enum ModelTypeEnum implements IBaseEnum<Integer> {
    DEHAZE(0, "图像去雾");


    @Getter
    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private Integer value;

    @Getter
    // @JsonValue //  表示对枚举序列化时返回此字段
    private String label;

    ModelTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
