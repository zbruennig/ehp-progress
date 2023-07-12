package com.vivascape.ehp;

import com.google.inject.Provides;
import com.vivascape.ehp.beans.SkillMethod;
import com.vivascape.ehp.datatypes.EhpCurve;
import com.vivascape.ehp.datatypes.SkillPlayerInfo;
import com.vivascape.ehp.enums.ConfigOption;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
	name = "EHP Progress",
	description = "Track your skilling progress in Efficient Hours Played.",
	tags = {"ehp", "progress", "experience", "calculator", "efficient", "hours", "played"}

)
public class EhpProgressPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private EhpProgressConfig config;

	@Inject
	private EhpProgressData data;

	@Inject
	private EhpProgressState state;

	@Getter
	private SkillMethod[] rates;

	private EhpProgressPanel panel;
	private NavigationButton sidebarButton;

	@Override
	protected void startUp() throws Exception
	{
		final BufferedImage icon = ImageUtil.getResourceStreamFromClass(EhpProgressPlugin.class, "ehp_icon.png");
		panel = new EhpProgressPanel();

		sidebarButton = NavigationButton.builder()
			.tooltip("EHP Progress")
			.icon(icon)
			.priority(10)
			.panel(panel)
			.build();

		clientToolbar.addNavigation(sidebarButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(sidebarButton);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			state.setXpValues(data.playerXpValues());
			SkillMethod[] ehpMethods = EhpProgressData.ehpRatesFromFile(config.accountType());
			state.setEhpValues(EhpCurve.toCurves(ehpMethods));
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("ehpprogress")) {
			return;
		}
		ConfigOption configOption = ConfigOption.getConfig(event.getKey());
		switch (configOption) {
			case AUTODETECT:
				break;
			case GOAL_TYPE:
				break;
			case ACCOUNT_TYPE:
				SkillMethod[] ehpMethods = EhpProgressData.ehpRatesFromFile(config.accountType());
				state.setXpValues(data.playerXpValues());
				state.setEhpValues(EhpCurve.toCurves(ehpMethods));
				break;
			case SHOW_COMPLETED:
				break;
		}
		update();
	}

	private void update()
	{
		for (Skill skill: Skill.values()) {
			EhpCurve curve = state.getEhpValues().get(skill);
			Integer xp = state.getXpValues().get(skill);
			if (curve == null || xp == null) {
				continue;
			}

			SkillPlayerInfo info = new SkillPlayerInfo(
				config.goalType(),
				state.getEhpValues().get(skill),
				state.getXpValues().get(skill)
			);
			log.info(info.toString());
		}
	}

	@Provides
	EhpProgressConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EhpProgressConfig.class);
	}
}
