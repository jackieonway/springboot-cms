package com.pengzu.controller.manage;

import com.pengzu.entity.Headline;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.HeadlineVo;
import com.pengzu.utils.RegExpValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminManager/headline")
public class HeadlineController extends BaseController {

    @RequestMapping("/list.html")
    @RequiresPermissions("H02")
    public String list(ModelMap map, HeadlineVo headlineVo) {
        List<Headline> headlines = headlineService.queryHeadlines(headlineVo);
        Long total = headlineService.queryHeadlineCount(headlineVo);
        map.put("headlines", headlines);
        map.put("total", total);
        map.put("pageSize", headlineVo.getPageSize());
        map.put("pageNum", headlineVo.getPageNum());
        return "manage/headline/list";
    }

    @RequestMapping("/add.html")
    @RequiresPermissions("H03")
    public String add() {
        return "manage/headline/add";
    }

    @RequestMapping("update.html")
    @RequiresPermissions("H04")
    public String update(@RequestParam("id") Long id, ModelMap modelMap) {
        HeadlineVo headlineVo = new HeadlineVo();
        headlineVo.setId(id);
        modelMap.put("headline", headlineService.queryHeadline(headlineVo));
        return "manage/headline/update";
    }

    @ResponseBody
    @RequiresPermissions("H05")
    @RequestMapping(value = "/addHeadline", method = RequestMethod.POST)
    public Response<Headline> addHeadline(HeadlineVo headlineVo) {
        try {
            if (!RegExpValidatorUtils.isUrl(headlineVo.getUrl())) {
                return new Response<>(false, "链接不正确哟", "500");
            }
            Headline headline = headlineService.addHeadline(headlineVo);
            if (headline != null) {
                return new Response<Headline>(true, "新增轮播图成功", headline, "200");
            } else {
                return new Response<>(false, "新增轮播图失败", "500");
            }

        } catch (Exception e) {
            logger.error("新增轮播图失败", e);
            return new Response<>(false, "新增轮播图失败", "500");
        }
    }

    @ResponseBody
    @RequiresPermissions("H06")
    @RequestMapping(value = "/updateHeadline", method = RequestMethod.POST)
    public Response<Headline> updateHeadline(HeadlineVo headlineVo) {
        try {
            if (!RegExpValidatorUtils.isUrl(headlineVo.getUrl())) {
                return new Response<>(false, "链接不正确哟", "500");
            }
            Headline headline = headlineService.updateHeadline(headlineVo);
            if (headline != null) {
                return new Response<Headline>(true, "修改轮播图成功", headline, "200");
            } else {
                return new Response<>(false, "修改轮播图失败", "500");
            }

        } catch (Exception e) {
            logger.error("修改轮播图失败", e);
            return new Response<>(false, "修改轮播图失败", "500");
        }
    }

    @ResponseBody
    @RequiresPermissions("H07")
    @RequestMapping(value = "/deleteHeadline", method = RequestMethod.POST)
    public Response<Integer> deleteHeadline(HeadlineVo headlineVo) {
        try {
            Integer count = headlineService.deleteHeadline(headlineVo);
            if (count != null && count > 0) {
                return new Response<Integer>(true, "删除轮播图成功", count, "200");
            } else {
                return new Response<>(false, "删除轮播图失败", "500");
            }

        } catch (Exception e) {
            logger.error("删除轮播图失败", e);
            return new Response<>(false, "删除轮播图失败", "500");
        }
    }
}
