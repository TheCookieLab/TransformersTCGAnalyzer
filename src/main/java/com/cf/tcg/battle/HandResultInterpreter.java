package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandResultInterpreter {

    public final List<Hand> hands;
    public final Deck deck;

    private final Map<BattleCard, Integer> deckList;
    private Map<BattleCard, Double> battleCardOccurrences;

    public HandResultInterpreter(Deck deck, List<Hand> hands) {
        this.hands = hands;
        this.deck = deck;
        this.deckList = deck.getDecklist();
    }

    public Double getChanceOfHavingCard(BattleCard battleCard) {
//        Map<BattleCard, Double> occurrenceMap = this.getOccurrenceMap();
//
//        return occurrenceMap.getOrDefault(battleCard, 0d) / this.getCount();
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
            if (hand.containsAll(battleCards)) {
                count++;
            }
        }

        return count / this.getCount().doubleValue();
    }

    private Map<BattleCard, Double> getOccurrenceMap() {
        if (this.battleCardOccurrences == null || this.battleCardOccurrences.isEmpty()) {
            this.battleCardOccurrences = new HashMap<>();

            for (BattleCard battleCard : this.deckList.keySet()) {
                long count = this.hands.stream().filter(hand -> hand.contains(battleCard)).count();
                this.battleCardOccurrences.put(battleCard, (double) count);
            }
        }

        return this.battleCardOccurrences;
    }

    public Integer getCount() {
        return this.hands.size();
    }
}
