package com.cf.tcg.battle;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author David
 */
public class DrawSimulator {

    private final BattleFlipSimulator battleFlipSimulator;
    private final Deck deck;

    public DrawSimulator(Deck deck) {
        this.deck = deck;
        this.battleFlipSimulator = new BattleFlipSimulator(deck);
    }

    public Map<BattleCard, Double> getInitialDrawOdds() {
        return this.getInitialDrawOdds(10_000);
    }

    public Map<BattleCard, Double> getInitialDrawOdds(int iterations) {
        Map<BattleCard, Long> results = new HashMap<>();

        for (int i = 0; i < iterations; i++) {
            Map<BattleCard, Long> drawnCards = this.drawCards(4).getDrawnCardsAsMap();
            results = Stream.of(results, drawnCards).flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (x, y) -> x + y));

            this.deck.resetDeck();
        }

        return new HashMap<>();
    }

    private DrawResult drawCards(int count) {
        DrawResult drawnCards = new DrawResult();

        for (int i = 0; i < count; i++) {
            drawnCards.addDrawnCard(this.deck.drawCard());
        }

        return drawnCards;
    }
}
