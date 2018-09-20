package com.pengzu.entity;

import com.pengzu.entity.result.PageBean;

public class SysUserRole extends PageBean{
    private String id;

    private String uid;

    private String rid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"uid\":\"")
                .append(uid).append('\"');
        sb.append(",\"rid\":\"")
                .append(rid).append('\"');
        sb.append('}');
        return sb.toString();
    }
}