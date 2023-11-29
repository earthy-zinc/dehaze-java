package com.earthyzinc.dehaze.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earthyzinc.dehaze.model.bo.UserBO;
import com.earthyzinc.dehaze.model.bo.UserFormBO;
import com.earthyzinc.dehaze.model.entity.SysUser;
import com.earthyzinc.dehaze.model.form.UserForm;
import com.earthyzinc.dehaze.model.vo.UserImportVO;
import com.earthyzinc.dehaze.model.vo.UserInfoVO;
import com.earthyzinc.dehaze.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.earthyzinc.dehaze.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.earthyzinc.dehaze.common.enums.GenderEnum.class))")
    })
    UserPageVO bo2Vo(UserBO bo);

    Page<UserPageVO> bo2Vo(Page<UserBO> bo);

    UserForm bo2Form(UserFormBO bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO entity2UserInfoVo(SysUser entity);

    SysUser importVo2Entity(UserImportVO vo);

}
