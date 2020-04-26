package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkDAO {

    /**
     * 获取友情链接列表
     */
    List<Link> getLinkList(@Param("keyword") String keyword);

    /**
     * 删除友情链接
     */
    int deleteLinkById(Integer id);
}
