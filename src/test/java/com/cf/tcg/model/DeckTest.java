package com.cf.tcg.model;

import com.cf.tcg.FlipResult;
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

        BattleCard battleCard = deck.drawCard();
        deck.scrapCard(battleCard);

        assertEquals(expectedDeckSize - 1, deck.getRemainingDeckCount());
    }

    @Test
    public void testReshuffle() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.shuffleDeck();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testReset() {
        int expectedDeckSize = 40;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.scrapCard(deck.drawCard());
        deck.scrapCard(deck.drawCard());

        deck.reshuffleScrapIntoDeck();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testDrawingWhenNoCardsLeftReshufflesDeck() {
        int expectedDeckSize = 3;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
        BattleCard card1 = deck.drawCard();
        assertNotNull(card1);
        deck.scrapCard(card1);

        assertEquals(expectedDeckSize-1, deck.getRemainingDeckCount());
        BattleCard card2 = deck.drawCard();
        assertNotNull(card2);
        deck.scrapCard(card2);

        assertEquals(expectedDeckSize-2, deck.getRemainingDeckCount());
        BattleCard card3 = deck.drawCard();
        assertNotNull(card3);
        deck.scrapCard(card3);
        assertEquals(3, deck.getScrapPileCount());

        assertEquals(0, deck.getRemainingDeckCount());
        BattleCard card4 = deck.drawCard();
        assertNotNull(card4);
        deck.scrapCard(card4);
        assertEquals(1, deck.getScrapPileCount());
    }

    @Test
    public void testFlippingCards() {
        int expectedDeckSize = 3;
        int cardsToFlip = 1;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        FlipResult flippedCards = deck.flipCards(cardsToFlip);
        assertEquals(cardsToFlip, flippedCards.getTotalNumberOfCardsFlipped().intValue());
        assertEquals(expectedDeckSize - cardsToFlip, deck.getRemainingDeckCount());
        assertEquals(0, deck.getScrapPileCount());

        int nextFlipCount = 1;
        flippedCards = deck.flipCards(nextFlipCount);
        assertEquals(nextFlipCount, flippedCards.getTotalNumberOfCardsFlipped().intValue());
        assertEquals(expectedDeckSize - cardsToFlip - nextFlipCount, deck.getRemainingDeckCount());
        assertEquals(1, deck.getScrapPileCount());

        int finalFlipCount = 2;
        flippedCards = deck.flipCards(finalFlipCount);
        assertEquals(finalFlipCount, flippedCards.getTotalNumberOfCardsFlipped().intValue());
        assertEquals(1, deck.getRemainingDeckCount());
        assertEquals(0, deck.getScrapPileCount());
    }

    @Test
    public void testScrappingFromTopOfDeck() {
        int expectedDeckSize = 3;
        Deck deck = new Deck.DeckBuilder(expectedDeckSize)
                .build();

        deck.scrapCardsFromTopOfDeck(1);
        assertEquals(2, deck.getRemainingDeckCount());
        assertEquals(1, deck.getScrapPileCount());

        deck.scrapCardsFromTopOfDeck(1);
        assertEquals(1, deck.getRemainingDeckCount());
        assertEquals(2, deck.getScrapPileCount());

        deck.scrapCardsFromTopOfDeck(1);
        assertEquals(0, deck.getRemainingDeckCount());
        assertEquals(3, deck.getScrapPileCount());

        deck.scrapCardsFromTopOfDeck(1);
        assertEquals(2, deck.getRemainingDeckCount());
        assertEquals(1, deck.getScrapPileCount());

        deck.scrapCardsFromTopOfDeck(3);
        assertEquals(2, deck.getRemainingDeckCount());
        assertEquals(1, deck.getScrapPileCount());
    }
}
