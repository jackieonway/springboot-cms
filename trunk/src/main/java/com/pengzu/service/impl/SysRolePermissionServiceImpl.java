package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.SysRolePermissionDao;
import com.pengzu.entity.SysRolePermission;
import com.pengzu.entity.SysRolePermissionExample;
import com.pengzu.service.SysRolePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public SysRolePermission addSysRolePermission(SysRolePermission sysRolePermission) {
        sysRolePermission.setId(UUID.randomUUID().toString());
        if (sysRolePermissionDao.insert(sysRolePermission) > 0) {
            return sysRolePermission;
        }
        return null;
    }

    @Override
    public SysRolePermission updateSysRolePermission(SysRolePermission sysRolePermission) {
        if (sysRolePermissionDao.updateByPrimaryKey(sysRolePermission) > 0) {
            return sysRolePermission;
        }
        return null;
    }

    @Override
    public SysRolePermission querySysRolePermission(SysRolePermission sysRolePermission) {
        SysRolePermissionExample sysRolePermissionExample = toSysRolePermissionExample(sysRolePermission);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionDao.selectByExample(sysRolePermissionExample);
        if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
            return sysRolePermissions.get(0);
        }
        return null;
    }

    @Override
    public List<SysRolePermission> querySysRolePermissions(SysRolePermission sysRolePermission) {
        SysRolePermissionExample sysRolePermissionExample = toSysRolePermissionExample(sysRolePermission);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionDao.selectByExample(sysRolePermissionExample);
        if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
            return sysRolePermissions;
        }
        return null;
    }

    @Override
    public Long querySysRolePermissionCount(SysRolePermission sysRolePermission) {
        SysRolePermissionExample sysRolePermissionExample = toSysRolePermissionExample(sysRolePermission);
        return sysRolePermissionDao.countByExample(sysRolePermissionExample);
    }

    @Override
    public Integer deleteSysRolePermission(SysRolePermission sysRolePermission) {
        SysRolePermissionExample sysRolePermissionExample = toSysRolePermissionExample(sysRolePermission);
        return sysRolePermissionDao.deleteByExample(sysRolePermissionExample);
    }

    private SysRolePermissionExample toSysRolePermissionExample(SysRolePermission sysRolePermission) {
        SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
        PageHelper.startPage(sysRolePermission.getPageNum(), sysRolePermission.getPageSize());
        SysRolePermissionExample.Criteria criteria = sysRolePermissionExample.createCriteria();
        if (StringUtils.isNotEmpty(sysRolePermission.getId())) {
            criteria.andIdEqualTo(sysRolePermission.getId());
        }
        if (StringUtils.isNotEmpty(sysRolePermission.getRid())) {
            criteria.andRidEqualTo(sysRolePermission.getRid());
        }
        if (StringUtils.isNotEmpty(sysRolePermission.getPid())) {
            criteria.andPidEqualTo(sysRolePermission.getPid());
        }
        return sysRolePermissionExample;
    }
}
