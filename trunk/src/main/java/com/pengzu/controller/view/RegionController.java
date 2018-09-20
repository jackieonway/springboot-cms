package com.pengzu.controller.view;

import com.pengzu.controller.BaseController;
import com.pengzu.entity.PengzuRegion;
import com.pengzu.entity.result.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class RegionController extends BaseController {

    @RequestMapping("/region/{parentCode}")
    public Response<List<PengzuRegion>> getRegion(@PathVariable(value = "parentCode") String parentCode) {
        try {
            if (StringUtils.isEmpty(parentCode)) {
                parentCode = "100000";
            }
            List<PengzuRegion> pengzuRegions = pengzuRegionService.queryRegionByParentCode(parentCode);
            return new Response<List<PengzuRegion>>(true, "获取地区成功", pengzuRegions, "200");
        } catch (Exception e) {
            logger.error("获取地区信息失败", e);
            return new Response<>(false, "获取地区信息失败", "500");
        }
    }
}
