package com.cf.tcg.model;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author David
 */
public class Deck {

    public final Stack<BattleCard> battleCards;
    private final Stack<BattleCard> scrapPile;

    public Deck(int whiteCards, int numTotalCards) {
        this(initializeDeck(whiteCards, numTotalCards));
    }

    public Deck() {
        this(new Stack<>());
    }

    public Deck(Stack<BattleCard> battleCards) {
        this.battleCards = battleCards;
        this.scrapPile = new Stack<>();
    }

    public BattleCard draw() {
        LogManager.getLogger().debug("Deck currently has {} cards", this.battleCards.size());
        return this.battleCards.pop();
    }

    public void scrap(BattleCard battleCard) {
        this.scrapPile.push(battleCard);
    }

    public void shuffle() {
        Collections.shuffle(battleCards);
    }

    public void reset() {
        this.battleCards.addAll(scrapPile);
        this.shuffle();
    }

    private static Stack<BattleCard> initializeDeck(int numWhiteCards, int numTotalCards) {
        Stack<BattleCard> deck = new Stack<>();

        for (int i = 0; i < numWhiteCards; i++) {
            deck.push(new BattleCard(Pip.WHITE));
        }

        for (int i = deck.size(); i < numTotalCards; i++) {
            deck.push(new BattleCard());
        }

        Collections.shuffle(deck);
        return deck;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static class DeckBuilder {

        private int numWhiteCards;
        private int numTotalCards;

        public DeckBuilder(int numTotalCards) {
            this.numTotalCards = numTotalCards;
        }

        public DeckBuilder withNumberOfWhiteCards(int numWhiteCards) {
            this.numWhiteCards = numWhiteCards;
            return this;
        }

        public Deck build() {
            Deck deck = new Deck(numWhiteCards, numTotalCards);
            return deck;
        }
    }
}
