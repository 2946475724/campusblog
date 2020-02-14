package com.zs.campusblog.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zs
 * @date 2020/1/9
 */

public class Tag implements Serializable {
    private Integer id;
    private String name;
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.createTime = createTime == null ? timestamp : createTime;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
