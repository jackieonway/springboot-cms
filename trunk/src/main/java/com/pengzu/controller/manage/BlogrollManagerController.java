package com.pengzu.controller.manage;

import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.BlogRollVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adminManager/blogroll")
public class BlogrollManagerController extends BaseController {

    @RequestMapping("/add.html")
    @RequiresPermissions("B03")
    public String add() {
        return "manage/blogroll/add";
    }

    @RequestMapping("/update.html")
    @RequiresPermissions("B04")
    public String update(ModelMap modelMap, BlogRollVo blogRollVo) {
        modelMap.put("blogroll", blogRollService.queryBlogrollByPrimaryKey(blogRollVo.getId()));
        return "manage/blogroll/update";
    }

    @RequestMapping("/list.html")
    @RequiresPermissions("B02")
    public String list(ModelMap modelMap, BlogRollVo blogRollVo) {
        modelMap.put("blogrolls", blogRollService.queryBlogrolls(blogRollVo));
        modelMap.put("total", blogRollService.queryBlogrollsCount(blogRollVo));
        modelMap.put("pageNum", blogRollVo.getPageNum());
        modelMap.put("pageSize", blogRollVo.getPageSize());
        return "manage/blogroll/list";
    }

    @ResponseBody
    @RequiresPermissions("B05")
    @RequestMapping(value = "/addBlogroll", method = RequestMethod.POST)
    public Response<Integer> addBlogroll(BlogRollVo blogRollVo) {
        try {
            if (blogRollService.addBlogroll(blogRollVo) == 0) {
                return new Response<>(false, "新增友情链接失败", "500");
            } else {
                return new Response<Integer>(true, "新增成功", null, "200");
            }
        } catch (Exception e) {
            logger.error("新增友情链接失败", e);
            return new Response<>(false, "新增友情链接失败", "500");
        }
    }

    @ResponseBody
    @RequiresPermissions("B06")
    @RequestMapping(value = "/updateBlogroll", method = RequestMethod.POST)
    public Response<Integer> updateBlogroll(BlogRollVo blogRollVo) {
        try {
            if (blogRollService.updateBlogroll(blogRollVo) == 0) {
                return new Response<>(false, "更新友情链接失败", "500");
            } else {
                return new Response<Integer>(true, "更新成功", null, "200");
            }
        } catch (Exception e) {
            logger.error("更新友情链接失败", e);
            return new Response<>(false, "更新友情链接失败", "500");
        }
    }

    @ResponseBody
    @RequiresPermissions("B07")
    @RequestMapping(value = "/deleteBlogroll", method = RequestMethod.POST)
    public Response deleteBlogroll(BlogRollVo blogRollVo) {
        try {
            if (blogRollService.deleteBlogrollByPrimaryKey(blogRollVo.getId()) == 1) {
                return new Response<Integer>(true, "删除成功", null, "200");
            } else {
                return new Response(false, "删除友情链接失败", "500");
            }
        } catch (Exception e) {
            logger.error("删除友情链接失败", e);
            return new Response(false, "删除友情链接失败", "500");
        }
    }
}
