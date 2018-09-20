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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminManager/sysRole")
public class SysRoleController extends BaseController {

    private static final String[] ROLE_CODE = {"Q", "M", "G"};

    @RequestMapping("/list.html")
    @RequiresPermissions("R02")
    public String list(ModelMap modelMap, SysRoleVo sysRoleVo) {
        modelMap.put("total", sysRoleService.querySysRoleCount(sysRoleVo));
        modelMap.put("pageNum", sysRoleVo.getPageNum());
        modelMap.put("pageSize", sysRoleVo.getPageSize());
        modelMap.put("roles", sysRoleService.querySysRoles(sysRoleVo));
        return "manage/role/list";
    }

    @RequestMapping("/add.html")
    @RequiresPermissions("R03")
    public String addRole() {
        return "manage/role/add";
    }

    @RequestMapping("/update.html")
    @RequiresPermissions("R04")
    public String updateRole(ModelMap modelMap, SysRoleVo sysRoleVo) {
        SysRole sysRole = sysRoleService.querySysRole(sysRoleVo);
        logger.info(BeanConvertUtils.convert(sysRole, SysRoleVo.class));
        modelMap.put("role", sysRole);
        return "manage/role/update";
    }

    @ResponseBody
    @RequestMapping("/add")
    @RequiresPermissions("R05")
    public Response<SysRoleVo> add(SysRoleVo sysRoleVo) {
        for (String s : ROLE_CODE) {
            if (sysRoleVo.getRoleCode().contains(s)) {
                SysRole sysRole = sysRoleService.addSysRole(sysRoleVo);
                if (sysRole == null) {
                    return new Response<>(false,
                            CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
                } else {
                    return new Response<>(true,
                            CodeEnum.ADD_ROLE_FAIL.getValue(), CodeEnum.SUCCESS_CODE.getValue());
                }
            }
        }
        return new Response<>(false,
                CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("R06")
    public Response<SysRoleVo> update(SysRoleVo sysRoleVo) {
        for (String s : ROLE_CODE) {
            if (sysRoleVo.getRoleCode().contains(s)) {
                SysRole sysRole = sysRoleService.updateSysRole(sysRoleVo);
                if (sysRole == null) {
                    return new Response<>(false,
                            CodeEnum.UPDATE_ROLE_FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
                } else {
                    return new Response<>(true,
                            CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
                }
            }
        }
        return new Response<>(false,
                CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("R07")
    public Response<SysRoleVo> delete(SysRoleVo sysRoleVo) {
        Integer integer = sysRoleService.deleteSysRole(sysRoleVo);
        if (integer != null && integer > 0) {
            return new Response<>(true,
                    CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS_CODE.getValue());
        }
        return new Response<>(false,
                CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
    }

    /**
     * 角色权限列表页
     *
     * @param modelMap 返回model
     * @param id       角色Id
     * @param parentId 父级权限id
     * @param pName    父级权限名称
     * @return 页面
     */
    @GetMapping("permission.html")
    @RequiresPermissions("R08")
    public String addPermision(ModelMap modelMap, String id, String parentId, String pName) {
        if (StringUtils.isNotEmpty(id)) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setRid(id);
            sysRolePermission.setPageNum(1);
            sysRolePermission.setPageSize(1000);
            List<SysRolePermission> sysRolePermissions = null;
            List<SysPermission> sysPermissions = null;
            //查询系统角色已赋予的权限信息
            sysRolePermissions = sysRolePermissionService.querySysRolePermissions(sysRolePermission);
            SysPermissionVo sysPermissionVo = new SysPermissionVo();
            sysPermissionVo.setPageNum(1);
            sysPermissionVo.setPageSize(1000);
            if (StringUtils.isNotEmpty(parentId)) {
                sysPermissionVo.setParentId(parentId);
                sysPermissions = sysPermissionService.querySysPermissions(sysPermissionVo);
            } else {
                sysPermissionVo.setParentId("0");
                sysPermissions = sysPermissionService.querySysPermissions(sysPermissionVo);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
                for (SysRolePermission sysRolePermission1 : sysRolePermissions) {
                    stringBuilder.append(sysRolePermission1.getPid());
                    stringBuilder.append(";");
                }
            }
            modelMap.put("rolePermissions", stringBuilder);
            modelMap.put("permissions", sysPermissions);
            modelMap.put("id", id);
            modelMap.put("parentId", parentId);
            modelMap.put("pName", pName);
        }
        return "manage/role/permission";
    }

    @ResponseBody
    @RequestMapping("/addPermission")
    @RequiresPermissions("R09")
    public Response<SysRoleVo> addPermission(SysRolePermission sysRolePermission) {
        SysPermissionVo sysPermissionVo = new SysPermissionVo();
        sysPermissionVo.setId(sysRolePermission.getPid());
        SysPermission sysPermission = sysPermissionService.querySysPermission(sysPermissionVo);
        if (!"0".equals(sysPermission.getParentId())) {
            SysRolePermission sysRolePermission1 = new SysRolePermission();
            sysRolePermission1.setRid(sysRolePermission.getRid());
            sysRolePermission1.setPid(sysPermission.getParentId());
            sysRolePermission1 = sysRolePermissionService.querySysRolePermission(sysRolePermission1);
            if (sysRolePermission1 == null) {
                return new Response<>(false,
                        "未赋予父级权限,请先赋予父级权限", CodeEnum.FAIL_CODE.getValue());
            }
        }
        sysRolePermission = sysRolePermissionService.addSysRolePermission(sysRolePermission);
        if (sysRolePermission != null) {
            return new Response<>(true,
                    CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
        }
        return new Response<>(false,
                CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("/delPermission")
    @RequiresPermissions("R10")
    public Response<SysRoleVo> deleltePermission(SysRolePermission sysRolePermission) {
        checkPermissionLevel(sysRolePermission);
        return new Response<>(true,
                CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    /**
     * 检查角色对应的权限的级数,并做删除处理
     *
     * @param sysRolePermission 角色权限信息
     */
    private void checkPermissionLevel(SysRolePermission sysRolePermission) {
        SysPermissionVo sysPermissionVo = new SysPermissionVo();
        sysPermissionVo.setParentId(sysRolePermission.getPid());
        List<SysPermission> sysPermissions = sysPermissionService.querySysPermissions(sysPermissionVo);
        //如果为NULL , 表示是最后一级权限, 否则不是最后一级权限
        if (sysPermissions == null || sysPermissions.size() == 0) {
            sysRolePermissionService.deleteSysRolePermission(sysRolePermission);
        } else {
            for (SysPermission sysPermission : sysPermissions) {
                SysRolePermission sysRolePermission1 = new SysRolePermission();
                sysRolePermission1.setRid(sysRolePermission.getRid());
                sysRolePermission1.setPid(sysPermission.getId());
                checkPermissionLevel(sysRolePermission1);
                sysRolePermissionService.deleteSysRolePermission(sysRolePermission);
            }
        }
    }
}
