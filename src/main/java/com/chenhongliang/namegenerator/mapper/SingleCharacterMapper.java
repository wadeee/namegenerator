package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingleCharacterMapper
{

	List<SingleCharacterModel> findAll();

	Boolean isExist(String character);

	int insert(SingleCharacterModel singleCharacterModel);

	int updateSex(List<String> characters, String sex);
}