package com.cf.tcg.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
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

        assertEquals(expectedDeckSize - 1, deck.battleCards.size());
    }

    @Test
    public void testReshuffle() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.shuffle();

        assertEquals(expectedDeckSize, deck.battleCards.size());
    }

    @Test
    public void testReset() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.scrap(deck.draw());
        deck.scrap(deck.draw());

        deck.reset();

        assertEquals(expectedDeckSize, deck.battleCards.size());
    }
}
