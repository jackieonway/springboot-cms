package com.pengzu.controller;

import com.pengzu.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/12/24.
 */
public class BaseController {

    @Autowired
    protected SysUserService sysUserService;

    @Autowired
    protected SysUserRoleService sysUserRoleService;

    @Autowired
    protected SysRolePermissionService sysRolePermissionService;

    @Autowired
    protected SysPermissionService sysPermissionService;

    @Autowired
    protected SysRoleService sysRoleService;

    @Autowired
    protected PengzuRegionService pengzuRegionService;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected FolderService folderService;

    @Autowired
    protected HeadlineService headlineService;

    @Autowired
    protected PengzuConfigService pengzuConfigService;

    protected static Logger logger = Logger.getLogger(BaseController.class);

}
