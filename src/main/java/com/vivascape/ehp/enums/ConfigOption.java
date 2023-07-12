package com.vivascape.ehp.enums;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConfigOption
{
	AUTODETECT("autodetect"),
	ACCOUNT_TYPE("accountType"),
	GOAL_TYPE("goalType"),
	SHOW_COMPLETED("showCompletedSkills");

	@Getter
	private final String configVariableName;

	private static final Map<String, ConfigOption> CONFIG_MAP;

	static
	{
		ImmutableMap.Builder<String, ConfigOption> builder = new ImmutableMap.Builder<>();
		for (ConfigOption config : values())
		{
			builder.put(config.getConfigVariableName(), config);
		}
		CONFIG_MAP = builder.build();
	}

	public static ConfigOption getConfig(String varName)
	{
		return CONFIG_MAP.getOrDefault(varName, null);
	}
}
