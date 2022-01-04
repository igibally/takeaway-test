package com.gameofthree.client.starter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.gameofthree.client.domain.exception.GameOfThreeException;
import com.gameofthree.client.domain.handler.ClientSessionHandler;

@Component
public class WebClientStarter implements CommandLineRunner {

	private Logger logger = LogManager.getLogger(ClientSessionHandler.class);

	@Value("${server.app.url}")
	private String serverURL;
	@Value("${server.start.error.message}")
	private String errorMessage;

	@Override
	public void run(String... args) throws Exception {
		try {
			logger.debug("the server URL:" + serverURL);
			WebSocketClient client = new StandardWebSocketClient();
			WebSocketStompClient stompClient = new WebSocketStompClient(client);
			stompClient.setMessageConverter(new MappingJackson2MessageConverter());
			StompSessionHandler sessionHandler = new ClientSessionHandler();
			stompClient.connect(serverURL, sessionHandler).get();
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			throw new GameOfThreeException(errorMessage);
		}

	}

}
