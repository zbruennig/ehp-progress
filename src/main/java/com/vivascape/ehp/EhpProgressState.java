package com.vivascape.ehp;

import com.vivascape.ehp.datatypes.EhpCurve;
import java.util.Map;
import lombok.Data;
import net.runelite.api.Skill;

@Data
public class EhpProgressState
{
	Map<Skill, Integer> xpValues;
	Map<Skill, EhpCurve> ehpValues;
}
