package com.vivascape.ehp.beans;

import lombok.Value;

@Value
public class Bonus
{
	String originSkill;
	String bonusSkill;
	int startExp;
	int endExp;
	int maxBonus;
	boolean end;
	double ratio;
}
