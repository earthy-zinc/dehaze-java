package com.pei.dehaze.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pei.dehaze.model.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色菜单业务接口
 *
 * @author earthyzinc
 * @since 2.5.0
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> listMenuIdsByRoleId(Long roleId);


    /**
     * 刷新权限缓存(所有角色)
     */
    void refreshRolePermsCache();

    /**
     * 刷新权限缓存(指定角色)
     *
     * @param roleCode 角色编码
     */
    void refreshRolePermsCache(String roleCode);

    /**
     * 刷新权限缓存(修改角色编码时调用)
     *
     * @param oldRoleCode 旧角色编码
     * @param newRoleCode 新角色编码
     */
    void refreshRolePermsCache(String oldRoleCode, String newRoleCode);

}
