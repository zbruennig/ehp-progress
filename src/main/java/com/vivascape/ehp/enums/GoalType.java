package com.vivascape.ehp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GoalType
{
	MAX("Level 99", 13034431),
	TWO_HUNDRED_MIL("200M XP", 200000000);

	@Getter
	private final String name;

	@Getter
	private final Integer xpRequired;

	@Override
	public String toString() {
		return name;
	}
}
