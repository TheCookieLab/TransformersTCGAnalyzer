package com.cf.tcg.model.battle.card;

import com.cf.tcg.model.Pip;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author David
 */
public class BattleCard {

    public final String name;
    public final List<Pip> pips;

    public BattleCard() {
        this(null, new ArrayList<>());
    }

    public BattleCard(String name, List<Pip> pips) {
        this.name = name;
        this.pips = pips;
    }

    public BattleCard(String name, Pip... pips) {
        this(name, Arrays.asList(pips));
    }

    public BattleCard(Pip... pips) {
        this(null, Arrays.asList(pips));
    }

    public Integer getPipCount(Pip pip) {
        int count = 0;
        for (Pip p : this.pips) {
            if (p == pip) {
                count++;
            }
        }

        return count;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + Objects.hashCode(this.pips);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BattleCard other = (BattleCard) obj;

        if (!Objects.equals(this.pips, other.pips)) {
            return false;
        }

        return Objects.equals(this.name, other.name);

    }

    public boolean equalsIgnoreName(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BattleCard other = (BattleCard) obj;

        if (!Objects.equals(this.pips, other.pips)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // Generic Pip Battle Cards
    public static BattleCard SINGLE_ORANGE = new BattleCard(Pip.ORANGE);
    public static BattleCard DOUBLE_ORANGE = new BattleCard(Pip.ORANGE, Pip.ORANGE);

    public static BattleCard SINGLE_BLUE = new BattleCard(Pip.BLUE);
    public static BattleCard DOUBLE_BLUE = new BattleCard(Pip.BLUE, Pip.BLUE);

    public static BattleCard BLUE_ORANGE = new BattleCard(Pip.BLUE, Pip.ORANGE);
    public static BattleCard BLUE_ORANGE_WHITE = new BattleCard(Pip.BLUE, Pip.ORANGE, Pip.WHITE);

    public static BattleCard SINGLE_WHITE = new BattleCard(Pip.WHITE);

    public static BattleCard SINGLE_BLACK = new BattleCard(Pip.BLACK);
    public static BattleCard DOUBLE_BLACK = new BattleCard(Pip.BLACK, Pip.BLACK);
    public static BattleCard BLACK_ORANGE = new BattleCard(Pip.BLACK, Pip.ORANGE);
    public static BattleCard BLACK_BLUE = new BattleCard(Pip.BLACK, Pip.BLUE);

    public static BattleCard SINGLE_GREEN = new BattleCard(Pip.GREEN);
    public static BattleCard WHITE_GREEN = new BattleCard(Pip.WHITE, Pip.GREEN);
    public static BattleCard BLUE_GREEN = new BattleCard(Pip.BLUE, Pip.GREEN);
    public static BattleCard ORANGE_GREEN = new BattleCard(Pip.ORANGE, Pip.GREEN);
    public static BattleCard BLACK_GREEN = new BattleCard(Pip.BLACK, Pip.GREEN);

    public static BattleCard BLANK = new BattleCard();

    // Named Battle Cards

    // Actions
    public static BattleCard CONFIDENCE = new BattleCard("Confidence", Pip.ORANGE);

    public static BattleCard INSPIRING_LEADERSHIP = new BattleCard("Inspiring Leadership", Pip.BLUE);
    public static BattleCard I_STILL_FUNCTION = new BattleCard("I Still Function!");

    public static BattleCard MARKSMANSHIP = new BattleCard("Marksmanship", Pip.BLUE);
    public static BattleCard ONE_SHALL_STAND_ONE_SHALL_FALL = new BattleCard("One Shall Stand, One Shall Fall");
    public static BattleCard PEACE_THROUGH_TYRANNY = new BattleCard("Peace Through Tyranny", Pip.ORANGE, Pip.ORANGE);
    public static BattleCard PRESS_THE_ADVANTAGE = new BattleCard("Press The Advantage", Pip.ORANGE, Pip.GREEN);
    public static BattleCard RAMMING_SPEED = new BattleCard("Ramming Speed", Pip.ORANGE);
    public static BattleCard RECKLESS_CHARGE = new BattleCard("Reckless Charge", Pip.ORANGE);
    public static BattleCard SECURITY_CHECKPOINT = new BattleCard("Security Checkpoint", Pip.BLUE, Pip.BLUE);
    public static BattleCard SMELT = new BattleCard("Smelt", Pip.BLUE, Pip.GREEN);
    public static BattleCard START_YOUR_ENGINES = new BattleCard("Start Your Engines", Pip.BLUE);
    public static BattleCard TECH_RESEARCH = new BattleCard("Tech Research", Pip.GREEN);
    public static BattleCard THE_BIGGER_THEY_ARE = new BattleCard("The Bigger They Are", Pip.BLUE);
    public static BattleCard VAPORIZE = new BattleCard("Vaporize", Pip.BLUE);

    public static BattleCard QUARTERMASTER = new BattleCard("Quartermaster", Pip.WHITE);

    // Upgrade - Weapons
    public static BattleCard ARMED_HOVERCRAFT = new BattleCard("Armed Hovercraft", Pip.BLUE);
    public static BattleCard ERRATIC_LIGHTNING = new BattleCard("Erratic Lightning", Pip.ORANGE);
    public static BattleCard GRENADE_LAUNCHER = new BattleCard("Grenade Launcher", Pip.ORANGE);
    public static BattleCard HANDHELD_BLASTER = new BattleCard("Handheld Blaster", Pip.BLUE, Pip.BLUE);

    public static BattleCard NOBLES_BLASTER = new BattleCard("Noble's Blaster", Pip.BLUE, Pip.GREEN);


    // Upgrade - Armor
    public static BattleCard BASHING_SHIELD = new BattleCard("Bashing Shield", Pip.ORANGE, Pip.GREEN);
    public static BattleCard FORCE_FIELD = new BattleCard("Force Field", Pip.WHITE);

    public static BattleCard IMPROVISED_SHIELD = new BattleCard("Improvised Shield", Pip.ORANGE, Pip.ORANGE);

    public static BattleCard REINFORCED_PLATING = new BattleCard("Reinforced Plating", Pip.BLUE);
    public static BattleCard SUPERIOR_PLATING = new BattleCard("Superior Plating", Pip.BLUE);


    // Upgrade - Utilities
    public static BattleCard BRAVERY = new BattleCard("Bravery", Pip.BLUE);

    public static BattleCard MATRIX_OF_LEADERSHIP = new BattleCard("Matrix of Leadership", Pip.ORANGE, Pip.BLUE);

    public static BattleCard SPARE_PARTS = new BattleCard("Spare Parts", Pip.WHITE, Pip.GREEN);
    public static BattleCard SUPERIOR_JETPACK = new BattleCard("Superior Jetpack", Pip.WHITE);
    public static BattleCard TURBO_BOOSTERS = new BattleCard("Turbo Boosters", Pip.ORANGE);


}
