package com.pengzu.service.impl;

import com.github.pagehelper.PageHelper;
import com.pengzu.dao.FolderDao;
import com.pengzu.entity.Folder;
import com.pengzu.entity.FolderExample;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.service.FolderService;
import com.pengzu.utils.BeanConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
@Service("folderService")
public class FolderServiceImpl implements FolderService {

    private static Logger logger = Logger.getLogger(FolderServiceImpl.class);

    @Autowired
    private FolderDao folderDao;

    @Override
    public Folder addFolder(FolderVo folder) {
        try {
            Date now = new Date();
            folder.setGmtCreate(now);
            if (folder.getFatherId() == 0) {
                folder.setLevel(1);
            } else {
                folder.setLevel(2);
            }
            Folder folder1 = BeanConvertUtils.convert(folder, Folder.class);
            if (folderDao.insertSelective(folder1) == 1) {
                return folder1;
            }
        } catch (Exception e) {
            logger.error("新增目录失败", e);
        }
        return null;
    }

    @Override
    public Folder updateFolder(FolderVo folder) {
        Folder folder1 = BeanConvertUtils.convert(folder, Folder.class);
        folder1.setGmtModify(new Date());
        if (folderDao.updateByPrimaryKeySelective(folder1) == 1) {
            return folder1;
        }
        return null;
    }

    @Override
    public Folder queryFolder(FolderVo folder) {
        return folderDao.selectByPrimaryKey(folder.getId());
    }

    @Override
    public List<FolderVo> queryFolders(FolderVo folder) {
        folder.setIsDelete(0);
        FolderExample folderExample = toFolderExample(folder, "sort asc");
        PageHelper.startPage(folder.getPageNum(), folder.getPageSize());
        List<Folder> folders = folderDao.selectByExample(folderExample);
        List<FolderVo> folderVos = BeanConvertUtils.convertList(folders, FolderVo.class);
        for (FolderVo folderVo : folderVos) {
            FolderVo folderVo1 = new FolderVo();
            folderVo1.setFatherId(folderVo.getId());
            folderVo1.setStatus(0);
            folderVo1.setIsDelete(0);
            folderExample = toFolderExample(folderVo1, "sort asc");
            PageHelper.startPage(1, 100);
            List<Folder> childFolders = folderDao.selectByExample(folderExample);
            folderVo.setFolders(BeanConvertUtils.convertList(childFolders, FolderVo.class));
        }
        return folderVos;
    }

    @Override
    public Long queryFolderCount(FolderVo folder) {
        folder.setIsDelete(0);
        FolderExample folderExample = toFolderExample(folder, null);
        return folderDao.countByExample(folderExample);
    }

    @Override
    public void deleteFolder(FolderVo folder) {
        folder.setIsDelete(1);
        folderDao.updateByPrimaryKeySelective(
                BeanConvertUtils.convert(folder, Folder.class));
    }

    @Override
    public FolderVo queryFolderByEname(FolderVo folderVo) {
        folderVo.setIsDelete(0);
        Folder folder = null;
        FolderExample folderExample = toFolderExample(folderVo, null);
        List<Folder> folders = folderDao.selectByExample(folderExample);
        if (folders != null && folders.size() > 0) {
            folder = folders.get(0);
            folderVo = new FolderVo();
            folderVo.setIsDelete(0);
            folderVo.setStatus(0);
            folderVo.setFatherId(folder.getId());
            folderExample = toFolderExample(folderVo, null);
            PageHelper.startPage(1, 100);
            folders = folderDao.selectByExample(folderExample);
            folderVo = BeanConvertUtils.convert(folder, FolderVo.class);
            folderVo.setFolders(BeanConvertUtils.convertList(folders, FolderVo.class));
            return folderVo;
        } else {
            return null;
        }
    }

    @Override
    public Integer updateFolderStatus(FolderVo folderVo) {
        Folder folder = new Folder();
        folder.setId(folderVo.getId());
        folder.setStatus(folderVo.getStatus());
        return folderDao.updateByPrimaryKeySelective(folder);
    }

    private FolderExample toFolderExample(FolderVo folderVo, String orderByCluse) {
        FolderExample example = new FolderExample();
        if (orderByCluse != null) {
            example.setOrderByClause(orderByCluse);
        }
        FolderExample.Criteria criteria = example.createCriteria();
        if (folderVo.getFatherId() != null) {
            criteria.andFatherIdEqualTo(folderVo.getFatherId());
        }
        if (folderVo.getId() != null) {
            criteria.andIdEqualTo(folderVo.getId());
        }
        if (folderVo.getEname() != null) {
            criteria.andEnameEqualTo(folderVo.getEname());
        }
        if (folderVo.getCheck() != null) {
            criteria.andCheckEqualTo(folderVo.getCheck());
        }
        if (folderVo.getIsDelete() != null) {
            criteria.andIsDeleteEqualTo(folderVo.getIsDelete());
        }
        if (folderVo.getLevel() != null) {
            criteria.andLevelEqualTo(folderVo.getLevel());
        }
        if (folderVo.getStatus() != null) {
            criteria.andStatusEqualTo(folderVo.getStatus());
        }
        return example;
    }
}

