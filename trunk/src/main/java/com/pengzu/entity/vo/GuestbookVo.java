package com.pengzu.entity.vo;

import com.pengzu.entity.result.PageBean;

import java.util.Date;

public class GuestbookVo extends PageBean {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String title;

    private String content;

    private String reply;

    private Integer status;

    private Date gmtCraete;

    private Date gmtModify;

    private Date gmtReply;

    private Integer isDelete;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCraete() {
        return gmtCraete;
    }

    public void setGmtCraete(Date gmtCraete) {
        this.gmtCraete = gmtCraete;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtReply() {
        return gmtReply;
    }

    public void setGmtReply(Date gmtReply) {
        this.gmtReply = gmtReply;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"reply\":\"")
                .append(reply).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"gmtCraete\":\"")
                .append(gmtCraete).append('\"');
        sb.append(",\"gmtModify\":\"")
                .append(gmtModify).append('\"');
        sb.append(",\"gmtReply\":\"")
                .append(gmtReply).append('\"');
        sb.append(",\"isDelete\":")
                .append(isDelete);
        sb.append(",\"time\":\"")
                .append(time).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
