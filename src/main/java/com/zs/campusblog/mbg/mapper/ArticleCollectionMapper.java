package com.zs.campusblog.mbg.mapper;

import com.zs.campusblog.mbg.model.ArticleCollection;
import com.zs.campusblog.mbg.model.ArticleCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleCollectionMapper {
    long countByExample(ArticleCollectionExample example);

    int deleteByExample(ArticleCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleCollection record);

    int insertSelective(ArticleCollection record);

    List<ArticleCollection> selectByExample(ArticleCollectionExample example);

    ArticleCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleCollection record, @Param("example") ArticleCollectionExample example);

    int updateByExample(@Param("record") ArticleCollection record, @Param("example") ArticleCollectionExample example);

    int updateByPrimaryKeySelective(ArticleCollection record);

    int updateByPrimaryKey(ArticleCollection record);
}