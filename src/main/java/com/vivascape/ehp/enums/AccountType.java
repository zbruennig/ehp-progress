package com.vivascape.ehp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AccountType
{
	MAIN("main", "Main"),
	IRONMAN("ironman", "Ironman"),
	ULTIMATE("ultimate", "Ultimate Ironman"),
	F2P("f2p", "Free To Play"),
	LVL3("lvl3", "Level 3");

	@Getter
	private final String name;

	@Getter
	private final String display;

	@Override
	public String toString() {
		return display;
	}
}
