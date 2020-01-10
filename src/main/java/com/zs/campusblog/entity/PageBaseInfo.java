package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zs
 * @date 2020/1/9
 */

@Data
public class PageBaseInfo implements Serializable {
    /**
     * 当前页数
     */
    private Integer pageNum;
    /**
     * 找那个页数
     */
    private Integer totalPages;
    /**
     * 总记录数
     */
    private Integer total;

    public PageBaseInfo() {

    }

    public PageBaseInfo(Integer pageNum, Integer totalPages, Integer total) {
        this.pageNum = pageNum;
        this.totalPages = totalPages;
        this.total = total;
    }

}
