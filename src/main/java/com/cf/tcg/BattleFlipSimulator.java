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

        }

        return results;
    }

    public FlipResult getFlipResult() {
        FlipResult result = new FlipResult();



        return result;
    }


}
