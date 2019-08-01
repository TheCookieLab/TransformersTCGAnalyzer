package com.cf.tcg.battle;

import com.cf.tcg.model.Deck;

import java.util.ArrayList;
import java.util.List;

public class DrawSimulator {
    public final Deck deck;
    public final int iterations;


    public DrawSimulator(Deck deck, int iterations) {
        this.deck = deck;
        this.iterations = iterations;
    }

    public DrawSimulator(Deck deck) {
        this(deck, 10_000);
    }

    public List<Hand> simulate() {
        return this.simulate(1);
    }

    public List<Hand> simulate(int numberOfTurns) {
        List<Hand> results = new ArrayList<>();
        
        this.deck.shuffleDeck();

        for (int i = 0; i < this.iterations; i++) {
            Hand hand = new Hand();

            hand.addCards(this.getInitialDraw().flippedCards);

            for (int j = 0; j < numberOfTurns; j++) {
                hand.addCards(this.deck.flipCards(1).flippedCards); // Draw for turn
                this.deck.flipCards(2, true); // Attack
                this.deck.flipCards(2, true); // Defense
            }

            results.add(hand);
        }

        return results;
    }

    public FlipResult getInitialDraw() {
        return this.drawCards(3);
    }

    public FlipResult drawCards(int count) {
        return this.deck.flipCards(count);
    }

}
