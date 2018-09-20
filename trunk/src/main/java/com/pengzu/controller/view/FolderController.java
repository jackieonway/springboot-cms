package com.pengzu.controller.view;

import com.pengzu.controller.BaseController;
import com.pengzu.entity.Folder;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.ArticleVo;
import com.pengzu.entity.vo.FolderVo;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.utils.BeanConvertUtils;
import com.pengzu.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FolderController extends BaseController {

    @RequestMapping(value = "/{clazz}")
    public String FirstFolder(@PathVariable("clazz") String clazz,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              ModelMap modelMap) {
        try {
            FolderVo folderVo = new FolderVo();
            folderVo.setEname(clazz);
            folderVo.setStatus(0);
            FolderVo folderVo1 = folderService.queryFolderByEname(folderVo);
            //被隐藏的目录返回404页面
            if (folderVo1 == null){
                return "forward:/404.html";
            }
            modelMap.put("clazz", clazz);
            modelMap.put("folder", folderVo1);
            //第一级目录的文章,没有第二级的目录
            if (folderVo1.getFolders() == null || folderVo1.getFolders().size() == 0) {
                firstFolderArticles(pageNum, pageSize, modelMap, folderVo1);
                return "view/folder/first-folder";
            }
            secondFolderArticles(pageNum, pageSize, modelMap, folderVo1);
            modelMap.put("secondFolders", folderVo1.getFolders());
            return "view/folder/second-folder";
        } catch (Exception e) {
            logger.info(e);
            return "404";
        }
    }

    @RequestMapping(value = "/{clazz}/{second}", method = RequestMethod.GET)
    public String secondFolder(@PathVariable("clazz") String clazz,
                               @PathVariable("second") String second,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               ModelMap modelMap) {
        try {
            FolderVo folderVo = new FolderVo();
            folderVo.setEname(clazz);
            folderVo.setStatus(0);
            folderVo = folderService.queryFolderByEname(folderVo);
            //被隐藏的目录返回404页面
            if (folderVo == null){
                return "forward:/404.html";
            }
            FolderVo folderVo1 = new FolderVo();
            folderVo1.setEname(second);
            folderVo1.setStatus(0);
            folderVo1 = folderService.queryFolderByEname(folderVo1);
            //被隐藏的目录返回404页面
            if (folderVo1 == null){
                return "forward:/404.html";
            }
            modelMap.put("clazz", clazz);
            modelMap.put("second", second);
            modelMap.put("folder", folderVo1);
            ArticleVo articleVo = new ArticleVo();
            articleVo.setFolderId(folderVo1.getId());
            articleVo.setPageSize(pageSize);
            articleVo.setPageNum(pageNum);
            articleVo.setCheck(0);
            articleVo.setStatus(0);
            articleVo.setIsPublish(1);
            List<ArticleVo> articleVos = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,null), ArticleVo.class);
            setSecondFolderArticlesUrlAndUsername(pageNum, pageSize, modelMap, folderVo, folderVo1, articleVo, articleVos);
            modelMap.put("secondFolders", folderVo.getFolders());
            return "view/folder/first-folder";
        } catch (Exception e) {
            logger.info(e);
            return "404";
        }
    }

    /**
     *   设置二级目录文章的地址和作者
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @param modelMap 返回Map集合
     * @param firstFolderVo 一级目录
     * @param secondFolderVo 二级目录
     * @param articleVo 查询文章实体
     * @param articleVos 文章集合
     */
    private void setSecondFolderArticlesUrlAndUsername(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HashMap modelMap, FolderVo firstFolderVo, FolderVo secondFolderVo,
            ArticleVo articleVo, List<ArticleVo> articleVos) {
        for (ArticleVo articleVo1 : articleVos) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/").append(firstFolderVo.getEname());
            stringBuilder.append("/").append(secondFolderVo.getEname());
            stringBuilder.append("/").append(articleVo1.getId()).append(".html");
            articleVo1.setUrl(stringBuilder.toString());
            articleVo1.setMonthDay(DateUtils.formatPattern(articleVo1.getGmtArticle(), "MM/dd"));
            articleVo1.setYear(DateUtils.formatPattern(articleVo1.getGmtArticle(), "yyyy"));
            articleVo1.setArticleTime(DateUtils.format(articleVo1.getGmtArticle()));
            articleVo1.setViewCount(articleVo1.getViewCount() + 1);
            articleService.updateViewCountById(articleVo1);
            if (StringUtils.isNotEmpty(articleVo1.getUserId()) && !"0".equals(articleVo1.getUserId())){
                SysUserVo sysUserVo = new SysUserVo();
                sysUserVo.setId(articleVo1.getUserId());
                SysUser sysUser = sysUserService.querySysUser(sysUserVo);
                articleVo1.setUsername(sysUser.getNickName());
            }
        }
        modelMap.put("articles", articleVos);
        Long total = articleService.queryArticleCount(articleVo,null);
        if (pageNum * pageSize >= total) {
            modelMap.put("pageNum", 0);
        } else {
            modelMap.put("pageNum", pageNum + 1);
        }
        modelMap.put("pageSize", pageSize);
    }

    @RequestMapping("/search.html")
    public String search(@RequestParam("search") String search,
                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                         ModelMap modelMap) {
        try {
            searchArticle(pageNum, search, pageSize, modelMap);
            modelMap.put("search", search);
            modelMap.put("pageSize", pageSize);
            return "view/search";
        } catch (Exception e) {
            logger.error("查询关键字失败", e);
            return "404";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/more", method = RequestMethod.POST)
    public Response folderJson(@RequestParam(value = "clazz") String clazz,
                               @RequestParam(value = "second") String second,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "search") String search,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("clazz", clazz);
            map.put("pageNum", pageNum + "");
            map.put("pageSize", pageSize + "");
            map.put("search", search);
            map.put("second", second);
            if (StringUtils.isNotEmpty(clazz) && StringUtils.isEmpty(second)) { // 一级目录存在,二级目录不存在
                FolderVo folderVo = new FolderVo();
                folderVo.setEname(clazz);
                folderVo = folderService.queryFolderByEname(folderVo);
                if (folderVo == null) {
                    return new Response(false, "失败", "500");
                }
                if (folderVo.getFolders() != null && folderVo.getFolders().size() > 0) {
                    secondFolderArticles(pageNum, pageSize, map, folderVo);
                } else {
                    firstFolderArticles(pageNum, pageSize, map, folderVo);
                }
                return new Response<>(true, "成功", map, "200");
            } else if (StringUtils.isNotEmpty(clazz) && StringUtils.isNotEmpty(second)) {  // 一级目录存在，二级目录存在的时候执行
                FolderVo folderVo = new FolderVo();
                folderVo.setEname(clazz);
                folderVo = folderService.queryFolderByEname(folderVo);
                if (folderVo == null) {
                    return new Response(false, "失败", "500");
                }
                FolderVo folderVo1 = new FolderVo();
                folderVo1.setEname(second);
                folderVo1 = folderService.queryFolderByEname(folderVo1);
                if (folderVo1 == null) {
                    return new Response(false, "失败", "500");
                }
                ArticleVo articleVo = new ArticleVo();
                articleVo.setPageNum(pageNum);
                articleVo.setPageSize(pageSize);
                articleVo.setFolderId(folderVo1.getId());
                articleVo.setCheck(0);
                articleVo.setStatus(0);
                //已发布的
                articleVo.setIsPublish(1);
                List<ArticleVo> articles = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,null), ArticleVo.class);
                setSecondFolderArticlesUrlAndUsername(pageNum,pageSize,map,folderVo,folderVo1,articleVo,articles);
                return new Response<>(true, "成功", map, "200");
            } else {  // 关键字搜索存在
                searchArticle(pageNum, search, pageSize, map);
                return new Response<>(true, "成功", map, "200");
            }
        } catch (Exception e) {
            return new Response<>(false, "失败", "500");
        }
    }

    /**
     * 一级目录下的文章
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @param map 返回Map集合
     * @param folderVo 一级目录
     */
    private void firstFolderArticles(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HashMap<String, Object> map, FolderVo folderVo) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setPageNum(pageNum);
        articleVo.setPageSize(pageSize);
        articleVo.setFolderId(folderVo.getId());
        articleVo.setCheck(0);
        articleVo.setStatus(0);
        //已发布的
        articleVo.setIsPublish(1);
        List<ArticleVo> articles = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,null), ArticleVo.class);
        for (ArticleVo articleVo1 : articles) {
            articleVo1.setMonthDay(DateUtils.formatPattern(articleVo1.getGmtArticle(), "MM/dd"));
            articleVo1.setYear(DateUtils.formatPattern(articleVo1.getGmtArticle(), "yyyy"));
            articleVo1.setArticleTime(DateUtils.format(articleVo1.getGmtArticle()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/").append(folderVo.getEname());
            stringBuilder.append("/").append(articleVo1.getId()).append(".html");
            articleVo1.setUrl(stringBuilder.toString());
            articleVo1.setViewCount(articleVo1.getViewCount() + 1);
            articleService.updateViewCountById(articleVo1);
            if (StringUtils.isNotEmpty(articleVo1.getUserId()) && !"0".equals(articleVo1.getUserId())){
                SysUserVo sysUserVo = new SysUserVo();
                sysUserVo.setId(articleVo1.getUserId());
                SysUser sysUser = sysUserService.querySysUser(sysUserVo);
                articleVo1.setUsername(sysUser.getNickName());
            }
        }
        Long total = articleService.queryArticleCount(articleVo,null);
        if (pageNum * pageSize >= total) {
            map.put("pageNum", 0);
        } else {
            map.put("pageNum", pageNum + 1);
        }
        map.put("articles", articles);
    }

    /**
     * 二级目录下的文章
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @param map 返回Map集合
     * @param folderVo 一级目录
     */
    private void secondFolderArticles(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HashMap<String, Object> map, FolderVo folderVo) {
        Map<Long,FolderVo> folderVoMap = new HashMap<Long, FolderVo>();
        folderVoMap.put(folderVo.getId(),folderVo);
        List<Long> folderIds = new ArrayList<Long>();
        folderIds.add(folderVo.getId());
        for (FolderVo folderVo2:folderVo.getFolders()) {
            folderIds.add(folderVo2.getId());
            folderVoMap.put(folderVo2.getId(),folderVo2);
        }
        ArticleVo articleVo = new ArticleVo();
        articleVo.setPageSize(pageSize);
        articleVo.setPageNum(pageNum);
        articleVo.setCheck(0);
        articleVo.setStatus(0);
        articleVo.setIsPublish(1);
        List<ArticleVo> articleVos = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,folderIds), ArticleVo.class);
        setUrlAndUsername(folderVoMap, articleVos);
        Long total = articleService.queryArticleCount(articleVo,folderIds);
        if (pageNum * pageSize >= total) {
            map.put("pageNum", 0);
        } else {
            map.put("pageNum", pageNum + 1);
        }
        map.put("articles", articleVos);
    }

    /**
     * 设置文章地址和作者
     * @param folderVoMap 目录集合
     * @param articleVos 文章集合
     */
    private void setUrlAndUsername( Map<Long, FolderVo> folderVoMap, List<ArticleVo> articleVos) {
        for (ArticleVo articleVo1:articleVos) {
            StringBuilder stringBuilder = new StringBuilder();
            FolderVo fatherFolder = folderVoMap.get(articleVo1.getFatherFolderId());
            if (fatherFolder != null){
                stringBuilder.append("/").append(fatherFolder.getEname());
            }
            FolderVo folderVo2 = folderVoMap.get(articleVo1.getFolderId());
            if (folderVo2 != null){
                stringBuilder.append("/").append(folderVo2.getEname());
            }
            stringBuilder.append("/").append(articleVo1.getId()).append(".html");
            articleVo1.setUrl(stringBuilder.toString());
            articleVo1.setMonthDay(DateUtils.formatPattern(articleVo1.getGmtArticle(), "MM/dd"));
            articleVo1.setYear(DateUtils.formatPattern(articleVo1.getGmtArticle(), "yyyy"));
            articleVo1.setArticleTime(DateUtils.format(articleVo1.getGmtArticle()));
            articleVo1.setViewCount(articleVo1.getViewCount() + 1);
            articleService.updateViewCountById(articleVo1);
            if (StringUtils.isNotEmpty(articleVo1.getUserId()) && !"0".equals(articleVo1.getUserId())){
                SysUserVo sysUserVo = new SysUserVo();
                sysUserVo.setId(articleVo1.getUserId());
                SysUser sysUser = sysUserService.querySysUser(sysUserVo);
                articleVo1.setUsername(sysUser.getNickName());
            }
        }
    }

    /**
     *  搜索文章
     * @param pageNum 当前页
     * @param search 搜索内容
     * @param pageSize 每页大小
     * @param map 返回的ModelMap
     */
    private void searchArticle(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "search") String search,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HashMap<String, Object> map) {
        FolderVo searchFolderVo = new FolderVo();
        searchFolderVo.setStatus(0);
        searchFolderVo.setLevel(1);
        searchFolderVo.setPageNum(1);
        searchFolderVo.setPageSize(100);
        Map<Long,FolderVo> folderVoMap = new HashMap<Long, FolderVo>();
        List<FolderVo> folderVos = folderService.queryFolders(searchFolderVo);
        List<Long> folderIds = new ArrayList<Long>();
        for (FolderVo folderVo :folderVos) {
            folderIds.add(folderVo.getId());
            folderVoMap.put(folderVo.getId(),folderVo);
            if (folderVo.getFolders() != null && folderVo.getFolders().size() >0 ){
                for (FolderVo folderVo1:folderVo.getFolders()) {
                    folderIds.add(folderVo1.getId());
                    folderVoMap.put(folderVo1.getId(),folderVo1);
                }
            }
        }
        ArticleVo articleVo = new ArticleVo();
        articleVo.setSearch(search);
        articleVo.setPageSize(pageSize);
        articleVo.setPageNum(pageNum);
        articleVo.setCheck(0);
        articleVo.setStatus(0);
        articleVo.setIsPublish(1);
        List<ArticleVo> articleVos = BeanConvertUtils.convertList(articleService.queryArticles(articleVo,folderIds), ArticleVo.class);
        setUrlAndUsername( folderVoMap, articleVos);
        map.put("articles", articleVos);
        Long total = articleService.queryArticleCount(articleVo,folderIds);
        if (pageNum * pageSize >= total) {
            map.put("pageNum", 0);
        } else {
            map.put("pageNum", pageNum + 1);
        }
    }
}
