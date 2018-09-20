package com.pengzu.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.pengzu.config.ApplicationConfig;
import com.pengzu.config.CodeEnum;
import com.pengzu.entity.Folder;
import com.pengzu.entity.PengzuConfig;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.BlogRollVo;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.service.*;
import com.pengzu.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2017/12/24.
 */
@ControllerAdvice
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
    protected PengzuConfigService pengzuConfigService;

    @Autowired
    protected FolderService folderService;

    @Autowired
    protected HeadlineService headlineService;

    @Autowired
    protected ApplicationConfig applicationConfig;

    @Autowired
    protected BlogRollService blogRollService;

    @Autowired
    protected JavaMailSender javaMailSender;

    @Autowired
    protected ThreadService threadService;

    protected static Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    public String defaultUnauthorizedExceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {
        if (req.getMethod().equalsIgnoreCase("post")) {
            responseOutWithJson(response, new Response<>(false, "没有操作权限,请通知管理员处理", CodeEnum.FAIL_CODE.getValue()).toString());
        }
        String basePath = HttpUtils.getBasePathNoPort(request);
        PengzuConfig config = new PengzuConfig();
        config.setConfigType(0);
        List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(config);
        for (PengzuConfig pengzuConfig1 : configs) {
            req.setAttribute(pengzuConfig1.getKey(), pengzuConfig1.getValue());
        }
        req.setAttribute("V_PATH", basePath + "/skin");
        req.setAttribute("VIEW_PATH", basePath);
        BlogRollVo blogRollVo = new BlogRollVo();
        blogRollVo.setPageNum(1);
        blogRollVo.setPageSize(10);
        req.setAttribute("blogrolls", blogRollService.queryBlogrolls(blogRollVo));
        Folder f = new Folder();
        f.setTitle("403");
        f.setDescription("没有赋予权限");
        f.setKeywords("未授权");
        req.setAttribute("folder", f);
        FolderVo folder = new FolderVo();
        folder.setLevel(1);
        folder.setStatus(0);
        folder.setIsDelete(0);
        List<FolderVo> folders = folderService.queryFolders(folder);
        req.setAttribute("menus", folders);
        return "403";
        //return new Response<>(false, "没有操作权限,请通知管理员处理",CodeEnum.FAIL_CODE.getValue());
    }

    /**
     * 针对POST返回json格式数据
     *
     * @param response
     * @param responseObject
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       String responseObject) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = JSONObject.parseObject(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
            logger.debug(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 获取登录用户信息
     * @return 用户信息
     */
    protected SysUser getSessionUser(){
       Object object = request.getSession().getAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue());
       if (object instanceof SysUser){
           return (SysUser)object;
       }
       return null;
    }
}
