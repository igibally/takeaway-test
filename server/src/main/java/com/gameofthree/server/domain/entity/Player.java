package com.gameofthree.server.domain.entity;

import com.gameofthree.server.domain.model.PlayerType;

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
}
