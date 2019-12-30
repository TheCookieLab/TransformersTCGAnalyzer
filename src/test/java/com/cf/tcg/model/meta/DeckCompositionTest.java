package com.cf.tcg.model.meta;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;

import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
    public void testToString() {
        Deck deck = this.buildDeck();

        DeckComposition deckStats = new DeckComposition(deck);

        assertEquals(6, deckStats.doubleOrange.intValue());
        assertEquals(5, deckStats.white.intValue());
        assertEquals(2, deckStats.orangeGreen.intValue());
        assertEquals(40, deckStats.totalCards.intValue());

        LogManager.getLogger().info(deckStats);
    }

    @Test
    public void testWithThreeBattleCards() {

        int expectedCardCount = 3;
        DeckComposition deckComposition = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.GRENADE_LAUNCHER, expectedCardCount)
                .build();

        assertEquals(expectedCardCount, deckComposition.battleCards.size());
    }

    @Test
    public void testWithOneBattleCard() {
        DeckComposition deckComposition = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.GRENADE_LAUNCHER)
                .build();

        assertEquals(1, deckComposition.battleCards.size());
    }

    @Test
    public void testWithNamedBattleCards() {
        DeckComposition deckComposition = new DeckComposition.DeckCompositionBuilder()
                .withBattleCards(BattleCard.GRENADE_LAUNCHER, BattleCard.BASHING_SHIELD, BattleCard.FORCE_FIELD)
                .build();

        assertEquals(3, deckComposition.battleCards.size());
    }
}
