package com.gameofthree.client.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerTypeHelper {

	public static List<String> getStringValues() {
		List<String> enumNames = new ArrayList<>(PlayerType.values().length);
		Stream.of(PlayerType.values()).forEach(type -> enumNames.add(type.value));
		return enumNames;
	}
}
