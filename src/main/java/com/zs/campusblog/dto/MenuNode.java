package com.zs.campusblog.dto;

import com.zs.campusblog.mbg.model.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/28
 * 后台菜单节点封装
 */
@Data
public class MenuNode extends Menu {
    private List<MenuNode> children;
}
