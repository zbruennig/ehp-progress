package com.vivascape.ehp.beans;

import lombok.Value;

@Value
public class SkillMethod
{
	String skill;
	Method[] methods;
	Bonus[] bonuses;
}
