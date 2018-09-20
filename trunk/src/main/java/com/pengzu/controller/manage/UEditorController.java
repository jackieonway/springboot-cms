package com.pengzu.controller.manage;

import com.baidu.ueditor.ActionEnter;
import com.pengzu.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/manage/ueditor")
public class UEditorController {

    @Autowired
    private ApplicationConfig applicationConfig;

    @ResponseBody
    @RequestMapping(value = "/ueditor.html")
    public String config(@RequestParam(value = "action") String action,
                         HttpServletResponse response, HttpServletRequest request) {
        String root = applicationConfig.getFileRoot()
                + java.io.File.separatorChar;
        return new ActionEnter(request, root).exec();
    }

    @RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath, applicationConfig.getFileRoot()).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
