package com.pengzu.entity;

import com.pengzu.entity.result.PageBean;

public class SysRolePermission extends PageBean{
    private String id;

    private String rid;

    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"rid\":\"")
                .append(rid).append('\"');
        sb.append(",\"pid\":\"")
                .append(pid).append('\"');
        sb.append('}');
        return sb.toString();
    }
}