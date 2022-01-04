package com.gameofthree.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.gameofthree.server.domain.entity.GameData;
import com.gameofthree.server.domain.model.GameState;

@Controller
public class GameOfThreeController {

	@MessageMapping("/sendnumber")
	@SendTo("/topic/movements")
	public GameData sendNumber(@Payload GameData gamedata) {
		gamedata.printGameData();
		if (gamedata.getPlayerMovement().getResultNumber() == 1)
			gamedata.setGameState(GameState.ENDED);
		return gamedata;
	}

}
