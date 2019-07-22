package com.cf.tcg.model;

import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.ScrapSinglePipsFocusRule;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

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

        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedNumberOfOrangeCards)
                .withSingleWhiteCards(expectedNumberOfWhiteCards)
                .withSingleBlueCards(expectedNumberOfBlueCards)
                .build();

        Deck deck = new Deck(deckComp);

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
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

        BattleCard battleCard = deck.drawCard();
        deck.scrapCard(battleCard);

        assertEquals(expectedDeckSize - 1, deck.getRemainingDeckCount());
    }

    @Test
    public void testReshuffle() {
        int expectedDeckSize = 40;
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

        deck.shuffleDeck();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testReset() {
        int expectedDeckSize = 40;
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

        deck.scrapCard(deck.drawCard());
        deck.scrapCard(deck.drawCard());

        deck.reshuffleScrapIntoDeck();

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
    }

    @Test
    public void testDrawingWhenNoCardsLeftReshufflesDeck() {
        int expectedDeckSize = 3;
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

        assertEquals(expectedDeckSize, deck.getRemainingDeckCount());
        BattleCard card1 = deck.drawCard();
        assertNotNull(card1);
        deck.scrapCard(card1);

        assertEquals(expectedDeckSize - 1, deck.getRemainingDeckCount());
        BattleCard card2 = deck.drawCard();
        assertNotNull(card2);
        deck.scrapCard(card2);

        assertEquals(expectedDeckSize - 2, deck.getRemainingDeckCount());
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
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

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
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withSingleOrangeCards(expectedDeckSize)
                .build();

        Deck deck = new Deck(deckComp);

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

    @Test
    public void testPeeking() {
        Stack<BattleCard> battleCards = new Stack<>();
        battleCards.push(BattleCard.SINGLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_WHITE);
        battleCards.push(BattleCard.SINGLE_BLUE); // Top of Deck

        Deck deck = new Deck(battleCards);

        BattleCard battleCard = deck.peekCard();
        assertEquals(BattleCard.SINGLE_BLUE, battleCard);

        BattleCard battleCard2 = deck.peekCard();
        assertEquals(BattleCard.SINGLE_BLUE, battleCard2);
    }

    @Test
    public void testFocusScrapsTopCard() {
        Stack<BattleCard> battleCards = new Stack<>();

        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);
        FocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        deck.focus(1, focusRule);
        assertEquals(3, deck.getRemainingDeckCount());
    }

    @Test
    public void testFocusNScrapsAllMatching() {
        Stack<BattleCard> battleCards = new Stack<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);
        FocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        deck.focus(4, focusRule);
        assertEquals(3, deck.getRemainingDeckCount());
    }
}
