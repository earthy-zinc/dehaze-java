package com.pei.dehaze.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pei.dehaze.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色访问层
 *
 * @author earthyzinc
 * @since 2022/1/15
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取角色绑定的用户数
     *
     * @param roleId 角色ID
     */
    int countUsersForRole(Long roleId);
}
