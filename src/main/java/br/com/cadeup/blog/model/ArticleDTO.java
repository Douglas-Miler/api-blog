package br.com.cadeup.blog.model;

import java.time.LocalDate;

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
	private LocalDate creationDate;
	
	private String name;
	private String position;
	private String resume;
	private String linkedInURL;
	private String profileImage;
	
	@SuppressWarnings("unused")
	private ArticleDTO() {}
	
	public static ArticleDTO convertToArticleDTO(Article article) {
		return new ArticleDTO(
				article.getTitle(), 
				article.getSecondaryTitle(), 
				article.getCategory(), 
				article.getIntroduction(), 
				article.getContent(),
				article.getCreationDate(),
				article.getUser().getName(), 
				article.getUser().getPosition(), 
				article.getUser().getResume(), 
				article.getUser().getLinkedInURL(), 
				article.getUser().getProfileImage());
	}
}
