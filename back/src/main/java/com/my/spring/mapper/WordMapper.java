package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.WordDto;
import com.my.spring.domain.words.NounDto;

public interface WordMapper {
	public List<WordDto> getWordFromTag(@Param("item") WordDto item);
	public void setWordFromTag(@Param("item") WordDto item);
	public void updateWordFromTag(@Param("item") WordDto item);
	public int checkCompleteForTag(@Param("id") String id);

	public List<WordDto> getWordFromChannel(@Param("item") WordDto item);
	public void setWordFromChannel(@Param("item") WordDto item);
	public void updateWordFromChannel(@Param("item") WordDto item);
	public int checkCompleteForChannel(@Param("id") String id);

	public List<WordDto> getWordFromVideo(@Param("item") WordDto item);
	public void setWordFromVideo(@Param("item") WordDto item);
	public void updateWordFromVideo(@Param("item") WordDto item);
	public int checkCompleteForVideo(@Param("id") String id);
	
	public List<NounDto> getTagWordsByTopic(@Param("topic") String topic);
	public List<NounDto> getChannelWordsByTopic(@Param("topic") String topic);
	public List<NounDto> getVideoWordsByTopic(@Param("topic") String topic);
}
