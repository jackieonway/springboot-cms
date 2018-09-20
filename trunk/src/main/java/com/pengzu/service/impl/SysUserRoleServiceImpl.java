package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.SysUserRoleDao;
import com.pengzu.entity.SysUserRole;
import com.pengzu.entity.SysUserRoleExample;
import com.pengzu.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysUserRole addSysUserRole(SysUserRole sysUserRole) {
        sysUserRole.setId(UUID.randomUUID().toString());
        if (sysUserRoleDao.insert(sysUserRole) > 0) {
            return sysUserRole;
        }
        return null;
    }

    @Override
    public SysUserRole updateSysUserRole(SysUserRole sysUserRole) {
        if (sysUserRoleDao.updateByPrimaryKey(sysUserRole) > 0) {
            return sysUserRole;
        }
        return null;
    }

    @Override
    public SysUserRole querySysUserRole(SysUserRole sysUserRole) {
        SysUserRoleExample sysUserRoleExample = toSysUserRoleExample(sysUserRole);
        List<SysUserRole> sysUserRoles = sysUserRoleDao.selectByExample(sysUserRoleExample);
        if (sysUserRoles != null && sysUserRoles.size() > 0) {
            return sysUserRoles.get(0);
        }
        return null;
    }

    @Override
    public List<SysUserRole> querySysUserRoles(SysUserRole sysUserRole) {
        return null;
    }

    @Override
    public Long querySysUserRoleCount(SysUserRole sysUserRole) {
        return null;
    }

    @Override
    public Integer deleteSysUserRole(SysUserRole sysUserRole) {
        SysUserRoleExample sysUserRoleExample = toSysUserRoleExample(sysUserRole);
        return sysUserRoleDao.deleteByExample(sysUserRoleExample);
    }

    private SysUserRoleExample toSysUserRoleExample(SysUserRole sysUserRole) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        PageHelper.startPage(sysUserRole.getPageNum(), sysUserRole.getPageSize());
        SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
        if (StringUtils.isNotEmpty(sysUserRole.getId())) {
            criteria.andIdEqualTo(sysUserRole.getId());
        }
        if (StringUtils.isNotEmpty(sysUserRole.getRid())) {
            criteria.andRidEqualTo(sysUserRole.getRid());
        }
        if (StringUtils.isNotEmpty(sysUserRole.getUid())) {
            criteria.andUidEqualTo(sysUserRole.getUid());
        }
        return sysUserRoleExample;
    }

}
