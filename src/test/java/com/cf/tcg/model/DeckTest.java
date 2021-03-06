package com.cf.tcg.model;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.battle.focus.ScrapSinglePipsFocusRule;
import com.cf.tcg.model.battle.card.BattleCardType;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * @author David
 */
public class DeckTest {

    public final static Logger LOG = LogManager.getLogger();

    public DeckTest() {
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
        LinkedList<BattleCard> battleCards = new LinkedList<>();
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
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);
        FocusRule focusRule = new ScrapSinglePipsFocusRule(1);

        deck.focus(focusRule);
        assertEquals(3, deck.getRemainingDeckCount());
    }

    @Test
    public void testFocusNScrapsAllMatching() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);
        FocusRule focusRule = new ScrapSinglePipsFocusRule(4);
        focusRule.setAttacking();

        deck.focus(focusRule);
        assertEquals(3, deck.getRemainingDeckCount());
    }

    @Test
    public void testFocusNScrapsNonOrangeWhenAttacking() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);
        FocusRule focusRule = new ScrapOffColorFocusRule(2);
        focusRule.setAttacking();

        deck.focus(focusRule);
        FlipResult flipResult = deck.flipCards(2, true);
        assertEquals(3, flipResult.getTotalAttackBonus().intValue());
    }

    @Test
    public void testToStringStaysConsistentAfterDrawScrapAndFlip() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.SINGLE_ORANGE); // Top of Deck

        Deck deck = new Deck(battleCards);

        String expected = "{\"doubleOrange\":2,\"singleOrange\":1,\"singleBlue\":2,\"totalCards\":5}";
        assertEquals(expected, deck.toString());

        BattleCard battleCard = deck.drawCard();
        deck.scrapCard(battleCard);

        FlipResult flipResult = deck.flipCards(2);
        assertEquals(expected, deck.toString());
    }

    @Test
    public void testGetDecklistGroupsSameCardsTogether() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.SINGLE_ORANGE);

        Deck deck = new Deck(battleCards);

        Map<BattleCard, Integer> decklist = deck.getDecklist();
        assertEquals(3, decklist.size());
    }

    @Test
    public void testGetDeckPipBreakdownReturnsCorrectPipCount() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.DOUBLE_ORANGE);
        battleCards.push(BattleCard.SINGLE_BLUE);
        battleCards.push(BattleCard.SINGLE_ORANGE);
        battleCards.push(BattleCard.BLUE_ORANGE_WHITE);
        battleCards.push(BattleCard.TRIPLE_BLACK);


        Deck deck = new Deck(battleCards);

        Map<Pip, Integer> pips = deck.getDeckPipBreakdown();
        assertEquals(3, pips.get(Pip.BLUE));
        assertEquals(6, pips.get(Pip.ORANGE));
        assertEquals(0, pips.get(Pip.GREEN));
        assertEquals(3, pips.get(Pip.BLACK));
        assertEquals(1, pips.get(Pip.WHITE));
     }

    @Test
    public void testGetDeckBattleCardTypeBreakdownReturnsCorrectCount() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.COVERT_ARMOR);
        battleCards.push(BattleCard.BASHING_SHIELD);

        battleCards.push(BattleCard.GRENADE_LAUNCHER);
        battleCards.push(BattleCard.ENERGON_AXE);

        battleCards.push(BattleCard.MATRIX_OF_LEADERSHIP);

        battleCards.push(BattleCard.ONE_SHALL_STAND_ONE_SHALL_FALL);
        battleCards.push(BattleCard.THE_BIGGER_THEY_ARE);

        battleCards.push(BattleCard.SABOTAGED_ARMAMENTS);

        Deck deck = new Deck(battleCards);

        Map<BattleCardType, Integer> battleCardTypes = deck.getDeckCardTypeBreakdown();
        assertEquals(2, battleCardTypes.get(BattleCardType.ACTION));
        assertEquals(2, battleCardTypes.get(BattleCardType.UPGRADE_WEAPON));
        assertEquals(2, battleCardTypes.get(BattleCardType.UPGRADE_ARMOR));
        assertEquals(1, battleCardTypes.get(BattleCardType.UPGRADE_UTILITY));
        assertEquals(1, battleCardTypes.get(BattleCardType.SECRET_ACTION));
    }
}
