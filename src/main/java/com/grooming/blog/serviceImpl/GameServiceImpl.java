package com.grooming.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grooming.blog.DTO.GameDTO;
import com.grooming.blog.Entity.Game;
import com.grooming.blog.Exceptions.ResourceNotFoundException;
import com.grooming.blog.Repo.GameRepo;
import com.grooming.blog.Services.GameService;
import com.grooming.blog.utils.StandardApiResponseHandler;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	GameRepo gameRepo;

	@Override
	public GameDTO createGame(GameDTO gameDTO) {
		Game game = modelMapper.map(gameDTO, Game.class);
		Game savedGame = gameRepo.save(game);
		return modelMapper.map(savedGame, GameDTO.class);
	}

	@Override
	public GameDTO getGame(Integer gameId) {
		Game foundGame = gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game", "GameId", gameId));
		return modelMapper.map(foundGame, GameDTO.class);
	}

	@Override
	public GameDTO updateGame(Integer gameId, GameDTO gameDTO) {
		Game foundGame = gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game", "GameId", gameId));
		foundGame.setGameName(gameDTO.getGameName());
		Game savedGame = gameRepo.save(foundGame);
		return modelMapper.map(savedGame, GameDTO.class);
	}

	@Override
	public List<GameDTO> getallGames() {
		List<Game> allGames = gameRepo.findAll();
		List<GameDTO> listofGameDTOs = allGames.stream().map(eachGame -> modelMapper.map(eachGame, GameDTO.class))
				.collect(Collectors.toList());
		return listofGameDTOs;
	}

	@Override
	public StandardApiResponseHandler deleteGame(Integer gameId) {
		Game foundGame = gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game", "GameId", gameId));
		gameRepo.delete(foundGame);
		return new StandardApiResponseHandler("Game Deleted Successfully!", true);
	}

}
