package com.grooming.blog.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {
	private int GameId;
	@NotNull(message = "Game Name cannot be Empty")
	private String GameName;

}
