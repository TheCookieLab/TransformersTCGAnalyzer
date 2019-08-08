package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;

import java.util.List;
import java.util.Map;

public class HandResultInterpreter {

    public final List<Hand> hands;
    public final Deck deck;

    private final Map<BattleCard, Integer> deckList;

    public HandResultInterpreter(Deck deck, List<Hand> hands) {
        this.hands = hands;
        this.deck = deck;
        this.deckList = deck.getDecklist();
    }

    public Double getChanceOfHavingCard(BattleCard battleCard) {
        int count = 0;

        for (Hand hand : this.hands) {
            if (hand.contains(battleCard)) {
                count++;
            }
        }

        return count / this.getCount().doubleValue();
    }

    public Double getChanceOfHavingAllCards(BattleCard... battleCards) {
        int count = 0;

        for (Hand hand : this.hands) {
            if (hand.containsAll(battleCards)) {
                count++;
            }
        }

        return count / this.getCount().doubleValue();
    }

    public Double getChanceOfHavingAnyOfCards(BattleCard... battleCards) {
        int count = 0;

        for (Hand hand : this.hands) {
            if (hand.containsAny(battleCards)) {
                count++;
            }
        }

        return count / this.getCount().doubleValue();
    }

    public Double getChanceOfHavingCombinationOfCards(List<BattleCard> allOfBattleCards, List<BattleCard> anyOfBattleCards) {
        int count = 0;

        for (Hand hand : this.hands) {
            if (hand.containsAny(anyOfBattleCards.stream().toArray(BattleCard[]::new)) && hand.containsAll(allOfBattleCards.stream().toArray(BattleCard[]::new))) {
                count++;
            }
        }

        return count / this.getCount().doubleValue();
    }


    public Integer getCount() {
        return this.hands.size();
    }
}
