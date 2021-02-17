package com.my.spring.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class YoutubeDto {
	private String title;
	private String thumbnailPath;
	private String videoId;
	
	@Builder(toBuilder = true)
	public YoutubeDto(String title, String thumbnailPath, String videoId) {
		this.title = title;
		this.thumbnailPath = thumbnailPath;
		this.videoId = videoId;
	}

}
