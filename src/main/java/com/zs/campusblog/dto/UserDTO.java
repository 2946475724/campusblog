package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.User;
import lombok.Data;

/**
 * @author zs
 * @date 2020/4/24
 */
@Data
public class UserDTO extends User {
    private String roleName;
    private Integer roleId;

}
