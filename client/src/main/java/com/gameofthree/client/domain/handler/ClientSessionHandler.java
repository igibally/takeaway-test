package com.gameofthree.client.domain.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.gameofthree.client.domain.enitiy.GameClient;

public class ClientSessionHandler extends StompSessionHandlerAdapter {

	private Logger logger = LogManager.getLogger(ClientSessionHandler.class);
	@Value("${server.start.error.message}")
	private String errorMessage;

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		try {
			GameClient gameClient = new GameClient(session);
			gameClient.startGame();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR, e.getMessage(), e);
			logger.log(Level.INFO, errorMessage);
		}
	}
}
