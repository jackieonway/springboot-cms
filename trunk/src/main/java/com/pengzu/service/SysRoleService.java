package com.pengzu.service;

import com.pengzu.entity.SysRole;
import com.pengzu.entity.vo.SysRoleVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface SysRoleService {

    SysRole addSysRole(SysRoleVo sysRole);

    SysRole updateSysRole(SysRoleVo sysRole);

    SysRole querySysRole(SysRoleVo sysRole);

    List<SysRole> querySysRoles(SysRoleVo sysRole);

    Long querySysRoleCount(SysRoleVo sysRole);

    Integer deleteSysRole(SysRoleVo sysRole);

    List<SysRole> querySysRolesByKeywords(SysRoleVo sysRole);
}
