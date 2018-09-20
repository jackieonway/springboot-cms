package com.pengzu.service;

import com.pengzu.entity.SysUserRole;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface SysUserRoleService {

    SysUserRole addSysUserRole(SysUserRole sysUserRole);

    SysUserRole updateSysUserRole(SysUserRole sysUserRole);

    SysUserRole querySysUserRole(SysUserRole sysUserRole);

    List<SysUserRole> querySysUserRoles(SysUserRole sysUserRole);

    Long querySysUserRoleCount(SysUserRole sysUserRole);

    Integer deleteSysUserRole(SysUserRole sysUserRole);
}
