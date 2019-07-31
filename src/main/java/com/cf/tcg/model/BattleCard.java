package com.cf.tcg.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public String toString() {
        return new Gson().toJson(this);
    }

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

    public static BattleCard BASHING_SHIELD = new BattleCard("Bashing Shield", Pip.ORANGE, Pip.GREEN);
    public static BattleCard CONFIDENCE = new BattleCard("Confidence", Pip.ORANGE);

    public static BattleCard ERRATIC_LIGHTNING = new BattleCard("Erratic Lightning", Pip.ORANGE);
    public static BattleCard FORCE_FIELD = new BattleCard("Force Field", Pip.WHITE);
    public static BattleCard GRENADE_LAUNCHER = new BattleCard("Grenade Launcher", Pip.ORANGE);

    public static BattleCard IMPROVISED_SHIELD = new BattleCard("Improvised Shield", Pip.ORANGE, Pip.ORANGE);
    public static BattleCard I_STILL_FUNCTION = new BattleCard("I Still Function!");

    public static BattleCard MATRIX_OF_LEADERSHIP = new BattleCard("Matrix of Leadership", Pip.ORANGE, Pip.BLUE);

    public static BattleCard ONE_SHALL_STAND_ONE_SHALL_FALL = new BattleCard("One Shall Stand, One Shall Fall");

    public static BattleCard TURBO_BOOSTERS = new BattleCard("Turbo Boosters");

    public static BattleCard PEACE_THROUGH_TYRANNY = new BattleCard("Peace Through Tyranny", Pip.ORANGE, Pip.ORANGE);
    public static BattleCard PRESS_THE_ADVANTAGE = new BattleCard("Press The Advantage", Pip.ORANGE, Pip.GREEN);

    public static BattleCard RAMMING_SPEED = new BattleCard("Ramming Speed", Pip.ORANGE);
    public static BattleCard RECKLESS_CHARGE = new BattleCard("Reckless Charge", Pip.ORANGE);

    public static BattleCard START_YOUR_ENGINES = new BattleCard("Start Your Engines", Pip.BLUE);


}
