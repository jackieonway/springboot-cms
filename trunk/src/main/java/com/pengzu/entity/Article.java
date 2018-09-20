package com.pengzu.entity;

import java.util.Date;

public class Article {
    private Long id;

    private Long folderId;

    private Long fatherFolderId;

    private String userId;

    private String picture;

    private String title;

    private String summary;

    private Long viewCount;

    private Long commentCount;

    private Integer status;

    private Integer check;

    private String keywords;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private Date gmtArticle;

    private Date gmtPublish;

    private Integer isPublish;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getFatherFolderId() {
        return fatherFolderId;
    }

    public void setFatherFolderId(Long fatherFolderId) {
        this.fatherFolderId = fatherFolderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtArticle() {
        return gmtArticle;
    }

    public void setGmtArticle(Date gmtArticle) {
        this.gmtArticle = gmtArticle;
    }

    public Date getGmtPublish() {
        return gmtPublish;
    }

    public void setGmtPublish(Date gmtPublish) {
        this.gmtPublish = gmtPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"folderId\":")
                .append(folderId);
        sb.append(",\"fatherFolderId\":")
                .append(fatherFolderId);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"picture\":\"")
                .append(picture).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"summary\":\"")
                .append(summary).append('\"');
        sb.append(",\"viewCount\":")
                .append(viewCount);
        sb.append(",\"commentCount\":")
                .append(commentCount);
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"check\":")
                .append(check);
        sb.append(",\"keywords\":\"")
                .append(keywords).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"updateTime\":\"")
                .append(updateTime).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"gmtArticle\":\"")
                .append(gmtArticle).append('\"');
        sb.append(",\"gmtPublish\":\"")
                .append(gmtPublish).append('\"');
        sb.append(",\"isPublish\":")
                .append(isPublish);
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append('}');
        return sb.toString();
    }
}