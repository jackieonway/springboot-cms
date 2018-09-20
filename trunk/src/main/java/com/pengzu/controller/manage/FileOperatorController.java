package com.pengzu.controller.manage;

import com.pengzu.entity.result.Response;
import com.pengzu.utils.HttpUtils;
import com.pengzu.utils.MediaUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("adminManager/fileOperator")
public class FileOperatorController extends BaseController {

    @ResponseBody
    @RequiresPermissions("C05")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Response<Map<String, String>> upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            String path = MediaUtils.save(file, applicationConfig.getFileRoot());
            Map<String, String> map = new HashMap<String, String>(6);
            map.put("fileName", file.getOriginalFilename());
            map.put("path", path);
            String basePath = HttpUtils.getBasePathNoPort(request);
            map.put("url", basePath + path);
            return new Response<Map<String, String>>(true, "成功", map, "200");
        } catch (Exception e) {
            return new Response<Map<String, String>>(true, "上传失败", "200");
        }
    }
}
