package com.vivascape.ehp.datatypes;

import com.vivascape.ehp.enums.GoalType;
import net.runelite.api.Skill;

public class SkillPlayerInfo
{
	private Skill skill;
	private double ehpGained;
	private double ehpRequired;
	private GoalType goalType;

	public SkillPlayerInfo(GoalType goalType, EhpCurve curve, int currentXp)
	{
		this.goalType = goalType;
		this.skill = curve.getSkill();
		int targetXp = goalType.getXpRequired();
		if (targetXp <= currentXp)
		{
			double ehpAmount = curve.ehpOf(targetXp);
			this.ehpGained = this.ehpRequired = ehpAmount;
		}
		else
		{
			this.ehpGained = curve.ehpOf(currentXp);
			this.ehpRequired = curve.ehpOf(targetXp);
		}
	}

	public String toString()
	{
		return String.format(
			"EHP Required for %s %s: %.2f, Gained: %.2f, Remaining: %.2f",
			goalType.getName(),
			skill.getName(),
			ehpRequired,
			ehpGained,
			ehpRequired - ehpGained
		);
	}
}
