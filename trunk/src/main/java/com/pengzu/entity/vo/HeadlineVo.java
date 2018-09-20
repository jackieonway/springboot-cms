package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class HeadlineVo extends PageBean {

    private Long id;

    private String name;

    private String picture;

    private String url;

    private Integer sort;

    private Date gmtCreate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gmtPublish;

    private Date gmtModify;

    private Integer status;

    private String alt;

    private Integer isDelete;

    private MultipartFile file;

    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPublish() {
        return gmtPublish;
    }

    public void setGmtPublish(Date gmtPublish) {
        this.gmtPublish = gmtPublish;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"picture\":\"")
                .append(picture).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"sort\":")
                .append(sort);
        sb.append(",\"gmtCreate\":\"")
                .append(gmtCreate).append('\"');
        sb.append(",\"gmtPublish\":\"")
                .append(gmtPublish).append('\"');
        sb.append(",\"gmtModify\":\"")
                .append(gmtModify).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"alt\":\"")
                .append(alt).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"file\":")
                .append(file);
        sb.append(",\"time\":\"")
                .append(time).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
