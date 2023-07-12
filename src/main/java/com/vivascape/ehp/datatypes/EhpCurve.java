package com.vivascape.ehp.datatypes;

import com.vivascape.ehp.beans.Method;
import com.vivascape.ehp.beans.SkillMethod;
import com.vivascape.ehp.enums.SkillMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import net.runelite.api.Skill;

public class EhpCurve
{
	@Getter
	private Skill skill;
	private List<Method> orderedMethods;

	public EhpCurve(SkillMethod skillMethod)
	{
		skill = SkillMap.getSkillEnum(skillMethod.getSkill());

		orderedMethods = Arrays.asList(skillMethod.getMethods());
		orderedMethods.sort(Comparator.comparingInt(Method::getStartExp));
	}

	public double ehpOf(int xp) {
		double computedEhp = 0;
		for (int i = 0; i < orderedMethods.size(); i++) {
			int rate = orderedMethods.get(i).getRate();
			int startExp = orderedMethods.get(i).getStartExp();
			if (i == orderedMethods.size() - 1) {
				// If final method calculate rest of ehp at this rate
				computedEhp += (double) (xp - startExp) / rate;
				break;
			}

			int cutoff = orderedMethods.get(i+1).getStartExp();
			if (cutoff >= xp) {
				// We use this rate for the rest of the computation
				computedEhp += (double) (xp - startExp) / rate;
				break;
			}

			// For the entirety of this being the efficient rate, compute xp
			int xpToGain = cutoff - startExp;
			computedEhp += (double) xpToGain / rate;
		}

		return computedEhp;
	}

	public static Map<Skill, EhpCurve> toCurves(SkillMethod[] skillMethods)
	{
		Map<Skill, EhpCurve> curves = new EnumMap<>(Skill.class);
		for (SkillMethod method : skillMethods)
		{
			EhpCurve curve = new EhpCurve(method);
			curves.put(curve.getSkill(), curve);
		}
		return curves;
	}
}
