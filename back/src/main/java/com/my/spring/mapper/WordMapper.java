package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.WordDto;
import com.my.spring.domain.WordDtoForCloud;
import com.my.spring.domain.chains.WordChain;
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
	
	public List<NounDto> getTagWordsByTopic(@Param("game") String game);
	public List<NounDto> getChannelWordsByTopic(@Param("game") String game);
	public List<NounDto> getVideoWordsByTopic(@Param("game") String game);
	public List<NounDto> getWordsByGame(@Param("game") String game);
	
	public int checkWordUnique(@Param("word") String word);
	public void setWordUnique(@Param("word") String word);
	
	public WordChain getWordChain(@Param("item") WordChain item);
	public void setWordChain(@Param("item") WordChain item);
	public void updateWordChain(@Param("item") WordChain item);
	
	public List<WordChain> buildWordChainByTag(@Param("videoId") String videoId);
	public List<WordChain> buildWordChainByVideo(@Param("videoId") String videoId);
	public List<WordChain> buildWordChainByChannel(@Param("channelId") String channelId);
	
	public void setWordChainsById();
	public List<WordDtoForCloud> getWordDtoByGame(@Param("title") String title);
}
