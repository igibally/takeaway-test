package com.gameofthree.server.domain.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gameofthree.server.domain.model.GameState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class GameData {

	Movement playerMovement;
	GameState gameState;

	public void printGameData() {
		Logger logger = LogManager.getLogger();
		logger.info("Added Number :: " + playerMovement.getAddedNumber() + " Result Number :: "
				+ playerMovement.getResultNumber() + " Player :: " + playerMovement.getPlayer().getUserName());
	}

}
