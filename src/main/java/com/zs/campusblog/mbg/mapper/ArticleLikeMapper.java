package com.zs.campusblog.mbg.mapper;

import com.zs.campusblog.mbg.model.ArticleLike;
import com.zs.campusblog.mbg.model.ArticleLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleLikeMapper {
    long countByExample(ArticleLikeExample example);

    int deleteByExample(ArticleLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    List<ArticleLike> selectByExample(ArticleLikeExample example);

    ArticleLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

    int updateByExample(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);
}