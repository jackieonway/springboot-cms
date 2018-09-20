package com.pengzu.service;

import com.pengzu.entity.SysPermission;
import com.pengzu.entity.vo.SysPermissionVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface SysPermissionService {

    SysPermission addSysPermission(SysPermissionVo sysPermission);

    SysPermission updateSysPermission(SysPermissionVo sysPermission);

    SysPermission querySysPermission(SysPermissionVo sysPermission);

    List<SysPermission> querySysPermissions(SysPermissionVo sysPermission);

    Long querySysPermissionCount(SysPermissionVo sysPermission);

    Integer deleteSysPermission(SysPermissionVo sysPermission);
}
