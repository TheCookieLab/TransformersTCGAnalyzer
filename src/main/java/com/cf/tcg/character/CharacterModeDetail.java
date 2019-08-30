package com.cf.tcg.character;

import java.util.List;

public class CharacterModeDetail {
    private final Integer attack;
    private final Integer armor;
    private final Integer health;
    private final Integer bold;
    private final Integer tough;
    private final Integer focus;
    private final String text;
    private final List<String> keywords;

    public CharacterModeDetail(Integer attack, Integer armor, Integer health, Integer bold, Integer tough, Integer focus, String text, List<String> keywords) {
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.bold = bold;
        this.tough = tough;
        this.focus = focus;
        this.text = text;
        this.keywords = keywords;
    }
}
