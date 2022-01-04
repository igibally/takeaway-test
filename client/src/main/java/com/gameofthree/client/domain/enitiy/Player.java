package com.gameofthree.client.domain.enitiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompSession;

import com.gameofthree.client.domain.handler.ClientSessionHandler;
import com.gameofthree.client.domain.model.GameState;
import com.gameofthree.client.domain.model.PlayerType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Player {
	private String userName;
	private String userSessionId;
	private PlayerType playerInputType;
	private static Logger logger = LogManager.getLogger(ClientSessionHandler.class);

	protected void setPlayerName(String userMessage) throws IOException {
		String playerName = "";
		while (playerName.isEmpty()) {
			logger.info(userMessage);
			InputStreamReader inStream = new InputStreamReader(System.in);
			BufferedReader stdin = new BufferedReader(inStream);
			playerName = stdin.readLine();
		}
		this.userName = playerName;
	}

	protected void setPlayerType(String userMessage) throws IOException {
		String playerType = null;
		while (playerType == null || (!(Arrays.asList("M", "A").contains(playerType)))) {
			logger.info(userMessage);
			InputStreamReader inStream = new InputStreamReader(System.in);
			BufferedReader stdin = new BufferedReader(inStream);
			playerType = stdin.readLine();
		}
		this.playerInputType = (playerType.equals("M")) ? PlayerType.MANUAL : PlayerType.AUTOMATIC;
	}

	public void sendPlayerNumber(StompSession session, GameData gameData, String userMessage)
			throws NumberFormatException, IOException {
		Integer playerNumber = null;
		if (this.getPlayerInputType().equals(PlayerType.AUTOMATIC)) {
			playerNumber = countNumberToAdd(gameData.getPlayerMovement().getResultNumber());
		} else {
			while (playerNumber == null || !(Arrays.asList(-1, 0, 1).contains(playerNumber))) {
				try {
					playerNumber = GetPlayerNumber(userMessage);
				} catch (NumberFormatException e) {
					playerNumber = GetPlayerNumber(userMessage);
				}
			}
		}
		Movement nextMovement = Movement.builder().addedNumber(playerNumber).player(this)
				.resultNumber((gameData.getPlayerMovement().getResultNumber() + playerNumber) / 3).build();
		GameData nextGameDate = GameData.builder().gameState(gameData.getGameState()).playerMovement(nextMovement)
				.build();

		session.send("/app/sendnumber", nextGameDate);
	}

	public void sendStartingNumber(String userMessage, StompSession session) throws IOException {
		Integer resultNumber = null;

		if (this.getPlayerInputType().getValue().equals(PlayerType.AUTOMATIC.getValue())) {
			resultNumber = generateRandomNumber(100, 3);
		} else {
			while (resultNumber == null) {
				try {
					resultNumber = GetPlayerNumber(userMessage);
				} catch (NumberFormatException e) {
					resultNumber = GetPlayerNumber(userMessage);
				}
			}
		}
		GameData gamedata = CreateGameData(resultNumber);
		session.send("/app/sendnumber", gamedata);
	}

	private Integer generateRandomNumber(int max, int min) {
		Random random = new Random();
		int randomNumber = random.nextInt(max + 1 - min) + min;
		return randomNumber;
	}

	private Integer GetPlayerNumber(String userMessage) throws IOException, NumberFormatException {
		Integer playerNumber = null;
		logger.info(userMessage + " :: " + this.getUserName());
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(inStream);
		String inputStr = stdin.readLine();
		if (inputStr != null && !inputStr.isEmpty()) {
			playerNumber = Integer.parseInt(inputStr);
		}
		return playerNumber;
	}

	private GameData CreateGameData(Integer resultNumber) {
		Movement nextMovement = Movement.builder().addedNumber(0).player(this).resultNumber(resultNumber).build();
		GameData gamedata = GameData.builder().gameState(GameState.STARTED).playerMovement(nextMovement).build();
		return gamedata;
	}

	private Integer countNumberToAdd(Integer currentNumber) {
		return Arrays.asList(-1, 0, 1).stream().filter(number -> (currentNumber + number) % 3 == 0).findFirst().get();
	}

}
