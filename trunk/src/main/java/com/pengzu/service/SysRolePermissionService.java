package com.pengzu.service;

import com.pengzu.entity.SysRolePermission;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface SysRolePermissionService {

    SysRolePermission addSysRolePermission(SysRolePermission sysRolePermission);

    SysRolePermission updateSysRolePermission(SysRolePermission sysRolePermission);

    SysRolePermission querySysRolePermission(SysRolePermission sysRolePermission);

    List<SysRolePermission> querySysRolePermissions(SysRolePermission sysRolePermission);

    Long querySysRolePermissionCount(SysRolePermission sysRolePermission);

    Integer deleteSysRolePermission(SysRolePermission sysRolePermission);
}
