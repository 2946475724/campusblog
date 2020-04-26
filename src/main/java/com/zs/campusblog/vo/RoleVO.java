package com.zs.campusblog.vo;

import lombok.Data;

/**
 * @author zs
 * @date 2020/4/23
 */
@Data
public class RoleVO {
    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

}
