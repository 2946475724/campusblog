package com.zs.campusblog.mbg.mapper;

import com.zs.campusblog.mbg.model.ArticleTag;
import com.zs.campusblog.mbg.model.ArticleTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagMapper {
    long countByExample(ArticleTagExample example);

    int deleteByExample(ArticleTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    List<ArticleTag> selectByExample(ArticleTagExample example);

    ArticleTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleTag record, @Param("example") ArticleTagExample example);

    int updateByExample(@Param("record") ArticleTag record, @Param("example") ArticleTagExample example);

    int updateByPrimaryKeySelective(ArticleTag record);

    int updateByPrimaryKey(ArticleTag record);
}