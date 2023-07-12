package com.vivascape.ehp.enums;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Skill;

@AllArgsConstructor
public enum SkillMap
{
	ATTACK("attack", Skill.ATTACK),
	DEFENCE("defence", Skill.DEFENCE),
	STRENGTH("strength", Skill.STRENGTH),
	HITPOINTS("hitpoints", Skill.HITPOINTS),
	RANGED("ranged", Skill.RANGED),
	PRAYER("prayer", Skill.PRAYER),
	MAGIC("magic", Skill.MAGIC),
	COOKING("cooking", Skill.COOKING),
	WOODCUTTING("woodcutting", Skill.WOODCUTTING),
	FLETCHING("fletching", Skill.FLETCHING),
	FISHING("fishing", Skill.FISHING),
	FIREMAKING("firemaking", Skill.FIREMAKING),
	CRAFTING("crafting", Skill.CRAFTING),
	SMITHING("smithing", Skill.SMITHING),
	MINING("mining", Skill.MINING),
	HERBLORE("herblore", Skill.HERBLORE),
	AGILITY("agility", Skill.AGILITY),
	THIEVING("thieving", Skill.THIEVING),
	SLAYER("slayer", Skill.SLAYER),
	FARMING("farming", Skill.FARMING),
	RUNECRAFTING("runecrafting", Skill.RUNECRAFT),
	// Just in case
	RUNECRAFT("runecraft", Skill.RUNECRAFT),
	HUNTER("hunter", Skill.HUNTER),
	CONSTRUCTION("construction", Skill.CONSTRUCTION);

	@Getter
	private String name;

	@Getter
	private Skill skill;

	private static final Map<String, Skill> SKILL_MAP;

	static
	{
		ImmutableMap.Builder<String, Skill> builder = new ImmutableMap.Builder<>();
		for (SkillMap mapping : values())
		{
			builder.put(mapping.getName(), mapping.getSkill());
		}
		SKILL_MAP = builder.build();
	}

	public static Skill getSkillEnum(String skillName) {
		return SKILL_MAP.getOrDefault(skillName, null);
	}
}
