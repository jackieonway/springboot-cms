package com.pengzu.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pengzu.entity.Folder;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.FolderVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "adminManager/folder")
public class FolderManageController extends BaseController {

    @RequestMapping(value = "list.html")
    @RequiresPermissions("F02")
    public String addFolder(ModelMap modelMap, Long id, String type) {
        try {
            FolderVo folderVo = new FolderVo();
            /**
             * 查询一级目录
             */
            folderVo.setLevel(1);
            folderVo.setPageNum(1);
            folderVo.setPageSize(100);
            List<FolderVo> folders = folderService.queryFolders(folderVo);
            FolderVo folderVo1 = new FolderVo();
            folderVo1.setPageNum(1);
            folderVo1.setPageSize(100);
            if (id != null) {
                folderVo1.setFatherId(id);
                modelMap.put("parentId", id);
            } else {
                folderVo1.setFatherId(0L);
            }
            if (type != null && !"".equals(type)) {
                if ("update".equals(type)) {
                    FolderVo folderVo2 = new FolderVo();
                    folderVo2.setId(id);
                    Folder folder = folderService.queryFolder(folderVo2);
                    if (folder.getFatherId() != 0) {
                        modelMap.put("folderId", folder.getFatherId());
                    }
                    modelMap.put("updateFolder", folder);
                }
            }
            List<FolderVo> folders1 = folderService.queryFolders(folderVo1);
            modelMap.put("folders", folders);
            modelMap.put("folderAll", folders1);
            modelMap.put("id", id);
            return "manage/folder/list";
        } catch (Exception e) {
            return "redirect: /404.html";
        }
    }

    @RequestMapping(value = "update.html")
    @RequiresPermissions("F03")
    public String updateFolder() {
        return "manage/folder/update";
    }

    @ResponseBody
    @RequestMapping(value = "add")
    @RequiresPermissions("F04")
    public Response<FolderVo> addFolderJson(FolderVo folderVo) {
        try {
            if (StringUtils.isEmpty(folderVo.getEname())) {
                return new Response<>(false, "目录英文名不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getName())) {
                return new Response<>(false, "目录名不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getDescription())) {
                return new Response<>(false, "目录描述不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getKeywords())) {
                return new Response<>(false, "目录关键字不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getTitle())) {
                return new Response<>(false, "目录标题不能为空", "500");
            }
            folderVo.setStatus(0); //没有审核，暂时默认为已审核
            Folder folder = folderService.addFolder(folderVo);
            if (folder != null) {
                return new Response<FolderVo>(true, "保存成功", folderVo, "200");
            }
            return new Response<>(false, "保存失败", "500");
        } catch (Exception e) {
            return new Response<>(false, "未知错误", "500");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update")
    @RequiresPermissions("F05")
    public Response<FolderVo> updateFolderJson(FolderVo folderVo) {
        try {
            if (StringUtils.isEmpty(folderVo.getEname())) {
                return new Response<>(false, "目录英文名不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getName())) {
                return new Response<>(false, "目录名不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getDescription())) {
                return new Response<>(false, "目录描述不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getKeywords())) {
                return new Response<>(false, "目录关键字不能为空", "500");
            }
            if (StringUtils.isEmpty(folderVo.getTitle())) {
                return new Response<>(false, "目录标题不能为空", "500");
            }
            Folder folder = folderService.updateFolder(folderVo);
            if (folder != null) {
                return new Response<FolderVo>(true, "保存成功", folderVo, "200");
            }
            return new Response<>(false, "保存失败", "500");
        } catch (Exception e) {
            return new Response<>(false, "未知错误", "500");
        }

    }

    @ResponseBody
    @RequestMapping(value = "delete")
    @RequiresPermissions("F07")
    public Response<FolderVo> deleteFolderJson(FolderVo folderVo) {
        try {
            FolderVo folderVo1 = new FolderVo();
            folderVo1.setFatherId(folderVo.getId());
            List<FolderVo> folders = folderService.queryFolders(folderVo1);
            if (folders != null && folders.size() > 0) {
                return new Response<>(false, "目录删除失败,原因：该目录下存在子目录或文章", "500");
            }
            folderService.deleteFolder(folderVo);
            return new Response<>(true, "删除成功", null, "200");
        } catch (Exception e) {
            return new Response<>(false, "未知错误", "500");
        }
    }


    @ResponseBody
    @RequestMapping(value = "queryFolder")
    @RequiresPermissions("F06")
    public Response<List<FolderVo>> queryFolder(Long id) {
        try {
            if (id == null) {
                return new Response<>(false, "id不能为空", "500");
            }
            FolderVo folderVo = new FolderVo();
            folderVo.setFatherId(id);
            folderVo.setStatus(0);
            folderVo.setPageSize(100);
            List<FolderVo> folders = folderService.queryFolders(folderVo);
            return new Response<>(true, "查询成功", folders, "200");
        } catch (Exception e) {
            return new Response<>(false, "未知错误", "500");
        }
    }

    /**
     * @author 目录排序
     */
    @ResponseBody
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    @RequiresPermissions("F08")
    public Response<String> sort(
            @RequestParam(value = "sort") String sortJson) {
        try {
            JSONArray array = JSONArray.parseArray(sortJson);
            for (int i = 0; i < array.size(); i++) {
                JSONObject folder = array.getJSONObject(i);
                String folderId = folder.get("folderId").toString();
                String sort = folder.get("sort").toString();
                FolderVo folderVo = new FolderVo();
                folderVo.setId(Long.parseLong(folderId));
                folderVo.setSort(Integer.parseInt(sort));
                folderService.updateFolder(folderVo);
            }
            return new Response<>(true, "更新排序成功", null, "200");
        } catch (Exception e) {
            return new Response<>(false, "更新失败", "500");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateFolderStatus")
    @RequiresPermissions("F09")
    public Response updateFolderStatus(FolderVo folderVo) {
        try {
            Integer result =  folderService.updateFolderStatus(folderVo);
            if (result != null && result > 0){
                return new Response<>(true, "更新成功", null, "200");
            }else {
                return new Response<>(false, "更新失败", "500");
            }
        } catch (Exception e) {
            return new Response<>(false, "更新失败", "500");
        }
    }
}
