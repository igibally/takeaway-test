package com.gameofthree.client.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GameOfThreeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;
	Throwable cause;

	public GameOfThreeException(String message, Throwable cause) {
		super(message, cause);

	}

	public GameOfThreeException(String message) {
		super(message);
	}

	public GameOfThreeException(Throwable cause) {
		super(cause);

	}

}
