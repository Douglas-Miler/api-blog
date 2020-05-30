package br.com.cadeup.blog.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleForm {

	private String title;
	private String secondaryTitle;
	private String category;
	private String introduction;
	private String content;
	private LocalDate creationDate;
	private Long userId;
	
}
