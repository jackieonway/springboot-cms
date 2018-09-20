package com.pengzu.controller.manage;

import com.pengzu.entity.Article;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.utils.BeanConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "adminManager/article")
public class ArticleManageController extends BaseController {

    @RequestMapping(value = "/add.html")
    @RequiresPermissions("A05")
    public String addArticlePage(ModelMap modelMap) {
        return "manage/article/add";
    }

    @RequestMapping(value = "/update.html")
    @RequiresPermissions("A03")
    public String updateArticlePage(ModelMap modelMap, ArticleVo articleVo) {
        SysUser sysUser = getSessionUser();
        articleVo.setUserId(sysUser.getId());
        Article article = articleService.queryArticle(articleVo);
        articleVo = BeanConvertUtils.convert(article, ArticleVo.class);
        modelMap.put("article", articleVo);
        return "manage/article/update";
    }

    @ResponseBody
    @RequiresPermissions("A06")
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public Response<ArticleVo> addArticle(ArticleVo articleVo) {
        Response<ArticleVo> response = null;
        try {
            if (articleVo.getFatherFolderId() == null) {
                return new Response<>(false, "请选择文章所属目录", "500");
            }
            if (articleVo.getFolderId() == null) {
                articleVo.setFolderId(articleVo.getFatherFolderId());
                articleVo.setFatherFolderId(0L);
            }
            Article article = articleService.addArticle(articleVo);
            if (article != null) {
                articleVo = BeanConvertUtils.convert(article, ArticleVo.class);
                response = new Response<ArticleVo>(true, "新增文章成功", articleVo, "200");
            } else {
                response = new Response<>(false, "新增文章失败", "500");
            }
        } catch (Exception e) {
            logger.info("新增文章失败", e);
            response = new Response<>(false, "新增文章失败", "500");
        }
        return response;
    }

    @ResponseBody
    @RequiresPermissions("A04")
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public Response<ArticleVo> updateArticle(ArticleVo articleVo) {
        Response<ArticleVo> response = null;
        try {
            Article article = articleService.updateArticle(articleVo);
            if (article != null) {
                articleVo = BeanConvertUtils.convert(article, ArticleVo.class);
                response = new Response<ArticleVo>(true, "更新文章成功", articleVo, "200");
            } else {
                response = new Response<>(false, "更新文章失败", "500");
            }
        } catch (Exception e) {
            logger.info("更新文章失败", e);
            response = new Response<>(false, "更新文章失败", "500");
        }
        return response;
    }

    @ResponseBody
    @RequiresPermissions("A07")
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public Response deleteArticle(ArticleVo articleVo) {
        Response response = null;
        try {
            Integer count = articleService.deleteArticle(articleVo);
            if (count == null || count != 1) {
                response = new Response<>(false, "删除文章失败", "500");
            } else {
                response = new Response<>(true, "更新文章成功", count, "200");
            }
        } catch (Exception e) {
            logger.info("删除文章失败...", e);
            response = new Response<>(false, "删除文章失败", "500");
        }
        return response;
    }

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    @RequiresPermissions("A02")
    public String queryArticles(ModelMap map, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
            , @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize
            , ArticleVo articleVo) {
        try {
            articleVo.setPageNum(pageNum);
            articleVo.setPageSize(pageSize);
            Long folderId = articleVo.getFolderId();
            List<Article> articles = null;
            Long total = null;
            if (folderId != null && folderId >= 0) { //有目录Id
                FolderVo folderVo = new FolderVo();
                folderVo.setFatherId(folderId);
                List<FolderVo> folders = folderService.queryFolders(folderVo);
                if (folders != null && folders.size() > 0) { //如果有下级目录，则是一级目录
                    ArticleVo articleVo1 = new ArticleVo();
                    articleVo1.setPageNum(1);
                    articleVo1.setPageSize(100);
                    articleVo1.setFatherFolderId(folderVo.getId());
                    articles = articleService.queryArticles(articleVo1,null);
                    total = articleService.queryArticleCount(articleVo1,null);
                } else { //如果没有下级目录，则是二级目录或者只有一级目录的目录
                    articles = articleService.queryArticles(articleVo,null);
                    total = articleService.queryArticleCount(articleVo,null);
                }

            } else { //查询所有文章
                articles = articleService.queryArticles(articleVo,null);
                total = articleService.queryArticleCount(articleVo,null);
            }
            List<ArticleVo> articleVos = BeanConvertUtils.convertList(articles, ArticleVo.class);
            map.put("articles", articleVos);
            map.put("total", total);
            map.put("folderId", folderId);
            map.put("pageSize", articleVo.getPageSize());
            map.put("pageNum", articleVo.getPageNum());
        } catch (Exception e) {
            logger.info("查询文章失败...", e);
        }
        return "manage/article/list";
    }

    @ResponseBody
    @RequiresPermissions("A08")
    @RequestMapping(value = "/updateArticleCheckStatus", method = RequestMethod.POST)
    public Response updateArticleCheckStatus(ArticleVo articleVo) {
        Response response = null;
        try {
            Article article = new Article();
            article.setCheck(articleVo.getCheck());
            article.setId(articleVo.getId());
            Integer count = articleService.updateCheckStatus(article);
            if (count == null || count != 1) {
                response = new Response<>(false, "更新文章审核状态失败", "500");
            } else {
                response = new Response<>(true, "更新文章审核状态成功", count, "200");
            }
        } catch (Exception e) {
            logger.info("更新文章审核状态失败...", e);
            response = new Response<>(false, "更新文章审核状态失败", "500");
        }
        return response;
    }

    @ResponseBody
    @RequiresPermissions("A09")
    @RequestMapping(value = "/updateArticleStatus", method = RequestMethod.POST)
    public Response updateArticleStatus(ArticleVo articleVo) {
        Response response = null;
        try {
            Article article = new Article();
            article.setStatus(articleVo.getStatus());
            article.setId(articleVo.getId());
            Integer count = articleService.updateStatus(article);
            if (count == null || count != 1) {
                response = new Response<>(false, "更新文章状态失败", "500");
            } else {
                response = new Response<>(true, "更新文章状态成功", count, "200");
            }
        } catch (Exception e) {
            logger.info("更新文章状态失败...", e);
            response = new Response<>(false, "更新文章状态失败", "500");
        }
        return response;
    }
}
