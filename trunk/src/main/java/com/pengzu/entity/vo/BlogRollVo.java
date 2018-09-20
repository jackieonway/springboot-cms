package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;

import java.util.Date;

public class BlogRollVo extends PageBean {

    private Long id;

    private String blogrollName;

    private String blogrollUrl;

    private String description;

    private String blogrollImageUrl;

    private Date createDate;

    private Date modifyDate;

    private Long userId;

    private Integer status;

    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogrollName() {
        return blogrollName;
    }

    public void setBlogrollName(String blogrollName) {
        this.blogrollName = blogrollName;
    }

    public String getBlogrollUrl() {
        return blogrollUrl;
    }

    public void setBlogrollUrl(String blogrollUrl) {
        this.blogrollUrl = blogrollUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlogrollImageUrl() {
        return blogrollImageUrl;
    }

    public void setBlogrollImageUrl(String blogrollImageUrl) {
        this.blogrollImageUrl = blogrollImageUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(",\"blogrollName\":\"")
                .append(blogrollName).append('\"');
        sb.append(",\"blogrollUrl\":\"")
                .append(blogrollUrl).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"blogrollImageUrl\":\"")
                .append(blogrollImageUrl).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"modifyDate\":\"")
                .append(modifyDate).append('\"');
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"time\":\"")
                .append(time).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
