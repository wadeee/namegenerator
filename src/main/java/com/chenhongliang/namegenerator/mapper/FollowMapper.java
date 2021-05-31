package com.chenhongliang.namegenerator.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
    Integer insert(Integer year, Integer month, String name);
    Integer update(Integer year, Integer month, String name);
    Integer getCount(Integer year, Integer month, String name);
}