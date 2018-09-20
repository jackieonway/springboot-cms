package com.pengzu.controller.manage;

import com.pengzu.config.CodeEnum;
import com.pengzu.entity.SysRole;
import com.pengzu.entity.SysUser;
import com.pengzu.entity.SysUserRole;
import com.pengzu.entity.result.Response;
import com.pengzu.entity.vo.SysRoleVo;
import com.pengzu.entity.vo.SysUserVo;
import com.pengzu.utils.BeanConvertUtils;
import com.pengzu.utils.RegExpValidatorUtils;
import com.pengzu.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

//@Login
@Controller
@RequestMapping(value = "/adminManager/user")
public class UserManageController extends BaseController {

    @Value("${email.username}")
    private String sender;

    @RequestMapping("/list.html")
    @RequiresPermissions("U02")
    public String list(ModelMap map, SysUserVo sysUserVo) {
        List<SysUserVo> users = BeanConvertUtils.convertList(
                sysUserService.querySysUsers(sysUserVo), SysUserVo.class);
        for (SysUserVo sysUserVo1 : users) {
            if (StringUtils.isNotEmpty(sysUserVo1.getPhone())) {
                sysUserVo1.setPhone(RegExpValidatorUtils.hidePhone(sysUserVo1.getPhone()));
            }
            if (StringUtils.isNotEmpty(sysUserVo1.getEmail())) {
                sysUserVo1.setEmail(RegExpValidatorUtils.hideEmail(sysUserVo1.getEmail()));
            }
            if (StringUtils.isNotEmpty(sysUserVo1.getIdcard())) {
                sysUserVo1.setIdcard(RegExpValidatorUtils.hideIdCard(sysUserVo1.getIdcard()));
            }
        }
        Long total = sysUserService.querySysUserCount(sysUserVo);
        map.put("users", users);
        map.put("total", total);
        map.put("pageSize", sysUserVo.getPageSize());
        map.put("pageNum", sysUserVo.getPageNum());
        return "manage/user/list";
    }

    @RequestMapping("/add.html")
    @RequiresPermissions("U03")
    public String add() {
        return "manage/user/add";
    }

    @RequestMapping("update.html")
    @RequiresPermissions("U04")
    public String update(SysUserVo sysUserVo, ModelMap modelMap) {
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        if (StringUtils.isNotEmpty(sysUser.getPhone())) {
            sysUser.setPhone(RegExpValidatorUtils.hidePhone(sysUser.getPhone()));
        }
        if (StringUtils.isNotEmpty(sysUser.getEmail())) {
            sysUser.setEmail(RegExpValidatorUtils.hideEmail(sysUser.getEmail()));
        }
        if (StringUtils.isNotEmpty(sysUser.getIdcard())) {
            sysUser.setIdcard(RegExpValidatorUtils.hideIdCard(sysUser.getIdcard()));
        }
        sysUser.setPassword(null);
        modelMap.put("user", sysUser);
        return "manage/user/update";
    }

    @RequestMapping("updateUser.html")
    @RequiresPermissions("U05")
    public String updateUserInfo(SysUserVo sysUserVo, ModelMap modelMap) {
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        if (StringUtils.isNotEmpty(sysUser.getPhone())) {
            sysUser.setPhone(RegExpValidatorUtils.hidePhone(sysUser.getPhone()));
        }
        if (StringUtils.isNotEmpty(sysUser.getEmail())) {
            sysUser.setEmail(RegExpValidatorUtils.hideEmail(sysUser.getEmail()));
        }
        if (StringUtils.isNotEmpty(sysUser.getIdcard())) {
            sysUser.setIdcard(RegExpValidatorUtils.hideIdCard(sysUser.getIdcard()));
        }
        sysUser.setPassword(null);
        modelMap.put("user", sysUser);
        return "manage/user/updateUser";
    }

