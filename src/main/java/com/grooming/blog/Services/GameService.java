package com.grooming.blog.Services;

import java.util.List;

import com.grooming.blog.DTO.GameDTO;
import com.grooming.blog.utils.StandardApiResponseHandler;

public interface GameService {
	GameDTO createGame(GameDTO gameDTO);

	GameDTO getGame(Integer gameId);

	GameDTO updateGame(Integer gameId, GameDTO gameDTO);

	List<GameDTO> getallGames();

	StandardApiResponseHandler deleteGame(Integer gameId);
}
