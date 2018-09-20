package com.pengzu.controller.manage;

import com.pengzu.entity.PengzuConfig;
import com.pengzu.entity.result.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminManager/config")
public class PengzuConfigController extends BaseController {

    @RequestMapping("/update.html")
    @RequiresPermissions("C03")
    public String update(ModelMap modelMap, PengzuConfig pengzuConfig) {
        List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(pengzuConfig);
        for (PengzuConfig config : configs) {
            modelMap.put(config.getKey(), config.getValue());
        }
        return "manage/config/update";
    }

    @RequestMapping("/config.html")
    @RequiresPermissions("C02")
    public String list(ModelMap modelMap, PengzuConfig pengzuConfig) {
        List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(pengzuConfig);
        for (PengzuConfig config : configs) {
            modelMap.put(config.getKey(), config.getValue());
        }
        return "manage/config/config";
    }

    @ResponseBody
    @RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
    @RequiresPermissions("C04")
    public Response updateConfig(String logo, String logoBack, String siteName, String siteNameBack,
                                 String aboutUs, String copyRight, String copyRightBack, String recordNumber,
                                 String favicon, String QRCode, String telephone, String wechat,
                                 String faviconBack, String wechatQRCode, String qq,
                                 String qqgroup, String address, String weibo) {
        if (StringUtils.isEmpty(logo)) {
            return new Response(false, "前台LOGO不能为空", "500");
        }
        if (StringUtils.isEmpty(logoBack)) {
            return new Response(false, "后台LOGO不能为空", "500");
        }
        if (StringUtils.isEmpty(siteName)) {
            return new Response(false, "前台网站名称不能为空", "500");
        }
        if (StringUtils.isEmpty(siteNameBack)) {
            return new Response(false, "后台网站名称不能为空", "500");
        }
        if (StringUtils.isEmpty(aboutUs)) {
            return new Response(false, "关于我们不能为空", "500");
        }
        if (StringUtils.isEmpty(copyRight)) {
            return new Response(false, "前台版权信息不能为空", "500");
        }
        if (StringUtils.isEmpty(copyRightBack)) {
            return new Response(false, "后台版权信息不能为空", "500");
        }
        if (StringUtils.isEmpty(recordNumber)) {
            return new Response(false, "备案号不能为空", "500");
        }
        if (StringUtils.isEmpty(favicon)) {
            return new Response(false, "前台浏览器图标不能为空", "500");
        }
        if (StringUtils.isEmpty(QRCode)) {
            return new Response(false, "微信公众号二维码不能为空", "500");
        }
        if (StringUtils.isEmpty(telephone)) {
            return new Response(false, "联系电话不能为空", "500");
        }
        if (StringUtils.isEmpty(qq)) {
            return new Response(false, "qq号不能为空", "500");
        }
        if (StringUtils.isEmpty(wechat)) {
            return new Response(false, "微信不能为空", "500");
        }
        if (StringUtils.isEmpty(wechatQRCode)) {
            return new Response(false, "微信号二维码不能为空", "500");
        }
        if (StringUtils.isEmpty(address)) {
            return new Response(false, "联系地址不能为空", "500");
        }
        if (StringUtils.isEmpty(qqgroup)) {
            return new Response(false, "QQ群二维码不能为空", "500");
        }
        if (StringUtils.isEmpty(weibo)) {
            return new Response(false, "新浪微博不能为空", "500");
        }
        if (StringUtils.isEmpty(faviconBack)) {
            return new Response(false, "后台浏览器图标不能为空", "500");
        }
        Map<String, String> map = new HashMap<>();
        map.put("logo", logo);
        map.put("logoBack", logoBack);
        map.put("siteName", siteName);
        map.put("siteNameBack", siteNameBack);
        map.put("aboutUs", aboutUs);
        map.put("copyRight", copyRight);
        map.put("copyRightBack", copyRightBack);
        map.put("recordNumber", recordNumber);
        map.put("faviconBack", faviconBack);
        map.put("favicon", favicon);
        map.put("QRCode", QRCode);
        map.put("telephone", telephone);
        map.put("qq", qq);
        map.put("qqgroup", qqgroup);
        map.put("wechat", wechat);
        map.put("wechatQRCode", wechatQRCode);
        map.put("weibo", weibo);
        map.put("address", address);
        PengzuConfig pengzuConfig = new PengzuConfig();
        List<PengzuConfig> configs = pengzuConfigService.queryPengzuConfigs(pengzuConfig);
        for (PengzuConfig config : configs) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (config.getKey().equals(entry.getKey())) {
                    config.setValue(entry.getValue());
                    pengzuConfigService.updatePengzuConfig(config);
                }
            }
        }
        return new Response(true, "成功", null, "500");
    }
}
