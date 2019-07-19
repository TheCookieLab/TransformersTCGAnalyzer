package com.cf.tcg;

import com.cf.tcg.model.Deck;
import com.google.gson.Gson;

/**
 *
 * @author David
 */
public class ScenarioResult {
    public Double averageAttackBonus;
    public Double attackBonusVariance;
    public Double averageDefenseBonus;
    public Double defenseBonusVariance;
    public Double averagePierceBonus;
    public Double pierceBonusVariance;
    
    public Double boldValue;
    public Double toughValue;
    public Double focusValue;
    
    public final Deck deck;

    public ScenarioResult(Deck deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
