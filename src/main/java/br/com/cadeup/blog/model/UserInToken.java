package br.com.cadeup.blog.model;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInToken {

	private Long id;
	private String name;
	
	private UserInToken() {}
	
	public static UserInToken getInstance(User user) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(user, UserInToken.class);
	}
	
}
