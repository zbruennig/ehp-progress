package com.vivascape.ehp;

import com.vivascape.ehp.enums.AccountType;
import com.vivascape.ehp.enums.GoalType;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("ehpprogress")
public interface EhpProgressConfig extends Config
{
	@ConfigItem(
		keyName = "autodetect",
		name = "Autodetect Account Type",
		description = "Always show EHP rates based on your Ironman status and active membership."
	)
	default boolean autodetect() {
		return true;
	}

	@ConfigItem(
		keyName = "accountType",
		name = "Account Type",
		description = "Manually Set Account Type to use EHP rates for."
	)
	default AccountType accountType() {
		return AccountType.MAIN;
	}

	@ConfigItem(
		keyName = "goalType",
		name = "Goal Type",
		description = "Experience Goal to show EHP for."
	)
	default GoalType goalType() {
		return GoalType.MAX;
	}

	@ConfigItem(
		keyName = "showCompletedSkills",
		name = "Show Completed Skills",
		description = "Show EHP bars for skills where you've already met the goal."
	)
	default boolean showCompletedSkills() {
		return true;
	}
}
