package com.cf.tcg.model.meta;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;

import java.util.LinkedList;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author David
 */
public class DeckCompositionTest {

    public DeckCompositionTest() {
    }
    
    public Deck buildDeck() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        int totalCards = 40;
        int numDoubleOrange = 6;
        int numOrangeGreen = 2;
        int numWhite = 5;
        int numBlank = 3;

        for (int i = 0; i < numDoubleOrange; i++) {
            battleCards.push(BattleCard.DOUBLE_ORANGE);
        }

        for (int i = 0; i < numWhite; i++) {
            battleCards.push(BattleCard.SINGLE_WHITE);
        }

        for (int i = 0; i < numBlank; i++) {
            battleCards.push(BattleCard.BLANK);
        }

        for (int i = 0; i < numOrangeGreen; i++) {
            battleCards.push(BattleCard.ORANGE_GREEN);
        }

        int numSingleOrange = totalCards - numDoubleOrange - numOrangeGreen - numWhite - numBlank;
        for (int i = 0; i < numSingleOrange; i++) {
            battleCards.push(BattleCard.SINGLE_ORANGE);
        }

        Deck deck = new Deck(battleCards);
        deck.shuffleDeck();

        return deck;
    }

    @Test
    public void hello() {
        Deck deck = this.buildDeck();

        DeckComposition deckStats = new DeckComposition(deck);
        
        assertEquals(6, deckStats.doubleOrange.intValue());
        assertEquals(5, deckStats.white.intValue());
        assertEquals(2, deckStats.orangeGreen.intValue());
        assertEquals(40, deckStats.totalCards.intValue());
        
        LogManager.getLogger().info(deckStats);
    }
}
