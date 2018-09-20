package com.pengzu.entity;

import java.util.Date;

public class PengzuRegion {
    private Long id;

    private String code;

    private String parentCode;

    private String regionName;

    private String abbName;

    private String regionType;

    private String zip;

    private String createrId;

    private String creater;

    private Date gmtCreate;

    private String modifierId;

    private String modifier;

    private Date gmtModifier;

    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getAbbName() {
        return abbName;
    }

    public void setAbbName(String abbName) {
        this.abbName = abbName == null ? null : abbName.trim();
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType == null ? null : regionType.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId == null ? null : modifierId.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getGmtModifier() {
        return gmtModifier;
    }

    public void setGmtModifier(Date gmtModifier) {
        this.gmtModifier = gmtModifier;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"parentCode\":\"")
                .append(parentCode).append('\"');
        sb.append(",\"regionName\":\"")
                .append(regionName).append('\"');
        sb.append(",\"abbName\":\"")
                .append(abbName).append('\"');
        sb.append(",\"regionType\":\"")
                .append(regionType).append('\"');
        sb.append(",\"zip\":\"")
                .append(zip).append('\"');
        sb.append(",\"createrId\":\"")
                .append(createrId).append('\"');
        sb.append(",\"creater\":\"")
                .append(creater).append('\"');
        sb.append(",\"gmtCreate\":\"")
                .append(gmtCreate).append('\"');
        sb.append(",\"modifierId\":\"")
                .append(modifierId).append('\"');
        sb.append(",\"modifier\":\"")
                .append(modifier).append('\"');
        sb.append(",\"gmtModifier\":\"")
                .append(gmtModifier).append('\"');
        sb.append(",\"isDeleted\":")
                .append(isDeleted);
        sb.append('}');
        return sb.toString();
    }
}