package com.pengzu.service;

import com.pengzu.entity.Folder;
import com.pengzu.entity.vo.FolderVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface FolderService {

    Folder addFolder(FolderVo folderVo);

    Folder updateFolder(FolderVo folderVo);

    Folder queryFolder(FolderVo folderVo);

    List<FolderVo> queryFolders(FolderVo folderVo);

    Long queryFolderCount(FolderVo folderVo);

    void deleteFolder(FolderVo folderVo);

    FolderVo queryFolderByEname(FolderVo folderVo);

    Integer updateFolderStatus(FolderVo folderVo);
}
