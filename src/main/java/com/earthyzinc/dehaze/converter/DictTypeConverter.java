package com.earthyzinc.dehaze.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earthyzinc.dehaze.model.entity.SysDictType;
import com.earthyzinc.dehaze.model.form.DictTypeForm;
import com.earthyzinc.dehaze.model.vo.DictTypePageVO;
import org.mapstruct.Mapper;

/**
 * 字典类型对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);
}
