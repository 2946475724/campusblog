package com.zs.campusblog.mbg.mapper;

import com.zs.campusblog.mbg.model.UserPermissionRelation;
import com.zs.campusblog.mbg.model.UserPermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionRelationMapper {
    long countByExample(UserPermissionRelationExample example);

    int deleteByExample(UserPermissionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPermissionRelation record);

    int insertSelective(UserPermissionRelation record);

    List<UserPermissionRelation> selectByExample(UserPermissionRelationExample example);

    UserPermissionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPermissionRelation record, @Param("example") UserPermissionRelationExample example);

    int updateByExample(@Param("record") UserPermissionRelation record, @Param("example") UserPermissionRelationExample example);

    int updateByPrimaryKeySelective(UserPermissionRelation record);

    int updateByPrimaryKey(UserPermissionRelation record);
}