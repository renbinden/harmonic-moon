package io.github.lucariatias.harmonicmoon.skill;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import java.util.HashSet;
import java.util.Set;

public class SkillManager {

    private Set<Skill> skills = new HashSet<>();

    public SkillManager(HarmonicMoon harmonicMoon) {
        getSkills().add(new DouseSkill(harmonicMoon));
    }

    public Set<Skill> getSkills() {
        return skills;
    }

}