    @RequestMapping("updatePassword.html")
    @RequiresPermissions("U06")
    public String updatePassword(SysUserVo sysUserVo, ModelMap modelMap) {
        SysUser sysUser = sysUserService.querySysUser(sysUserVo);
        modelMap.put("user", sysUser);
        return "manage/user/updatePassword";
    }

    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("U07")
    public Response<SysUser> addSysUser(SysUserVo sysUserVo) {
        if (!RegExpValidatorUtils.isHandset(sysUserVo.getPhone())) {
            return new Response<>(false, CodeEnum.PHONE_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUserVo sysUserVo1 = new SysUserVo();
        sysUserVo1.setPhone(sysUserVo.getPhone());
        List<SysUser> sysUsers = sysUserService.querySysUsers(sysUserVo1);
        if (sysUsers != null && sysUsers.size() >= 1) {
            return new Response<>(false, "该联系方式已被使用,请更换其他联系方式",
                    CodeEnum.FAIL_CODE.getValue());
        }
        if (!RegExpValidatorUtils.isEmail(sysUserVo.getEmail())) {
            return new Response<>(false, CodeEnum.EMAIL_ERROR.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        sysUserVo1 = new SysUserVo();
        sysUserVo1.setEmail(sysUserVo.getEmail());
        sysUsers = sysUserService.querySysUsers(sysUserVo1);
        if (sysUsers != null && sysUsers.size() >= 1) {
            return new Response<>(false, "该邮箱已被使用,请更换其他邮箱",
                    CodeEnum.FAIL_CODE.getValue());
        }
        sysUserVo1 = new SysUserVo();
        sysUserVo1.setUsername(sysUserVo.getUsername());
        sysUsers = sysUserService.querySysUsers(sysUserVo1);
        if (sysUsers != null && sysUsers.size() >= 1) {
            return new Response<>(false, "该用户名已被使用,请更换其他用户名",
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUser sysUser = sysUserService.addSysUser(
                BeanConvertUtils.convert(sysUserVo, SysUser.class));
        if (sysUser == null) {
            return new Response<>(false, CodeEnum.FAIL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), sysUser,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("U08")
    public Response<SysUser> updateSysUser(SysUserVo sysUserVo, String code, HttpSession session) {
        if (StringUtils.isEmpty(code)) {
            return new Response<>(false, "请输入邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        String sessionCode = String.valueOf(session.getAttribute("email_code"));
        if (!code.equalsIgnoreCase(sessionCode)) {
            return new Response<>(false, "请输入正确的邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUserVo sysUserVo1 = new SysUserVo();
        sysUserVo1.setId(sysUserVo.getId());
        SysUser user = sysUserService.querySysUser(sysUserVo1);
        boolean phoneFlag = false;
        boolean emailFlag = false;
        if (StringUtils.isNotEmpty(sysUserVo.getPhone())) {
            if (!sysUserVo.getPhone().contains("*")) {
                if (!RegExpValidatorUtils.isHandset(sysUserVo.getPhone())) {
                    return new Response<>(false, CodeEnum.PHONE_ERROR.getValue(),
                            CodeEnum.FAIL_CODE.getValue());
                }
                sysUserVo1 = new SysUserVo();
                sysUserVo1.setPhone(sysUserVo.getPhone());
                List<SysUser> sysUsers = sysUserService.querySysUsers(sysUserVo1);
                if (sysUsers != null && sysUsers.size() >= 1) {
                    return new Response<>(false, "该联系方式已被使用,请更换其他联系方式",
                            CodeEnum.FAIL_CODE.getValue());
                }
                phoneFlag = true;
            } else {
                sysUserVo.setPhone(null);
            }
        }
        if (StringUtils.isNotEmpty(sysUserVo.getEmail())) {
            if (!sysUserVo.getEmail().contains("*")) {
                if (!RegExpValidatorUtils.isEmail(sysUserVo.getEmail())) {
                    return new Response<>(false, CodeEnum.EMAIL_ERROR.getValue(),
                            CodeEnum.FAIL_CODE.getValue());
                }
                sysUserVo1 = new SysUserVo();
                sysUserVo1.setEmail(sysUserVo.getEmail());
                List<SysUser> sysUsers = sysUserService.querySysUsers(sysUserVo1);
                if (sysUsers != null && sysUsers.size() >= 1) {
                    return new Response<>(false, "该邮箱已被使用,请更换其他邮箱",
                            CodeEnum.FAIL_CODE.getValue());
                }
                emailFlag = true;
            } else {
                sysUserVo.setEmail(null);
            }
        }
        SysUser sysUser = null;
        sysUser = sysUserService.updateSysUser(
                BeanConvertUtils.convert(sysUserVo, SysUser.class));
        if (sysUser == null) {
            return new Response<>(false, CodeEnum.FAIL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<title>用户信息更改通知</title>");
        content.append("<meta charset=\"UTF-8\"/>");
        content.append("</head>");
        content.append("<body>");
        content.append("<h2 style=\"align:center\">用户信息更改通知</h2>");
        content.append("<p>亲爱的用户:</p>");
        content.append("<p>      您的用户信息已被修改.");
        if (phoneFlag) {
            content.append("您的联系方式已从<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hidePhone(user.getPhone()));
            content.append("</span>变更为:<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hidePhone(sysUser.getPhone()));
            content.append("</span>.");
        }
        if (emailFlag) {
            content.append("您的邮箱已从<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hideEmail(user.getEmail()));
            content.append("</span>变更为:<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hideEmail(sysUser.getEmail()));
            content.append("</span>.");
        }
        content.append("若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
        content.append("</body>");
        content.append("</html>");
        threadService.sendSimpleHtmlEmail(javaMailSender, sender, user.getEmail(), null, "用户信息更改通知", content.toString(), true);
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), sysUser,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("U09")
    public Response<Integer> deleteSysUser(SysUserVo sysUserVo) {
        Integer integer = sysUserService.deleteSysUser(
                BeanConvertUtils.convert(sysUserVo, SysUser.class));
        if (integer == null || integer.equals(0)) {
            return new Response<>(false, CodeEnum.FAIL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), integer,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @GetMapping("/role.html")
    @RequiresPermissions("U10")
    public String roles(SysUserVo sysUserVo, ModelMap modelMap, HttpSession session) {
        Object o = session.getAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue());
        SysUser sysUser = null;
        SysUserRole sessionSysUserRole = new SysUserRole();
        SysUserRole chooseSysUserRole = new SysUserRole();
        List<SysRole> roles = null;
        //判断Session中获取的值是否是用户
        if (o instanceof SysUser) {
            sysUser = (SysUser) o;
            sessionSysUserRole.setUid(sysUser.getId());
            //查询当前用户的用户角色
            sessionSysUserRole = sysUserRoleService.querySysUserRole(sessionSysUserRole);
            SysRoleVo sessionUserRole = new SysRoleVo();
            //当前登录用户的用户角色不为空
            if (sessionSysUserRole != null) {
                sessionUserRole.setId(sessionSysUserRole.getRid());
                //查询当前用户的角色信息
                sessionUserRole = BeanConvertUtils.convert(
                        sysRoleService.querySysRole(sessionUserRole), SysRoleVo.class
                );
                //当前登录用户角色,在系统中有该角色
                if (sessionUserRole != null) {
                    //查询选择用户的用户角色表
                    chooseSysUserRole.setUid(sysUserVo.getId());
                    chooseSysUserRole = sysUserRoleService.querySysUserRole(chooseSysUserRole);
                    //被选择用户的用户角色不为空
                    if (chooseSysUserRole != null) {
                        SysRoleVo chooseUserRole = new SysRoleVo();
                        chooseUserRole.setId(chooseSysUserRole.getRid());
                        chooseUserRole = BeanConvertUtils.convert(
                                sysRoleService.querySysRole(chooseUserRole), SysRoleVo.class
                        );
                        //当前用户角色级别高于被选择用户
                        if (sessionUserRole.getRoleCode().charAt(0) > chooseUserRole.getRoleCode().charAt(0)) {
                            SysRoleVo sysRoleVo = new SysRoleVo();
                            sysRoleVo.setPageNum(1);
                            sysRoleVo.setPageSize(1000);
                            roles = sysRoleService.querySysRolesByKeywords(sysRoleVo);
                        }
                    } else {
                        SysRoleVo sysRoleVo = new SysRoleVo();
                        sysRoleVo.setPageNum(1);
                        sysRoleVo.setPageSize(1000);
                        roles = sysRoleService.querySysRolesByKeywords(sysRoleVo);
                    }
                }
            }
        }
        modelMap.put("roles", roles);
        modelMap.put("userId", sysUserVo.getId());
        modelMap.put("userRole", chooseSysUserRole);
        return "manage/user/role";
    }

    /**
     * 删除已选择的角色，并添加新角色
     *
     * @param sysUserRole 用户角色中间表实体
     * @return 添加结果
     */
    @ResponseBody
    @PostMapping("/addRole")
    @RequiresPermissions("U11")
    public Response chooseRole(SysUserRole sysUserRole, HttpSession session) {
        Object o = session.getAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue());
        SysUser sysUser = null;
        SysUserRole sessionSysUserRole = new SysUserRole();
        SysUserRole chooseSysUserRole = new SysUserRole();
        //判断Session中获取的值是否是用户
        if (o instanceof SysUser) {
            sysUser = (SysUser) o;
            sessionSysUserRole.setUid(sysUser.getId());
            //查询当前用户的用户角色
            sessionSysUserRole = sysUserRoleService.querySysUserRole(sessionSysUserRole);
            SysRoleVo sessionUserRole = new SysRoleVo();
            sessionUserRole.setId(sessionSysUserRole.getRid());
            //查询当前用户的角色信息
            sessionUserRole = BeanConvertUtils.convert(
                    sysRoleService.querySysRole(sessionUserRole), SysRoleVo.class
            );
            //查询选择用户的用户角色表
            chooseSysUserRole.setUid(sysUserRole.getUid());
            chooseSysUserRole = sysUserRoleService.querySysUserRole(chooseSysUserRole);
            if (chooseSysUserRole != null) {
                SysRoleVo chooseUserRole = new SysRoleVo();
                chooseUserRole.setId(chooseSysUserRole.getRid());
                chooseUserRole = BeanConvertUtils.convert(
                        sysRoleService.querySysRole(chooseUserRole), SysRoleVo.class
                );
                //当前用户角色级别高于被选择用户
                if (sessionUserRole.getRoleCode().charAt(0) > chooseUserRole.getRoleCode().charAt(0)) {
                    sysUserRole.setId(chooseSysUserRole.getId());
                    sysUserRole = sysUserRoleService.updateSysUserRole(sysUserRole);
                    if (sysUserRole != null) {
                        return new Response<>(true, CodeEnum.SUCCESS.getValue(),
                                sysUserRole, CodeEnum.SUCCESS_CODE.getValue());
                    } else {
                        return new Response<>(false, CodeEnum.FAIL.getValue(),
                                CodeEnum.FAIL_CODE.getValue());
                    }
                }
            } else {
                sysUserRole = sysUserRoleService.addSysUserRole(sysUserRole);
                if (sysUserRole != null) {
                    return new Response<>(true, CodeEnum.SUCCESS.getValue(),
                            sysUserRole, CodeEnum.SUCCESS_CODE.getValue());
                } else {
                    return new Response<>(false, CodeEnum.FAIL.getValue(),
                            CodeEnum.FAIL_CODE.getValue());
                }
            }
        }
        return new Response<>(false, CodeEnum.FAIL.getValue(),
                CodeEnum.FAIL_CODE.getValue());
    }

    @ResponseBody
    @RequestMapping("sendEmail")
    public Response updatePasswordSendEmailCode(HttpSession session) {
        try {
            SysUser sysUser = (SysUser) session.getAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue());
            SysUserVo sysUserVo = new SysUserVo();
            sysUserVo.setUsername(sysUser.getUsername());
            sysUser = sysUserService.querySysUser(sysUserVo);
            String verifyCode = VerifyCodeUtils.generateVerifyCode(6);
            StringBuilder content = new StringBuilder();
            content.append("<html>");
            content.append("<head>");
            content.append("<title>修改密码</title>");
            content.append("<meta charset=\"UTF-8\"/>");
            content.append("</head>");
            content.append("<body>");
            content.append("<h2 style=\"align:center\">修改密码验证</h2>");
            content.append("<p>亲爱的用户:</p>");
            content.append("<p>      您的邮箱验证码为: <span style=\"color: red;\">");
            content.append(verifyCode);
            content.append("</span> , 30分钟内有效.若非本人操作,请尽快修改密码,若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
            content.append("</body>");
            content.append("</html>");
            threadService.sendSimpleHtmlEmail(javaMailSender, sender, sysUser.getEmail(), null, "修改密码", content.toString(), true);
            session.setAttribute("email_code", verifyCode);
            return new Response<>(true, "获取验证码成功,若长时间未收到邮件,请查看是否在垃圾箱。", null,
                    CodeEnum.SUCCESS_CODE.getValue());
        } catch (Exception e) {
            logger.error("获取邮箱验证码失败:" + e.getMessage(), e);
            return new Response<>(false, "获取邮箱验证码失效,请稍后重试!",
                    CodeEnum.FAIL_CODE.getValue());
        }
    }

    /**
     * 修改个人信息验证邮箱
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("sendInfoEmail")
    public Response updateInfoSendEmailCode(HttpSession session) {
        try {
            SysUser sysUser = (SysUser) session.getAttribute(CodeEnum.SYS_USER_SESSION_ID.getValue());
            SysUserVo sysUserVo = new SysUserVo();
            sysUserVo.setUsername(sysUser.getUsername());
            sysUser = sysUserService.querySysUser(sysUserVo);
            String verifyCode = VerifyCodeUtils.generateVerifyCode(6);
            StringBuilder content = new StringBuilder();
            content.append("<html>");
            content.append("<head>");
            content.append("<title>修改个人信息</title>");
            content.append("<meta charset=\"UTF-8\"/>");
            content.append("</head>");
            content.append("<body>");
            content.append("<h2 style=\"align:center\">修改个人信息验证</h2>");
            content.append("<p>亲爱的用户:</p>");
            content.append("<p>      您的邮箱验证码为: <span style=\"color: red;\">");
            content.append(verifyCode);
            content.append("</span> , 30分钟内有效.若非本人操作,请尽快修改密码,若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
            content.append("</body>");
            content.append("</html>");
            threadService.sendSimpleHtmlEmail(javaMailSender, sender, sysUser.getEmail(), null, "修改个人信息", content.toString(), true);
            session.setAttribute("email_code", verifyCode);
            return new Response<>(true, "获取验证码成功,若长时间未收到邮件,请查看是否在垃圾箱。", null,
                    CodeEnum.SUCCESS_CODE.getValue());
        } catch (Exception e) {
            logger.error("获取邮箱验证码失败:" + e.getMessage(), e);
            return new Response<>(false, "获取邮箱验证码失效,请稍后重试!",
                    CodeEnum.FAIL_CODE.getValue());
        }
    }


    @ResponseBody
    @PostMapping("/updatePassword")
    @RequiresPermissions("U12")
    public Response<SysUser> updateSysUserPassword(SysUserVo sysUserVo, String code, HttpSession session) {
        if (StringUtils.isEmpty(code)) {
            return new Response<>(false, "请输入邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        String sessionCode = String.valueOf(session.getAttribute("email_code"));
        if (!code.equalsIgnoreCase(sessionCode)) {
            return new Response<>(false, "请输入正确的邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        if (StringUtils.isNotEmpty(sysUserVo.getPhone())) {
            if (!RegExpValidatorUtils.isHandset(sysUserVo.getPhone())) {
                return new Response<>(false, CodeEnum.PHONE_ERROR.getValue(),
                        CodeEnum.FAIL_CODE.getValue());
            }
        }
        if (StringUtils.isNotEmpty(sysUserVo.getEmail())) {
            if (!RegExpValidatorUtils.isEmail(sysUserVo.getEmail())) {
                return new Response<>(false, CodeEnum.EMAIL_ERROR.getValue(),
                        CodeEnum.FAIL_CODE.getValue());
            }
        }
        SysUser sysUser = sysUserService.updateSysUser(
                BeanConvertUtils.convert(sysUserVo, SysUser.class));
        if (sysUser == null) {
            return new Response<>(false, CodeEnum.FAIL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), sysUser,
                CodeEnum.SUCCESS_CODE.getValue());
    }

    @ResponseBody
    @PostMapping("/updateInfo")
    @RequiresPermissions("U13")
    public Response<SysUser> updateSysUserInfo(SysUserVo sysUserVo, String code, HttpSession session) {
        if (StringUtils.isEmpty(code)) {
            return new Response<>(false, "请输入邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        String sessionCode = String.valueOf(session.getAttribute("email_code"));
        if (!code.equalsIgnoreCase(sessionCode)) {
            return new Response<>(false, "请输入正确的邮箱验证码",
                    CodeEnum.FAIL_CODE.getValue());
        }
        SysUserVo sysUserVo1 = new SysUserVo();
        sysUserVo1.setId(sysUserVo.getId());
        SysUser user = sysUserService.querySysUser(sysUserVo1);
        boolean phoneFlag = false;
        boolean emailFlag = false;
        if (StringUtils.isNotEmpty(sysUserVo.getPhone())) {
            if (!sysUserVo.getPhone().contains("*")) {
                if (!RegExpValidatorUtils.isHandset(sysUserVo.getPhone())) {
                    return new Response<>(false, CodeEnum.PHONE_ERROR.getValue(),
                            CodeEnum.FAIL_CODE.getValue());
                }
                sysUserVo1 = new SysUserVo();
                sysUserVo1.setPhone(sysUserVo.getPhone());
                List<SysUser> sysUsers = sysUserService.querySysUsers(sysUserVo1);
                if (sysUsers != null && sysUsers.size() >= 1) {
                    return new Response<>(false, "该联系方式已被使用,请更换其他联系方式",
                            CodeEnum.FAIL_CODE.getValue());
                }
                phoneFlag = true;
            } else {
                sysUserVo.setPhone(null);
            }
        }
        if (StringUtils.isNotEmpty(sysUserVo.getEmail())) {
            if (!sysUserVo.getEmail().contains("*")) {
                if (!RegExpValidatorUtils.isEmail(sysUserVo.getEmail())) {
                    return new Response<>(false, CodeEnum.EMAIL_ERROR.getValue(),
                            CodeEnum.FAIL_CODE.getValue());
                }
                sysUserVo1 = new SysUserVo();
                sysUserVo1.setEmail(sysUserVo.getEmail());
                List<SysUser> sysUsers = sysUserService.querySysUsers(sysUserVo1);
                if (sysUsers != null && sysUsers.size() >= 1) {
                    return new Response<>(false, "该邮箱已被使用,请更换其他邮箱",
                            CodeEnum.FAIL_CODE.getValue());
                }
                emailFlag = true;
            } else {
                sysUserVo.setEmail(null);
            }
        }
        SysUser sysUser = sysUserService.updateSysUser(
                BeanConvertUtils.convert(sysUserVo, SysUser.class));
        if (sysUser == null) {
            return new Response<>(false, CodeEnum.FAIL.getValue(),
                    CodeEnum.FAIL_CODE.getValue());
        }
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<title>用户信息更改通知</title>");
        content.append("<meta charset=\"UTF-8\"/>");
        content.append("</head>");
        content.append("<body>");
        content.append("<h2 style=\"align:center\">用户信息更改通知</h2>");
        content.append("<p>亲爱的用户:</p>");
        content.append("<p>      您的用户信息已被修改.");
        if (phoneFlag) {
            content.append("您的联系方式已从<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hidePhone(user.getPhone()));
            content.append("</span>变更为:<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hidePhone(sysUser.getPhone()));
            content.append("</span>.");
        }
        if (emailFlag) {
            content.append("您的邮箱已从<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hideEmail(user.getEmail()));
            content.append("</span>变更为:<span style=\"color: red;\">");
            content.append(RegExpValidatorUtils.hideEmail(sysUser.getEmail()));
            content.append("</span>.");
        }
        content.append("若不是您的账户,请您忽略本邮件,若有打扰,敬请见谅!谢谢!</p>");
        content.append("</body>");
        content.append("</html>");
        threadService.sendSimpleHtmlEmail(javaMailSender, sender, user.getEmail(), null, "用户信息更改通知", content.toString(), true);
        return new Response<>(true, CodeEnum.SUCCESS.getValue(), sysUser,
                CodeEnum.SUCCESS_CODE.getValue());
    }
}
