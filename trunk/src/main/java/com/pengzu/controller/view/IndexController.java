package com.pengzu.controller.view;

import com.pengzu.controller.BaseController;
import com.pengzu.entity.Folder;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.entity.vo.HeadlineVo;
import com.pengzu.utils.BeanConvertUtils;
import com.pengzu.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = {"/", "/sy"})
    public String home(ModelMap modelMap) {
        return index(modelMap);
    }

    @RequestMapping(value = {"index.htm", "index.html"})
    public String index(ModelMap modelMap) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setFolderId(1L);
        //已发布的
        articleVo.setIsPublish(1);
        articleVo.setCheck(0);
        articleVo.setStatus(0);
        HeadlineVo headlineVo = new HeadlineVo();
        //已发布的
        headlineVo.setStatus(1);
        FolderVo folderVo = new FolderVo();
        folderVo.setId(1L);
        Folder folder = folderService.queryFolder(folderVo);
        modelMap.put("folder", folder);
        modelMap.put("headlines", headlineService.queryHeadlines(headlineVo));
        List<ArticleVo> articleVos = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,null), ArticleVo.class);
        for (ArticleVo articleVo1 : articleVos) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/").append(folder.getEname());
            if (folderVo.getFolders() != null && folderVo.getFolders().size() > 0) {
                for (FolderVo folderVo1 : folderVo.getFolders()) {
                    if (folderVo1.getId().equals(articleVo1.getFolderId())) {
                        stringBuilder.append("/").append(folderVo1.getEname());
                    }
                }
            }
            stringBuilder.append("/").append(articleVo1.getId()).append(".html");
            articleVo1.setUrl(stringBuilder.toString());
            articleVo1.setMonthDay(DateUtils.formatPattern(articleVo1.getGmtArticle(), "MM/dd"));
            articleVo1.setYear(DateUtils.formatPattern(articleVo1.getGmtArticle(), "yyyy"));
            articleVo1.setArticleTime(DateUtils.format(articleVo1.getGmtArticle()));
            articleVo1.setViewCount(articleVo1.getViewCount() + 1);
            articleService.updateViewCountById(articleVo1);
        }
        modelMap.put("articles", articleVos);
        return "view/index";
    }
}
