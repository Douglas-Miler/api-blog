package br.com.cadeup.blog.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleForm {

	private String title;
	private String secondaryTitle;
	private String category;
	private String introduction;
	private String content;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate creationDate;
	
	private Long userId;
	private String image;
	
}
