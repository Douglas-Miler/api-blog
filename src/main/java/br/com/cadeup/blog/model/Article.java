package br.com.cadeup.blog.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_ARTIGO")
public class Article {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITULO")
	private String title;
	
	@Column(name = "SUBTITULO")
	private String secondaryTitle;
	
	@Column(name = "CATEGORIA")
	private String category;
	
	@Column(name = "INTRODUCAO")
	private String introduction;
	
	@Lob
	@Column(name = "CONTEUDO", columnDefinition = "varchar(max)")
	private String content;
	
	@Column(name = "DATA_CRIACAO", columnDefinition = "DATE")
	private LocalDate creationDate;

	@ManyToOne()
	@JoinColumn(name = "USUARIO_ID")
	private User user;
	
	@Lob()
	@Column(name = "IMAGEM", columnDefinition = "varchar(max)")
	private String image;
	
	public Article(ArticleForm articleForm) {
		this.category = articleForm.getCategory();
		this.content = articleForm.getContent();
		this.creationDate = articleForm.getCreationDate();
		this.image = articleForm.getImage();
		this.introduction = articleForm.getIntroduction();
		this.secondaryTitle = articleForm.getSecondaryTitle();
		this.title = articleForm.getTitle();
		User user = new User();
		user.setId(articleForm.getUserId());
		this.user = user;
	}
}
