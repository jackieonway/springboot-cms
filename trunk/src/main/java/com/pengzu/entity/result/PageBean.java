package com.pengzu.entity.result;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/3.
 */
public class PageBean implements Serializable {

    /**
     * 每页大小
     */
    private Integer pageSize = Integer.valueOf(10);

    /**
     * 当前页数
     */
    private Integer pageNum = Integer.valueOf(1);

    /**
     * 获取每页大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页数
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * 设置设置当前页数
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取数据库起始位置
     */
    public Integer getPageStart() {
        return (this.pageNum - 1) * this.pageSize;
    }
}
