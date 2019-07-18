package com.cf.tcg.model;

import com.cf.tcg.FlipResult;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author David
 */
public class Deck {

    public final Stack<BattleCard> battleCards;
    private final Stack<BattleCard> scrapPile;
    private FlipResult currentlyFlippedCards;

    public Deck(int numWhiteCards, int numBlueCards, int numOrangeCards, int numTotalCards) {
        this(initializeDeck(numWhiteCards, numBlueCards, numOrangeCards, numTotalCards));
    }

    public Deck() {
        this(new Stack<>());
    }

    public Deck(Stack<BattleCard> battleCards) {
        this.battleCards = battleCards;
        this.scrapPile = new Stack<>();
        this.currentlyFlippedCards = new FlipResult();
    }

    public BattleCard drawCard() {
        if (this.battleCards.size() == 0) {
            this.reshuffleScrapIntoDeck();
        }
        return this.battleCards.pop();
    }

    public BattleCard peekCard() {
        if (this.battleCards.size() == 0) {
            this.reshuffleScrapIntoDeck();
        }
        return this.battleCards.peek();
    }

    public void scrapCardsFromTopOfDeck(int count) {
        for (int i = 0; i < count; i++) {
            BattleCard card = this.drawCard();
            this.scrapCard(card);
        }
    }

    public FlipResult flipCards(int count) {
        return this.flipCards(count, false);
    }

    public FlipResult flipCards(int count, boolean firstWhitePipFlips2More) {
        this.scrapFlippedCards();

        for (int i = 0; i < count; i++) {
            this.currentlyFlippedCards.addFlippedCard(drawCard());
        }

        if (firstWhitePipFlips2More && this.currentlyFlippedCards.getTotalNumberOfPipsFlipped(Pip.WHITE) > 0) {
            this.currentlyFlippedCards.addFlippedCard(drawCard());
            this.currentlyFlippedCards.addFlippedCard(drawCard());
        }

        return this.currentlyFlippedCards;
    }

    public void scrapCard(BattleCard battleCard) {
        this.scrapPile.push(battleCard);
    }

    public void shuffleDeck() {
        Collections.shuffle(battleCards);
    }

    public void reshuffleScrapIntoDeck() {
        this.battleCards.addAll(scrapPile);
        this.scrapPile.clear();
        this.shuffleDeck();
    }

    public void resetDeck() {
        this.scrapFlippedCards();
        this.reshuffleScrapIntoDeck();
    }

    public int getRemainingDeckCount() {
        return this.battleCards.size();
    }

    public int getScrapPileCount() {
        return this.scrapPile.size();
    }

    private void scrapFlippedCards() {
        if (currentlyFlippedCards.getTotalNumberOfCardsFlipped() > 0) {
            this.scrapPile.addAll(this.currentlyFlippedCards.flippedCards);
            this.currentlyFlippedCards = new FlipResult();
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
