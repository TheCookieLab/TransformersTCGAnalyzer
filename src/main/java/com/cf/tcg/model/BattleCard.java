package com.cf.tcg.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
