package com.zs.campusblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zs
 * @date 2019/12/29
 */
@Data
public class Menu implements Serializable {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String icon;
    private MenuMeta meta;
    private Integer parentId;
    private Boolean enabled;
    private List<Role> roles;
    private List<Menu> children;
}
