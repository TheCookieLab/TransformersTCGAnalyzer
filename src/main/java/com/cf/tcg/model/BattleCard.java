package com.cf.tcg.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
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
        hash = 89 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.pips, other.pips)) {
            return false;
        }
        return true;
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
}
