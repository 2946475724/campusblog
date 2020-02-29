package com.zs.campusblog.mbg.mapper;

import com.zs.campusblog.mbg.model.RoleResourceRelation;
import com.zs.campusblog.mbg.model.RoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourceRelationMapper {
    long countByExample(RoleResourceRelationExample example);

    int deleteByExample(RoleResourceRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResourceRelation record);

    int insertSelective(RoleResourceRelation record);

    List<RoleResourceRelation> selectByExample(RoleResourceRelationExample example);

    RoleResourceRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleResourceRelation record, @Param("example") RoleResourceRelationExample example);

    int updateByExample(@Param("record") RoleResourceRelation record, @Param("example") RoleResourceRelationExample example);

    int updateByPrimaryKeySelective(RoleResourceRelation record);

    int updateByPrimaryKey(RoleResourceRelation record);
}