package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;

import java.util.List;

public class SysRoleVo extends PageBean {

    private String id;

    private String name;

    private String type;

    private String roleCode;

    private String comment;

    private Integer isDelete;

    private String keywords;

    private List<SysPermissionVo> sysPermissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<SysPermissionVo> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermissionVo> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"roleCode\":\"")
                .append(roleCode).append('\"');
        sb.append(",\"comment\":\"")
                .append(comment).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"keywords\":")
                .append(keywords);
        sb.append(",\"sysPermissions\":")
                .append(sysPermissions);
        sb.append('}');
        return sb.toString();
    }
}
