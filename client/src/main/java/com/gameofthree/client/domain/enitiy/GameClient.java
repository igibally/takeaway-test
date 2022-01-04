package com.gameofthree.client.domain.enitiy;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

import com.gameofthree.client.domain.exception.GameOfThreeException;
import com.gameofthree.client.domain.model.GameState;

public class GameClient {
	private Logger logger = LogManager.getLogger(GameClient.class);
	@Value("${server.start.error.message}")
	private String errorMessage;
	private Player player;
	private Thread startingThread;
	private StompSession session;

	public GameClient(StompSession session) {
		this.session = session;
	}

	public StompSession getSession() {
		return session;
	}

	private void createPlayer() throws IOException {
		this.player = new Player();
		player.setUserSessionId(session.getSessionId());
		player.setPlayerName("Enter your Name : ");
		player.setPlayerType("Enter your Type : {Manual:(M) | Automatic:(A)}");
	}

	private void createStartingThread() {
		logger.info(player.getUserName() + " " + "Has Joined the Game");
		startingThread = new Thread(new StartingThread(this.player, session));
		startingThread.start();
	}

	private void StopStartingThread() {
		if (startingThread.isAlive()) {
			startingThread.stop();
		}

	}

	private Player getPlayer() {
		return player;
	}

	public void startGame() throws IOException {
		logger.info(" ======= Game Started : =======");
		createPlayer();
		session.subscribe("/topic/movements", new StompFrameHandler() {
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				try {
					StopStartingThread();
					GameData gameData = (GameData) payload;
					logger.info("Last Player Move :: ");
					gameData.printGameData();
					SetPlayerTurn(headers, gameData);
				} catch (GameOfThreeException e) {
					logger.log(Level.ERROR, e.getMessage(), e);
					logger.log(Level.INFO, errorMessage);
				}
			}

			@Override
			public Type getPayloadType(StompHeaders headers) {
				return GameData.class;
			}
		});
		createStartingThread();
	}

	private void SetPlayerTurn(StompHeaders headers, GameData gameData) {
		if (!gameData.getGameState().equals(GameState.ENDED)) {
			if (!gameData.getPlayerMovement().getPlayer().getUserSessionId().equals(getSession().getSessionId())) {
				try {
					getPlayer().sendPlayerNumber(getSession(), gameData, "Play A Number between {-1,0,1}");
				} catch (Exception e) {
					throw new GameOfThreeException(e);
				}
			} else {
				logger.info("Waiting for other Player to play ... ");
			}
		} else {
			logger.info("Game Ended : Result is :  " + gameData.getPlayerMovement().getResultNumber() + " Winner is : "
					+ gameData.getPlayerMovement().getPlayer().getUserName());
			session.disconnect(headers);
		}
	}

}
