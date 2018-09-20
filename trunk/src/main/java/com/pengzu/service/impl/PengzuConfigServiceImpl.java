package com.pengzu.service.impl;

import com.pengzu.dao.PengzuConfigDao;
import com.pengzu.entity.PengzuConfig;
import com.pengzu.entity.PengzuConfigExample;
import com.pengzu.service.PengzuConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("pengzuConfigService")
public class PengzuConfigServiceImpl implements PengzuConfigService {

    private static Logger logger = Logger.getLogger(PengzuConfigServiceImpl.class);

    @Autowired
    private PengzuConfigDao pengzuConfigDao;

    @Override
    public Integer addPengzuConfig(PengzuConfig pengzuConfig) {
        try {
            Date now = new Date();
            pengzuConfig.setGmtCreate(now);
            pengzuConfig.setGmtModify(now);
            return pengzuConfigDao.insertSelective(pengzuConfig);
        } catch (Exception e) {
            logger.error("新增配置信息失败", e);
            return 0;
        }
    }

    @Override
    public PengzuConfig queryPengzuConfig(PengzuConfig pengzuConfig) {
        try {
            return pengzuConfigDao.selectByPrimaryKey(pengzuConfig.getKey());
        } catch (Exception e) {
            logger.error("查询配置信息失败", e);
            return null;
        }
    }

    @Override
    public Integer deletePengzuConfig(String key) {
        try {
            return pengzuConfigDao.deleteByPrimaryKey(key);
        } catch (Exception e) {
            logger.error("删除配置信息失败", e);
            return 0;
        }
    }

    @Override
    public Integer updatePengzuConfig(PengzuConfig pengzuConfig) {
        try {
            pengzuConfig.setGmtModify(new Date());
            return pengzuConfigDao.updateByPrimaryKeySelective(pengzuConfig);
        } catch (Exception e) {
            logger.error("更新配置信息失败", e);
            return 0;
        }
    }

    @Override
    public List<PengzuConfig> queryPengzuConfigs(PengzuConfig pengzuConfig) {
        try {
            PengzuConfigExample pengzuConfigExample = new PengzuConfigExample();
            pengzuConfigExample.setOrderByClause("gmt_create desc ");
            PengzuConfigExample.Criteria criteria = pengzuConfigExample.createCriteria();
            return pengzuConfigDao.selectByExample(pengzuConfigExample);
        } catch (Exception e) {
            logger.error("查询所有配置信息失败", e);
            return null;
        }
    }

    @Override
    public Long queryPengzuConfigsCount(PengzuConfig pengzuConfig) {
        try {
            PengzuConfigExample pengzuConfigExample = new PengzuConfigExample();
            return pengzuConfigDao.countByExample(pengzuConfigExample);
        } catch (Exception e) {
            logger.error("查询所有配置信息总数失败", e);
            return null;
        }
    }
}
