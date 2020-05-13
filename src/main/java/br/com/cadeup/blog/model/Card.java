package br.com.cadeup.blog.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {

	private Long id;
	private String title;
	private String introduction;
	private String image;
	private String userName;
	private String userPosition;

	public static Card convertToCard(Article article) {
		return new Card(
					article.getId(),
					article.getTitle(),
					article.getIntroduction(),
					article.getImage(),
					article.getUser().getName(),
					article.getUser().getPosition()
				);
	}
}
