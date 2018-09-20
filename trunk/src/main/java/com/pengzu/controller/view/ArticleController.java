package com.pengzu.controller.view;

import com.pengzu.controller.manage.BaseController;
import com.pengzu.entity.Article;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.utils.BeanConvertUtils;
import com.pengzu.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class ArticleController extends BaseController {

    @RequestMapping(value = "{path}/{id}.html", method = RequestMethod.GET)
    public String queryArtcile(@PathVariable("path") String path,
                               @PathVariable("id") Long id,
                               ModelMap modelMap) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(id);
        Article article = articleService.queryArticle(articleVo);
        articleVo.setViewCount(article.getViewCount() + 1);
        articleService.updateViewCountById(articleVo);
        articleVo = BeanConvertUtils.convert(article, ArticleVo.class);
        articleVo.setArticleTime(DateUtils.format(article.getGmtArticle()));
        String s = article.getKeywords().replaceAll("，", ",");
        String[] strings = s.split(",");
        List<String> list = Arrays.asList(strings);
        if (StringUtils.isNotEmpty(articleVo.getUserId()) && !"0".equals(articleVo.getUserId())){
            SysUserVo sysUserVo = new SysUserVo();
            sysUserVo.setId(articleVo.getUserId());
            SysUser sysUser = sysUserService.querySysUser(sysUserVo);
            articleVo.setUsername(sysUser.getNickName());
        }
        articleVo.setSearchs(list);
        modelMap.put("clazz", path);
        modelMap.put("article", articleVo);
        return "/view/article/article";
    }

    @RequestMapping(value = "/{path}/{path2}/{id}.html", method = RequestMethod.GET)
    public String querySecondArtcile(@PathVariable("path") String path,
                                     @PathVariable("path2") String sencondPath,
                                     @PathVariable("id") Long id,
                                     ModelMap modelMap) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(id);
        Article article = articleService.queryArticle(articleVo);
        articleVo.setViewCount(article.getViewCount() + 1);
        articleService.updateViewCountById(articleVo);
        articleVo = BeanConvertUtils.convert(article, ArticleVo.class);
        articleVo.setArticleTime(DateUtils.format(article.getGmtArticle()));
        String s = article.getKeywords().replaceAll("，", ",");
        String[] strings = s.split(",");
        List<String> list = Arrays.asList(strings);
        articleVo.setSearchs(list);
        if (StringUtils.isNotEmpty(articleVo.getUserId()) && !"0".equals(articleVo.getUserId())){
            SysUserVo sysUserVo = new SysUserVo();
            sysUserVo.setId(articleVo.getUserId());
            SysUser sysUser = sysUserService.querySysUser(sysUserVo);
            articleVo.setUsername(sysUser.getNickName());
        }
        modelMap.put("clazz", path);
        modelMap.put("second", sencondPath);
        modelMap.put("article", articleVo);
        return "/view/article/article";
    }
}
