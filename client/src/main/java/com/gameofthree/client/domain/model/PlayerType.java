package com.gameofthree.client.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum PlayerType {
	AUTOMATIC("A"), MANUAL("M");
	String value;

	private PlayerType(String value) {
		this.value = value;
	}

}
