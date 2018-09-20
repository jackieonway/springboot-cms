package com.pengzu.controller.manage;

import com.pengzu.config.CodeEnum;
import com.pengzu.entity.SysPermission;
import com.pengzu.entity.SysRole;
import com.pengzu.entity.SysRolePermission;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.SysPermissionVo;
import com.pengzu.entity.vo.SysRoleVo;
import com.pengzu.utils.BeanConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adminManager/sysPermission")
public class SysPermissionController extends BaseController {

    @RequestMapping("/list.html")
    @RequiresPermissions("P02")
    public String list(ModelMap modelMap, String pname, SysPermissionVo sysPermissionVo) {
        if (StringUtils.isEmpty(sysPermissionVo.getParentId())) {
            sysPermissionVo.setParentId("0");
        }
        modelMap.put("total", sysPermissionService.querySysPermissionCount(sysPermissionVo));
        modelMap.put("pageNum", sysPermissionVo.getPageNum());
        modelMap.put("pageSize", sysPermissionVo.getPageSize());
        if (StringUtils.isEmpty(sysPermissionVo.getParentId())) {
            sysPermissionVo.setParentId("0");
        }
        modelMap.put("permissions", sysPermissionService.querySysPermissions(sysPermissionVo));
        modelMap.put("parentId", sysPermissionVo.getParentId());
        modelMap.put("pname", pname);
        return "manage/permission/list";
    }

    @RequestMapping("/add.html")
    @RequiresPermissions("P03")
    public String addPermission(String parentId, String pname, ModelMap modelMap) {
        modelMap.put("parentId", parentId);
        modelMap.put("pname", pname);
        return "manage/permission/add";
    }

    @RequestMapping("/update.html")
    @RequiresPermissions("P04")
    public String updateRole(ModelMap modelMap, String pname, SysPermissionVo sysPermissionVo) {
        SysPermission sysPermission = sysPermissionService.querySysPermission(sysPermissionVo);
        logger.info(BeanConvertUtils.convert(sysPermissionVo, SysRoleVo.class));
        modelMap.put("permission", sysPermission);
        modelMap.put("pname", pname);
        return "manage/permission/update";
    }

    @ResponseBody
    @RequestMapping("/add")
    @RequiresPermissions("P05")
    public Response<SysRoleVo> add(SysPermissionVo sysPermissionVo) {
        SysPermission sysPermission = sysPermissionService.addSysPermission(sysPermissionVo);
        if (sysPermission == null) {
            return new Response<>(false,
                    CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        SysRoleVo sysRoleVo = new SysRoleVo();
        sysRoleVo.setType("0");
        SysRole sysRole = sysRoleService.querySysRole(sysRoleVo);
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRid(sysRole.getId());
        sysRolePermission.setPid(sysPermission.getId());
        sysRolePermissionService.addSysRolePermission(sysRolePermission);
        return new Response<>(true,
                CodeEnum.ADD_ROLE_FAIL.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("P06")
    public Response<SysRoleVo> update(SysPermissionVo sysPermissionVo) {
        SysPermission sysPermission = sysPermissionService.updateSysPermission(sysPermissionVo);
        if (sysPermission == null) {
            return new Response<>(false,
                    CodeEnum.UPDATE_ROLE_FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true,
                CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("P07")
    public Response<SysRoleVo> delete(SysPermissionVo sysPermissionVo) {
        Integer integer = sysPermissionService.deleteSysPermission(sysPermissionVo);
        if (integer != null && integer > 0) {
            return new Response<>(true,
                    CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
        }
        return new Response<>(false,
                CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
    }
}
