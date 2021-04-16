package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingleCharacterMapper
{

	List<SingleCharacterModel> findAll();

}