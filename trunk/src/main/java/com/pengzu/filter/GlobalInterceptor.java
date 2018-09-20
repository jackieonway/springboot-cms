
package com.pengzu.filter;

import com.pengzu.entity.PengzuConfig;
import com.pengzu.entity.vo.BlogRollVo;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.service.BlogRollService;
import com.pengzu.service.FolderService;
import com.pengzu.service.PengzuConfigService;
import com.pengzu.utils.HttpUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private PengzuConfigService pengzuConfigService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private BlogRollService blogrollService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (null == modelAndView) {
            return;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (pengzuConfigService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            pengzuConfigService = (PengzuConfigService) factory.getBean("pengzuConfigService");
        }
        if (folderService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            folderService = (FolderService) factory.getBean("folderService");
        }

        if (blogrollService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            blogrollService = (BlogRollService) factory.getBean("blogrollService");
        }
        // 系统配置参数
        String basePath = HttpUtils.getBasePathNoPort(request);
        if (request.getRequestURI().contains("/adminManager")) {
            PengzuConfig config = new PengzuConfig();
            config.setConfigType(1);
            List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(config);
            for (PengzuConfig pengzuConfig : configs) {
                modelAndView.addObject(pengzuConfig.getKey(), pengzuConfig.getValue());
            }
            modelAndView.addObject("BASE_PATH", basePath);
        } else if (request.getRequestURI().contains("/error")) {
            PengzuConfig config = new PengzuConfig();
            config.setConfigType(0);
            List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(config);
            for (PengzuConfig pengzuConfig : configs) {
                modelAndView.addObject(pengzuConfig.getKey(), pengzuConfig.getValue());
            }
            modelAndView.addObject("V_PATH", basePath + "/skin");
            modelAndView.addObject("VIEW_PATH", basePath);
            Integer status = response.getStatus();
            if (status == 404) {
                response.sendRedirect(basePath + "/404.html");
            } else if (status == 500) {
                response.sendRedirect(basePath + "/500.html");
            } else if (status == 403) {
                response.sendRedirect(basePath + "/403.html");
            }
        } else {
            PengzuConfig config = new PengzuConfig();
            config.setConfigType(0);
            List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(config);
            for (PengzuConfig pengzuConfig : configs) {
                modelAndView.addObject(pengzuConfig.getKey(), pengzuConfig.getValue());
            }
            modelAndView.addObject("V_PATH", basePath + "/skin");
            modelAndView.addObject("VIEW_PATH", basePath);
            BlogRollVo blogRollVo = new BlogRollVo();
            blogRollVo.setPageNum(1);
            blogRollVo.setPageSize(10);
            modelAndView.addObject("blogrolls", blogrollService.queryBlogrolls(blogRollVo));
            FolderVo folder = new FolderVo();
            folder.setLevel(1);
            folder.setStatus(0);
            folder.setIsDelete(0);
            List<FolderVo> folders = folderService.queryFolders(folder);
            modelAndView.addObject("menus", folders);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
