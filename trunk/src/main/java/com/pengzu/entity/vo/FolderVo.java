package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;

import java.util.Date;
import java.util.List;

public class FolderVo extends PageBean {

    private Long id;

    private Long fatherId;

    private String ename;

    private String name;

    private Integer level;

    private Integer sort;

    private Integer count;

    private Integer status;

    private Integer check;

    private Date gmtCreate;

    private Date gmtModify;

    private String title;

    private String keywords;

    private String description;

    private Integer isDelete;

    public List<FolderVo> getFolders() {
        return folders;
    }

    public void setFolders(List<FolderVo> folders) {
        this.folders = folders;
    }

    private List<FolderVo> folders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"fatherId\":")
                .append(fatherId);
        sb.append(",\"ename\":\"")
                .append(ename).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"level\":")
                .append(level);
        sb.append(",\"sort\":")
                .append(sort);
        sb.append(",\"count\":")
                .append(count);
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"check\":")
                .append(check);
        sb.append(",\"gmtCreate\":\"")
                .append(gmtCreate).append('\"');
        sb.append(",\"gmtModify\":\"")
                .append(gmtModify).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"keywords\":\"")
                .append(keywords).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"folders\":")
                .append(folders);
        sb.append('}');
        return sb.toString();
    }
}
