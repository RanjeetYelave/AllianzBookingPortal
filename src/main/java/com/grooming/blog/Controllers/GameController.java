package com.grooming.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grooming.blog.DTO.GameDTO;
import com.grooming.blog.serviceImpl.GameServiceImpl;
import com.grooming.blog.utils.StandardApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
	@Autowired
	GameServiceImpl gameServiceImpl;

	@GetMapping("/getgame/{Id}")
	ResponseEntity<GameDTO> findGameById(@PathVariable Integer Id) {
		GameDTO game = gameServiceImpl.getGame(Id);
		return new ResponseEntity<GameDTO>(game, HttpStatus.OK);
	}

	@GetMapping("/getgame")
	ResponseEntity<List<GameDTO>> findAllGames() {
		List<GameDTO> getallGames = gameServiceImpl.getallGames();
		return new ResponseEntity<List<GameDTO>>(getallGames, HttpStatus.OK);
	}

	@PutMapping("/updategame/{Id}")
	ResponseEntity<GameDTO> updateGame(@PathVariable Integer Id, @Valid @RequestBody GameDTO gameDTO) {
		GameDTO updatedGame = gameServiceImpl.updateGame(Id, gameDTO);
		return new ResponseEntity<GameDTO>(updatedGame, HttpStatus.OK);
	}

	@PostMapping("/creategame")
	ResponseEntity<GameDTO> createGame(@Valid @RequestBody GameDTO gameDTO) {
		GameDTO createdGameDTO = gameServiceImpl.createGame(gameDTO);
		return new ResponseEntity<GameDTO>(createdGameDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{Id}")
	ResponseEntity<StandardApiResponseHandler> deleteGame(@PathVariable Integer Id) {
		StandardApiResponseHandler deletedGameResponse = gameServiceImpl.deleteGame(Id);
		return new ResponseEntity<StandardApiResponseHandler>(deletedGameResponse, HttpStatus.OK);
	}
}
