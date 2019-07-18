package com.cf.tcg.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author David
 */
public class DeckTest {

    public final static Logger LOG = LogManager.getLogger();

    public DeckTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDeckBuilderCreatesCorrectDeck() {
        int expectedNumberOfOrangeCards = 14;
        int expectedNumberOfWhiteCards = 10;
        int expectedNumberOfBlueCards = 16;
        int expectedDeckSize = expectedNumberOfOrangeCards + expectedNumberOfWhiteCards + expectedNumberOfBlueCards;

        Deck deck = new Deck.DeckBuilder(40)
                .withNumberOfWhiteCards(expectedNumberOfWhiteCards)
                .withNumberOfBlueCards(expectedNumberOfBlueCards)
                .withNumberOfOrangeCards(expectedNumberOfOrangeCards)
                .build();

        Long actualNumberOfWhiteCards = deck.battleCards.stream().filter((battleCard) -> {
            return battleCard.pips.contains(Pip.WHITE);
        }).count();

        Long actualNumberOfOrangeCards = deck.battleCards.stream().filter((battleCard) -> {
            return battleCard.pips.contains(Pip.ORANGE);
        }).count();

        Long actualNumberOfBlueCards = deck.battleCards.stream().filter((battleCard) -> {
            return battleCard.pips.contains(Pip.BLUE);
        }).count();

        assertEquals(expectedDeckSize, deck.battleCards.size());
        assertEquals(expectedNumberOfWhiteCards, actualNumberOfWhiteCards.intValue());
        assertEquals(expectedNumberOfOrangeCards, actualNumberOfOrangeCards.intValue());
        assertEquals(expectedNumberOfBlueCards, actualNumberOfBlueCards.intValue());
    }

    @Test
    public void testScrap() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        BattleCard battleCard = deck.draw();
        deck.scrap(battleCard);

        assertEquals(expectedDeckSize - 1, deck.getRemainingDeckCount());
    }

    @Test
    public void testReshuffle() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.shuffle();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testReset() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.scrap(deck.draw());
        deck.scrap(deck.draw());

        deck.reshuffleScrapIntoDeck();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testDrawingWhenNoCardsLeftReshufflesDeck() {
        int expectedDeckSize = 3;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
        BattleCard card1 = deck.draw();
        assertNotNull(card1);
        deck.scrap(card1);

        assertEquals(expectedDeckSize-1, deck.getRemainingDeckCount());
        BattleCard card2 = deck.draw();
        assertNotNull(card2);
        deck.scrap(card2);

        assertEquals(expectedDeckSize-2, deck.getRemainingDeckCount());
        BattleCard card3 = deck.draw();
        assertNotNull(card3);
        deck.scrap(card3);
        assertEquals(3, deck.getScrapPileCount());

        assertEquals(0, deck.getRemainingDeckCount());
        BattleCard card4 = deck.draw();
        assertNotNull(card4);
        deck.scrap(card4);
        assertEquals(1, deck.getScrapPileCount());
    }
}
