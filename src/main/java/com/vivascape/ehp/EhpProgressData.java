package com.vivascape.ehp;

import com.google.gson.Gson;
import com.vivascape.ehp.beans.SkillMethod;
import com.vivascape.ehp.enums.AccountType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class EhpProgressData
{
	@Inject
	private Client client;

	public static SkillMethod[] ehpRatesFromFile(AccountType type)
	{
		Gson gson = new Gson();
		String fileName = String.format("%s.json", type.getName());
		URL resource = EhpProgressData.class.getClassLoader().getResource(fileName);

		File file;
		try {
			assert resource != null;
			file = new File(resource.toURI());
		}
		catch (URISyntaxException e)
		{
			throw new RuntimeException(e);
		}

		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}

		SkillMethod[] skillMethods = gson.fromJson(reader, SkillMethod[].class);
		return skillMethods;
	}

	public Map<Skill, Integer> playerXpValues()
	{
		Map<Skill, Integer> values = new EnumMap<>(Skill.class);
		for (Skill s : Skill.values()) {
			values.put(s, client.getSkillExperience(s));
		}

		return values;
	}


}
