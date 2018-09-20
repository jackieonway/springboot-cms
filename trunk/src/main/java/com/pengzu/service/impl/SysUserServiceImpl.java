package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.SysUserDao;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.SysUserExample;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.service.SysUserService;
import com.pengzu.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser addSysUser(SysUser sysUser) {
        Date now = new Date();
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        sysUser.setCreateTime(now);
        if (sysUserDao.insertSelective(sysUser) > 0) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser updateSysUser(SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getPassword())) {
            sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
        }
        if (sysUserDao.updateByPrimaryKeySelective(sysUser) > 0) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser querySysUser(SysUserVo sysUserVo) {
        SysUserExample sysUserExample = toSysUserExample(sysUserVo);
        List<SysUser> sysUsers = sysUserDao.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }
        return null;
    }


    @Override
    public List<SysUser> querySysUsers(SysUserVo sysUserVo) {
        SysUserExample sysUserExample = toSysUserExample(sysUserVo);
        List<SysUser> sysUsers = sysUserDao.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers;
        }
        return null;
    }

    @Override
    public Long querySysUserCount(SysUserVo sysUserVo) {
        SysUserExample sysUserExample = toSysUserExample(sysUserVo);
        return sysUserDao.countByExample(sysUserExample);
    }

    @Override
    public Integer deleteSysUser(SysUser sysUser) {
        sysUser.setIsDelete(1);
        return sysUserDao.updateByPrimaryKeySelective(sysUser);
    }

    private SysUserExample toSysUserExample(SysUserVo sysUserVo) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setOrderByClause("create_time desc");
        PageHelper.startPage(sysUserVo.getPageNum(), sysUserVo.getPageSize());
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        if (StringUtils.isNotEmpty(sysUserVo.getId())) {
            criteria.andIdEqualTo(sysUserVo.getId());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getEmail())) {
            criteria.andEmailEqualTo(sysUserVo.getEmail());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getUsername())) {
            criteria.andUsernameEqualTo(sysUserVo.getUsername());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getPassword())) {
            criteria.andPasswordEqualTo(MD5Utils.md5(sysUserVo.getPassword()));
        }
        if (StringUtils.isNotEmpty(sysUserVo.getPhone())) {
            criteria.andPhoneEqualTo(sysUserVo.getPhone());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getIdcard())) {
            criteria.andPhoneEqualTo(sysUserVo.getId());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getSex())) {
            criteria.andPhoneEqualTo(sysUserVo.getSex());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getPhone())) {
            criteria.andPhoneEqualTo(sysUserVo.getPhone());
        }
        if (sysUserVo.getIsDelete() != null) {
            criteria.andIsDeleteEqualTo(sysUserVo.getIsDelete());
        }
        if (sysUserVo.getStatus() != null) {
            criteria.andStatusEqualTo(sysUserVo.getStatus());
        }
        return sysUserExample;
    }
}
