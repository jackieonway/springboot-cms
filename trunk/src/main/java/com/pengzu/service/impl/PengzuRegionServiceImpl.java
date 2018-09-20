package com.pengzu.service.impl;

import com.pengzu.dao.PengzuRegionDao;
import com.pengzu.entity.PengzuRegion;
import com.pengzu.entity.PengzuRegionExample;
import com.pengzu.service.PengzuRegionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pengzuRegionService")
public class PengzuRegionServiceImpl implements PengzuRegionService {

    private static Logger logger = Logger.getLogger(PengzuRegionServiceImpl.class);

    @Autowired
    private PengzuRegionDao pengzuRegionDao;

    @Override
    public List<PengzuRegion> queryRegionByParentCode(String parentCode) {
        try {
            PengzuRegionExample pengzuRegionExample = new PengzuRegionExample();
            PengzuRegionExample.Criteria criteria = pengzuRegionExample.createCriteria();
            criteria.andParentCodeEqualTo(parentCode);
            return pengzuRegionDao.selectByExample(pengzuRegionExample);
        } catch (Exception e) {
            logger.error("查询下一级地区失败", e);
            return null;
        }
    }

    @Override
    public PengzuRegion queryRegionByCode(String code) {
        try {
            PengzuRegionExample pengzuRegionExample = new PengzuRegionExample();
            PengzuRegionExample.Criteria criteria = pengzuRegionExample.createCriteria();
            criteria.andCodeEqualTo(code);
            List<PengzuRegion> pengzuRegions = pengzuRegionDao.selectByExample(pengzuRegionExample);
            if (pengzuRegions != null || pengzuRegions.size() > 0) {
                return pengzuRegions.get(0);
            }
            logger.error("查询地区信息失败 code [" + code + "]");
            return null;
        } catch (Exception e) {
            logger.error("查询地区信息失败 code [" + code + "]");
            return null;
        }
    }
}
