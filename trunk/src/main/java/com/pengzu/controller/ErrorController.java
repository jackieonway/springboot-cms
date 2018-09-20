package com.pengzu.controller;

import com.pengzu.entity.Folder;
import com.pengzu.entity.PengzuConfig;
import com.pengzu.service.PengzuConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/1/1.
 */
@Controller
public class ErrorController {

    @Autowired
    private PengzuConfigService pengzuConfigService;

    @RequestMapping("/403.html")
    public String Page403(ModelMap modelMap) {
        Folder f = new Folder();
        f.setTitle("403 未授权");
        f.setDescription("没有赋予权限");
        f.setKeywords("未授权");
        modelMap.put("folder",f);
        return "403";
    }

    @RequestMapping("/404.html")
    public String Page404(ModelMap modelMap) {
        Folder f = new Folder();
        f.setTitle("404 资源找不到");
        f.setDescription("资源找不到");
        f.setKeywords("资源找不到");
        modelMap.put("folder",f);
        return "404";
    }

    @RequestMapping("/500.html")
    public String Page500(ModelMap modelMap) {
        Folder f = new Folder();
        f.setTitle("500 服务器内部错误");
        f.setDescription("服务器内部错误");
        f.setKeywords("服务器内部错误");
        modelMap.put("folder",f);
        return "500";
    }


}
