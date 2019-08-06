package com.cf.tcg.battle;

import com.cf.tcg.model.BattleCard;
import com.google.gson.Gson;

import java.util.*;

public class Hand {
    public final List<BattleCard> cardsInHand;

    public Hand(List<BattleCard> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public Hand(BattleCard... cardsInHand) {
        this(Arrays.asList(cardsInHand));
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public boolean addCard(BattleCard battleCard) {
        return this.cardsInHand.add(battleCard);
    }

    public boolean addCards(BattleCard... battleCards) {
        List<BattleCard> battleCardList = Arrays.asList(battleCards);
        return this.addCards(battleCardList);
    }

    public boolean addCards(List<BattleCard> battleCards) {
        return this.cardsInHand.addAll(battleCards);
    }

    public Integer getNumberOf(BattleCard desiredCard) {
        int count = 0;
        for (BattleCard battleCard : this.cardsInHand) {
            if (battleCard.equals(desiredCard)) {
                count++;
            }
        }

        return count;
    }

    public boolean containsBattleCard(BattleCard battleCard) {
        return this.getNumberOf(battleCard) > 0;
    }
    
    public boolean containsBattleCards(BattleCard... battleCards) {
        for (BattleCard battleCard : battleCards) {
            if (this.containsBattleCard(battleCard) == false) {
                return false;
            }
        }
        
        return true;
    }

    public Integer getCount() {
        return this.cardsInHand.size();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
