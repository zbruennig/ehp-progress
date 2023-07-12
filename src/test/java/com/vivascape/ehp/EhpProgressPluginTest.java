package com.vivascape.ehp;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EhpProgressPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EhpProgressPlugin.class);
		RuneLite.main(args);
	}
}