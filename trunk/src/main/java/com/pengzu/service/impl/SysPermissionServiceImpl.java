package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.SysPermissionDao;
import com.pengzu.entity.SysPermission;
import com.pengzu.entity.SysPermissionExample;
import com.pengzu.entity.vo.SysPermissionVo;
import com.pengzu.service.SysPermissionService;
import com.pengzu.utils.BeanConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public SysPermission addSysPermission(SysPermissionVo sysPermissionVo) {
        sysPermissionVo.setId(UUID.randomUUID().toString());
        if (StringUtils.isEmpty(sysPermissionVo.getParentId())) {
            sysPermissionVo.setParentId("0");
        }
        SysPermission sysPermission = BeanConvertUtils.convert(sysPermissionVo, SysPermission.class);
        return sysPermissionDao.insertSelective(sysPermission) == 0 ? null : sysPermission;
    }

    @Override
    public SysPermission updateSysPermission(SysPermissionVo sysPermissionVo) {
        SysPermission sysPermission = BeanConvertUtils.convert(sysPermissionVo, SysPermission.class);
        return sysPermissionDao.updateByPrimaryKeySelective(sysPermission) == 0 ? null : sysPermission;
    }

    @Override
    public SysPermission querySysPermission(SysPermissionVo sysPermission) {
        List<SysPermission> sysPermissions = sysPermissionDao.selectByExample(
                setSysPermissionExample(sysPermission));
        if (sysPermissions != null && sysPermissions.size() > 0) {
            return sysPermissions.get(0);
        } else {
            return null;
        }
    }

    private SysPermissionExample setSysPermissionExample(SysPermissionVo sysPermission) {
        SysPermissionExample sysPermissionExample = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = sysPermissionExample.createCriteria();
        PageHelper.startPage(sysPermission.getPageNum(), sysPermission.getPageSize());
        if (StringUtils.isNotEmpty(sysPermission.getId())) {
            criteria.andIdEqualTo(sysPermission.getId());
        }
        if (StringUtils.isNotEmpty(sysPermission.getPermissionCode())) {
            criteria.andPermissionCodeEqualTo(sysPermission.getPermissionCode());
        }
        if (StringUtils.isNotEmpty(sysPermission.getPermissionType())) {
            criteria.andPermissionTypeEqualTo(sysPermission.getPermissionType());
        }
        if (StringUtils.isNotEmpty(sysPermission.getParentId())) {
            criteria.andParentIdEqualTo(sysPermission.getParentId());
        }
        return sysPermissionExample;
    }

    @Override
    public List<SysPermission> querySysPermissions(SysPermissionVo sysPermission) {
        SysPermissionExample sysPermissionExample = setSysPermissionExample(sysPermission);
        return sysPermissionDao.selectByExample(sysPermissionExample);
    }

    @Override
    public Long querySysPermissionCount(SysPermissionVo sysPermission) {
        return sysPermissionDao.countByExample(setSysPermissionExample(sysPermission));
    }

    @Override
    public Integer deleteSysPermission(SysPermissionVo sysPermission) {
        return sysPermissionDao.deleteByExample(setSysPermissionExample(sysPermission));
    }
}
