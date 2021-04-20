package com.chenhongliang.namegenerator.mapper;

import com.chenhongliang.namegenerator.model.SingleCharacterModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingleCharacterMapper
{
	Boolean isExist(String character);

	Integer insert(SingleCharacterModel singleCharacterModel);

	Integer updateSex(List<String> characters, String sex);

	Integer updatePinyin(String character, String pinyin);
}