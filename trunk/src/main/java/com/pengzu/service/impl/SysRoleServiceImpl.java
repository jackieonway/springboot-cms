package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.SysRoleDao;
import com.pengzu.entity.SysRole;
import com.pengzu.entity.SysRoleExample;
import com.pengzu.entity.vo.SysRoleVo;
import com.pengzu.service.SysRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRole addSysRole(SysRoleVo sysRoleVo) {
        try {
            sysRoleVo.setIsDelete(0);
            SysRole sysRole = BeanConvertUtils.convert(sysRoleVo, SysRole.class);
            sysRole.setId(UUID.randomUUID().toString());
            return sysRoleDao.insertSelective(sysRole) == 0 ? null : sysRole;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SysRole updateSysRole(SysRoleVo sysRoleVo) {
        try {
            SysRole sysRole = BeanConvertUtils.convert(sysRoleVo, SysRole.class);
            return sysRoleDao.updateByPrimaryKeySelective(sysRole) == 0 ? null : sysRole;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SysRole querySysRole(SysRoleVo sysRole) {
        try {
            sysRole.setIsDelete(0);
            List<SysRole> sysRoles = sysRoleDao.selectByExample(setSysRoleExample(sysRole));
            if (sysRoles != null && sysRoles.size() > 0) {
                return sysRoles.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SysRole> querySysRoles(SysRoleVo sysRole) {
        try {
            sysRole.setIsDelete(0);
            return sysRoleDao.selectByExample(setSysRoleExample(sysRole));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long querySysRoleCount(SysRoleVo sysRole) {
        try {
            return sysRoleDao.countByExample(setSysRoleExample(sysRole));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer deleteSysRole(SysRoleVo sysRole) {
        try {
            sysRole.setIsDelete(1);
            return sysRoleDao.updateByExampleSelective(
                    BeanConvertUtils.convert(sysRole, SysRole.class),
                    setSysRoleExample(sysRole));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SysRole> querySysRolesByKeywords(SysRoleVo sysRole) {
        try {
            sysRole.setIsDelete(0);
            return sysRoleDao.selectByExample(setSysRoleExample(sysRole));
        } catch (Exception e) {
            return null;
        }
    }

    private SysRoleExample setSysRoleExample(SysRoleVo sysRoleVo) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        PageHelper.startPage(sysRoleVo.getPageNum(), sysRoleVo.getPageSize());
        if (sysRoleVo.getIsDelete() != null && sysRoleVo.getIsDelete() != 1) {
            criteria.andIsDeleteEqualTo(0);
        }
        if (StringUtils.isNotEmpty(sysRoleVo.getId())) {
            criteria.andIdEqualTo(sysRoleVo.getId());
        }
        if (StringUtils.isNotEmpty(sysRoleVo.getKeywords())) {
            criteria.andRoleCodeLike(sysRoleVo.getKeywords());
        }
        if (StringUtils.isNotEmpty(sysRoleVo.getRoleCode())) {
            criteria.andRoleCodeEqualTo(sysRoleVo.getRoleCode());
        }
        if (StringUtils.isNotEmpty(sysRoleVo.getType())) {
            criteria.andTypeEqualTo(sysRoleVo.getType());
        }
        return sysRoleExample;
    }
}
