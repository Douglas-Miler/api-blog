package br.com.cadeup.blog.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleForm {

	private String title;
	private String secondaryTitle;
	private String category;
	private String introduction;
	private String content;
	private LocalDate creationDate;
	private Long userId;
	private String image;
	
}
