package com.zs.campusblog.dao;

import com.zs.campusblog.mbg.model.Link;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据排序获取前五条友情链接
     */
    @Select("select * from link where sort is not null order by sort desc limit 0,5")
    List<Link> getLinkListBySort();
}
