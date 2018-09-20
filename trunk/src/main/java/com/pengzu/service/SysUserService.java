package com.pengzu.service;

import com.pengzu.entity.SysUser;
import com.pengzu.entity.vo.SysUserVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface SysUserService {

    SysUser addSysUser(SysUser sysUser);

    SysUser updateSysUser(SysUser sysUser);

    SysUser querySysUser(SysUserVo sysUserVo);

    List<SysUser> querySysUsers(SysUserVo sysUserVo);

    Long querySysUserCount(SysUserVo sysUserVo);

    Integer deleteSysUser(SysUser sysUser);
}
