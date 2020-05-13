package br.com.cadeup.blog.model;

import java.time.ZoneOffset;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleDTO {
	
	private String title;
	private String secondaryTitle;
	private String category;
	private String introduction;
	private String content;
	private Long updateTimestamp;
	
	private String name;
	private String position;
	private String resume;
	private String linkedinURL;
	private String profileImage;
	
	private ArticleDTO() {}
	
	public static ArticleDTO convertToArticleDTO(Article article) {
		return new ArticleDTO(
				article.getTitle(), 
				article.getSecondaryTitle(), 
				article.getCategory(), 
				article.getIntroduction(), 
				article.getContent(), 
				article.getUpdateTimestamp().toEpochSecond(ZoneOffset.UTC), 
				article.getUser().getName(), 
				article.getUser().getPosition(), 
				article.getUser().getResume(), 
				article.getUser().getLinkedinURL(), 
				article.getUser().getProfileImage());
	}
}
