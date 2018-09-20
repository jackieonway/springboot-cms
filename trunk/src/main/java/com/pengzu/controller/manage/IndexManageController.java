package com.pengzu.controller.manage;

import com.pengzu.config.CodeEnum;
import com.pengzu.entity.SysPermission;
import com.pengzu.entity.SysRolePermission;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.SysUserRole;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.SysPermissionVo;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/10.
 */
@Controller
@RequestMapping("/adminManager")
public class IndexManageController extends BaseController {

    @Value("${email.username}")
    private String sender;

    @RequestMapping("/")
    public String home(ModelMap modelMap) {
        return getHome(modelMap);
    }

    @RequestMapping({"", "/index.htm", "/index.html"})
    public String getHome(ModelMap modelMap) {
        return "/manage/index";
    }

    @RequestMapping(value = "/login.html")
    public String loginPage() {
        return "manage/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> login(SysUserVo sysUserVo, HttpSession session) {
        if (StringUtils.isEmpty(sysUserVo.getVerifyCode())) {
            logger.info("登录失败,用户输入验证码为空");
            return new Response<>(false, CodeEnum.VERIFY_CODE_IS_NULL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        String verifyCode = String.valueOf(session.getAttribute(CodeEnum.VERIFY_CODE_ID.getValue()));
        if (!sysUserVo.getVerifyCode().equalsIgnoreCase(verifyCode)) {
            logger.info("登录失败,用户输入验证码不正确,用户输入:[" + sysUserVo.getVerifyCode() + "],系统缓存:[" + verifyCode + "]");
            return new Response<>(false, CodeEnum.VERIFY_CODE_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(
                    sysUserVo.getUsername(), sysUserVo.getPassword());
            currentUser.login(token);
        } catch (DisabledAccountException e2) {
            logger.info("登录失败,用户被禁用:" + e2.getMessage(), e2);
            return new Response<>(false, CodeEnum.USER_FORBIDDEN.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        } catch (AccountException e1) {
            logger.error("登录失败,账户错误:" + e1.getMessage(), e1);
            return new Response<>(false, CodeEnum.USERNAME_OR_PASSWORD_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        } catch (Exception e) {
            logger.error("登录失败,其他原因:" + e.getMessage(), e);
            return new Response<>(false, CodeEnum.USERNAME_OR_PASSWORD_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        SysUser sysUser1 = new SysUser();
        sysUser1.setId(sysUser.getId());
        sysUser1.setNickName(sysUser.getNickName());
        sysUser1.setUsername(sysUser.getUsername());
        session.setAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue(), sysUser1);
        //
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUid(sysUser.getId());
        sysUserRole = sysUserRoleService.querySysUserRole(sysUserRole);
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRid(sysUserRole.getRid());
        sysRolePermission.setPageSize(10000);
        sysRolePermission.setPageNum(1);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.querySysRolePermissions(sysRolePermission);
        SysPermissionVo sysPermissionVo = new SysPermissionVo();
        sysPermissionVo.setPermissionType("0");
        sysPermissionVo.setPageNum(1);
        sysPermissionVo.setPageSize(100);
        List<SysPermission> sysPermissions = sysPermissionService.querySysPermissions(sysPermissionVo);
        List<Map<String, SysPermission>> sysPermissionList = new ArrayList<Map<String, SysPermission>>();
        if (sysRolePermissions != null && sysRolePermissions.size() > 0) {
            for (SysRolePermission sysRolePermission1 : sysRolePermissions) {
                for (SysPermission sysPermission : sysPermissions) {
                    if (sysPermission.getId().equalsIgnoreCase(sysRolePermission1.getPid())) {
                        Map<String, SysPermission> map = new HashMap<String, SysPermission>(2);
                        SysPermissionVo sysPermissionVo1 = new SysPermissionVo();
                        sysPermissionVo1.setParentId(sysPermission.getId());
                        sysPermissionVo1.setPermissionType("1");
                        SysPermission sysPermission1 = sysPermissionService.querySysPermission(sysPermissionVo1);
                        map.put("0", sysPermission);
                        map.put("1", sysPermission1);
                        sysPermissionList.add(map);
                    }
                }
            }
        }
        session.setAttribute("permissionList", sysPermissionList);
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/logout")
    public Response<Integer> logout(HttpSession session) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), null,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @GetMapping("/forget.html")
    public String forget() {
        return "manage/forget";
    }

    @ResponseBody
    @PostMapping("/forgetInfo")
    public Response forgetInfo(String username, String email, String verifyCode, HttpSession session) {
        if (StringUtils.isEmpty(username)) {
            return new Response(false, "请输入账号", CodeEnum.FAIL_CODE.getValue());
        }
        if (StringUtils.isEmpty(email)) {
            return new Response(false, "请输入邮箱", CodeEnum.FAIL_CODE.getValue());
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return new Response(false, "请输入验证码", CodeEnum.FAIL_CODE.getValue());
        }
        String code = String.valueOf(session.getAttribute(CodeEnum.VERIFY_CODE_ID.getValue()));
        if (!verifyCode.equalsIgnoreCase(code)) {
            return new Response<>(false, CodeEnum.VERIFY_CODE_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setEmail(email);
        sysUserVo.setUsername(username);
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        if (sysUser == null) {
            return new Response(false, "账号或者邮箱不正确.", CodeEnum.FAIL_CODE.getValue());
        }
        String verifyCode1 = VerifyCodeUtils.generateVerifyCode(6);
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<title>找回密码</title>");
        content.append("<meta charset=\"UTF-8\"/>");
        content.append("</head>");
        content.append("<body>");
        content.append("<h2 style=\"align:center\">找回密码</h2>");
        content.append("<p>亲爱的用户:</p>");
        content.append("<p>      您的邮箱验证码为: <span style=\"color: red;\">");
        content.append(verifyCode1);
        content.append("</span> , 30分钟内有效.若非本人操作,请尽快修改密码,若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
        content.append("</body>");
        content.append("</html>");
        threadService.sendSimpleHtmlEmail(javaMailSender, sender, sysUser.getEmail(), null, "找回密码", content.toString(), true);
        session.setAttribute("email_code", verifyCode1);
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        return new Response(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }

    @GetMapping("inputPwd.html")
    public String inputPwd(HttpSession session) {
        String email = String.valueOf(session.getAttribute("email"));
        if (StringUtils.isEmpty(email) || "null".equalsIgnoreCase(email)) {
            return "forward:/403.html";
        }
        return "manage/inputPwd";
    }

    @ResponseBody
    @PostMapping("/inputPwdInfo")
    public Response inputPwdInfo(String password, String rePassword, String verifyCode, HttpSession session) {
        String username = String.valueOf(session.getAttribute("username"));
        String email = String.valueOf(session.getAttribute("email"));
        if (StringUtils.isEmpty(password)) {
            return new Response(false, "请输入新密码", CodeEnum.FAIL_CODE.getValue());
        }
        if (StringUtils.isEmpty(rePassword)) {
            return new Response(false, "请再次输入密码", CodeEnum.FAIL_CODE.getValue());
        }
        if (!password.equalsIgnoreCase(rePassword)) {
            return new Response<>(false, "两次输入密码不一致",
                    CodeEnum.FAIL_CODE.getValue());
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return new Response(false, "请输入验证码", CodeEnum.FAIL_CODE.getValue());
        }
        String code = String.valueOf(session.getAttribute("email_code"));
        if (!verifyCode.equalsIgnoreCase(code)) {
            return new Response<>(false, CodeEnum.VERIFY_CODE_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setEmail(email);
        sysUserVo.setUsername(username);
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        if (sysUser == null) {
            return new Response(false, "账号或者邮箱不正确.", CodeEnum.FAIL_CODE.getValue());
        }
        SysUser sysUser1 = new SysUser();
        sysUser1.setId(sysUser.getId());
        sysUser1.setPassword(password);
        sysUser = sysUserService.updateSysUser(sysUser1);
        if (sysUser == null) {
            return new Response(false, CodeEnum.FAIL.getValue(), CodeEnum.FAIL_CODE.getValue());
        }
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<title>找回密码成功</title>");
        content.append("<meta charset=\"UTF-8\"/>");
        content.append("</head>");
        content.append("<body>");
        content.append("<h2 style=\"align:center\">找回密码成功</h2>");
        content.append("<p>亲爱的用户:</p>");
        content.append("<p>      您的新密码为: <span style=\"color: red;\">");
        content.append(password);
        content.append("</span> , 请妥善保存.若非本人操作,请尽快修改密码,若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
        content.append("</body>");
        content.append("</html>");
        threadService.sendSimpleHtmlEmail(javaMailSender, sender, email, null, "找回密码成功", content.toString(), true);
        return new Response(true, CodeEnum.SUCCESS.getValue(), null, CodeEnum.SUCCESS_CODE.getValue());
    }
}
