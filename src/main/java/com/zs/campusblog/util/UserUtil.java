package com.zs.campusblog.util;

import com.zs.campusblog.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zs
 * @date 2020/1/4
 */
public class UserUtil {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
