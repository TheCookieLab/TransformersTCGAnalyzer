package com.cf.tcg.model;

import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.model.meta.DeckComposition;
import com.google.gson.Gson;

import java.awt.image.AreaAveragingScaleFilter;
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
    private FlipResult currentlyFlippedCards;

    public Deck() {
        this(new Stack<>());
    }

    public Deck(Stack<BattleCard> battleCards) {
        this.battleCards = battleCards;
        this.scrapPile = new Stack<>();
        this.currentlyFlippedCards = new FlipResult();
    }

    public Deck(DeckComposition deckComposition) {
        this.battleCards = new Stack<>();
        this.scrapPile = new Stack<>();
        this.currentlyFlippedCards = new FlipResult();

        for (int i = 0; i < deckComposition.blank; i++) {
            battleCards.push(BattleCard.BLANK);
        }

        for (int i = 0; i < deckComposition.doubleOrange; i++) {
            battleCards.push(BattleCard.DOUBLE_ORANGE);
        }

        for (int i = 0; i < deckComposition.singleOrange; i++) {
            battleCards.push(BattleCard.SINGLE_ORANGE);
        }

        for (int i = 0; i < deckComposition.doubleBlue; i++) {
            battleCards.push(BattleCard.DOUBLE_BLUE);
        }

        for (int i = 0; i < deckComposition.singleBlue; i++) {
            battleCards.push(BattleCard.SINGLE_BLUE);
        }

        for (int i = 0; i < deckComposition.doubleBlack; i++) {
            battleCards.push(BattleCard.DOUBLE_BLACK);
        }

        for (int i = 0; i < deckComposition.singleBlack; i++) {
            battleCards.push(BattleCard.SINGLE_BLACK);
        }

        for (int i = 0; i < deckComposition.white; i++) {
            battleCards.push(BattleCard.SINGLE_WHITE);
        }

        for (int i = 0; i < deckComposition.whiteGreen; i++) {
            battleCards.push(BattleCard.WHITE_GREEN);
        }

        for (int i = 0; i < deckComposition.whiteOrangeBlue; i++) {
            battleCards.push(BattleCard.BLUE_ORANGE_WHITE);
        }

        for (int i = 0; i < deckComposition.green; i++) {
            battleCards.push(BattleCard.SINGLE_GREEN);
        }

        for (int i = 0; i < deckComposition.blueGreen; i++) {
            battleCards.push(BattleCard.BLUE_GREEN);
        }

        for (int i = 0; i < deckComposition.orangeGreen; i++) {
            battleCards.push(BattleCard.ORANGE_GREEN);
        }

        for (int i = 0; i < deckComposition.blueOrange; i++) {
            battleCards.push(BattleCard.BLUE_ORANGE);
        }

        for (int i = 0; i < deckComposition.blueBlack; i++) {
            battleCards.push(BattleCard.BLACK_BLUE);
        }

        for (int i = 0; i < deckComposition.orangeBlack; i++) {
            battleCards.push(BattleCard.BLACK_ORANGE);
        }

        Collections.shuffle(battleCards);
    }

    public BattleCard drawCard() {
        if (this.battleCards.isEmpty()) {
            this.reshuffleScrapIntoDeck();
        }
        return this.battleCards.pop();
    }

    public BattleCard peekCard() {
        if (this.battleCards.isEmpty()) {
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

    public void planCard(BattleCard battleCard) {
        this.battleCards.push(battleCard);
    }

    public void focus(int focus, FocusRule focusRule) {
        List<BattleCard> inFocus = new ArrayList<>();

        for (int i = 0; i < focus; i++) {
            BattleCard battleCard = this.drawCard();
            if (focusRule.shouldScrap(battleCard)) {
                this.scrapCard(battleCard);
            } else {
                inFocus.add(battleCard);
            }
        }

        this.battleCards.addAll(inFocus);
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

    @Override
    public String toString() {
        
        DeckComposition deckComp = new DeckComposition(this);
        return new Gson().toJson(deckComp);
    }
}
