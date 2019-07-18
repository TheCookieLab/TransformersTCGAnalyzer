package com.cf.tcg;

import com.cf.tcg.model.Deck;

import java.util.ArrayList;
import java.util.List;

public class BattleFlipSimulator {

    public final Deck deck;
    public final int iterations;
    public final boolean resetDeckOnEachFlip;


    public BattleFlipSimulator(Deck deck, int iterations, boolean resetDeckOnEachFlip) {
        this.deck = deck;
        this.iterations = iterations;
        this.resetDeckOnEachFlip = resetDeckOnEachFlip;
    }

    public BattleFlipSimulator(Deck deck) {
        this(deck, 1000, false);
    }


    public List<FlipResult> simulate() {
        List<FlipResult> results = new ArrayList<>();

        for (int i = 0; i < this.iterations; i++) {
            results.add(this.getFlipResult(2));

            if (resetDeckOnEachFlip) {
                this.deck.resetDeck();
            }
        }

        return results;
    }

    public FlipResult getFlipResult(int flipCount) {
        FlipResult result = deck.flipCards(flipCount, true);

        return result;
    }


}
