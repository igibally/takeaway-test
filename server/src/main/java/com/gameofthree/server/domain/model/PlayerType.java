package com.gameofthree.server.domain.model;

import lombok.Getter;

@Getter
public enum PlayerType {
	AUTOMATIC("A"), MANUAL("M");
	String value;

	private PlayerType(String value) {
		this.value = value;
	}

}
