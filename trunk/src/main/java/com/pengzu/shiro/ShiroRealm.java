package com.pengzu.shiro;

import com.pengzu.entity.*;
import com.pengzu.entity.vo.SysPermissionVo;
import com.pengzu.entity.vo.SysRoleVo;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.service.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro身份校验核心类
 */

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser user = null;
        // 从数据库获取对应用户名密码的用户
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setUsername(name);
        sysUserVo.setPassword(password);
        user = sysUserService.querySysUser(sysUserVo);
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if ("0".equals(user.getStatus() + "")) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已被禁止登录！");
        } else {
            //登录成功
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            user.setPassword(password);
            sysUserService.updateSysUser(user);
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getId();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUid(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        sysUserRole = sysUserRoleService.querySysUserRole(sysUserRole);
        SysRoleVo sysRoleVo = new SysRoleVo();
        if (sysUserRole != null) {
            //根据用户ID查询角色（role），放入到Authorization里。
            sysRoleVo.setId(sysUserRole.getRid());
            SysRole sysRole = sysRoleService.querySysRole(sysRoleVo);
            Set<String> roleSet = new HashSet<String>();
            roleSet.add(sysRole.getRoleCode());
            info.setRoles(roleSet);
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setRid(sysUserRole.getRid());
            sysRolePermission.setPageNum(1);
            sysRolePermission.setPageSize(1000);
            List<SysRolePermission> sysRolePermissions = sysRolePermissionService.querySysRolePermissions(sysRolePermission);
            Set<String> permissionSet = new HashSet<String>();
            if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
                for (SysRolePermission sysRolePermission1 : sysRolePermissions) {
                    SysPermissionVo sysPermissionVo = new SysPermissionVo();
                    sysPermissionVo.setId(sysRolePermission1.getPid());
                    SysPermission sysPermission = sysPermissionService.querySysPermission(sysPermissionVo);
                    if (sysPermission != null) {
                        permissionSet.add(sysPermission.getPermissionCode());
                    }
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }
}
