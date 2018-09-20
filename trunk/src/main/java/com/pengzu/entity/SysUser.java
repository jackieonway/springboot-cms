package com.pengzu.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SysUser {
    private String id;

    private String nickName;

    private String username;

    private String email;

    private String address;

    private String sex;

    private String idcard;

    private String phone;

    @JSONField(serialize=false)
    private String password;

    @JSONField(serialize=false)
    private Date createTime;

    @JSONField(serialize=false)
    private Date lastLoginTime;

    private Byte status;

    @JSONField(serialize=false)
    private String createNameId;

    @JSONField(serialize=false)
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
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
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
        this.createNameId = createNameId == null ? null : createNameId.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        sb.append('}');
        return sb.toString();
    }
}