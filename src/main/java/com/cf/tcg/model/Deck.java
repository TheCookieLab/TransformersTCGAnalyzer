package com.cf.tcg.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author David
 */
public class Deck {

    public final Stack<BattleCard> battleCards;
    private final Stack<BattleCard> scrapPile;
    private final List<BattleCard> currentlyFlippedCards;

    public Deck(int numWhiteCards, int numBlueCards, int numOrangeCards, int numTotalCards) {
        this(initializeDeck(numWhiteCards, numBlueCards, numOrangeCards, numTotalCards));
    }

    public Deck() {
        this(new Stack<>());
    }

    public Deck(Stack<BattleCard> battleCards) {
        this.battleCards = battleCards;
        this.scrapPile = new Stack<>();
        this.currentlyFlippedCards = new ArrayList<>();
    }

    public BattleCard draw() {
        if (this.battleCards.size() == 0) {
            this.reshuffleScrapIntoDeck();
        }
        return this.battleCards.pop();
    }

    public List<BattleCard> flipCards(int count) {
        clearFlippedCards();

        for (int i = 0; i < count; i++) {
            this.currentlyFlippedCards.add(draw());
        }

        return this.currentlyFlippedCards;
    }

    public void scrap(BattleCard battleCard) {
        this.scrapPile.push(battleCard);
    }

    public void shuffle() {
        Collections.shuffle(battleCards);
    }

    public void reshuffleScrapIntoDeck() {
        this.battleCards.addAll(scrapPile);
        this.scrapPile.clear();
        this.shuffle();
    }

    public int getRemainingDeckCount() {
        return this.battleCards.size();
    }

    public int getScrapPileCount() {
        return this.scrapPile.size();
    }

    private void clearFlippedCards() {
        if (currentlyFlippedCards.size() > 0) {
            this.scrapPile.addAll(this.currentlyFlippedCards);
            this.currentlyFlippedCards.clear();
        }
    }

    private static Stack<BattleCard> initializeDeck(int numWhiteCards, int numBlueCards, int numOrangeCards, int numTotalCards) {        
        Stack<BattleCard> deck = new Stack<>();

        for (int i = 0; i < numWhiteCards; i++) {
            deck.push(new BattleCard(Pip.WHITE));
        }
        
        for (int i = 0; i < numBlueCards; i++) {
            deck.push(new BattleCard(Pip.BLUE));
        }
        
        for (int i = 0; i < numOrangeCards; i++) {
            deck.push(new BattleCard(Pip.ORANGE));
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
        private int numBlueCards;
        private int numOrangeCards;
        private int numTotalCards;

        public DeckBuilder(int numTotalCards) {
            this.numTotalCards = numTotalCards;
        }

        public DeckBuilder withNumberOfWhiteCards(int numWhiteCards) {
            this.numWhiteCards = numWhiteCards;
            return this;
        }

        public DeckBuilder withNumberOfOrangeCards(int numOrangeCards) {
            this.numOrangeCards = numOrangeCards;
            return this;
        }

        public DeckBuilder withNumberOfBlueCards(int numBlueCards) {
            this.numBlueCards = numBlueCards;
            return this;
        }

        public Deck build() {
            Deck deck = new Deck(numWhiteCards, numBlueCards, numOrangeCards, numTotalCards);
            return deck;
        }
    }
}
