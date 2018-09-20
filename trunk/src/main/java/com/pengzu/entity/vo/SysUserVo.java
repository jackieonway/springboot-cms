package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;

import java.util.Date;
import java.util.List;

public class SysUserVo extends PageBean {

    private String id;

    private String nickName;

    private String username;

    private String email;

    private String address;

    private String sex;

    private String idcard;

    private String phone;

    private String password;

    private Date createTime;

    private Date lastLoginTime;

    private Byte status;

    private String createNameId;

    private Integer isDelete;

    private SysRoleVo sysRole;

    private String verifyCode;

    private List<SysRoleVo> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateNameId() {
        return createNameId;
    }

    public void setCreateNameId(String createNameId) {
        this.createNameId = createNameId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public SysRoleVo getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRoleVo sysRole) {
        this.sysRole = sysRole;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public List<SysRoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleVo> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"nickName\":\"")
                .append(nickName).append('\"');
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"address\":\"")
                .append(address).append('\"');
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"idcard\":\"")
                .append(idcard).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastLoginTime\":\"")
                .append(lastLoginTime).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"createNameId\":\"")
                .append(createNameId).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"sysRole\":")
                .append(sysRole);
        sb.append(",\"verifyCode\":\"")
                .append(verifyCode).append('\"');
        sb.append(",\"roles\":")
                .append(roles);
        sb.append('}');
        return sb.toString();
    }
}
