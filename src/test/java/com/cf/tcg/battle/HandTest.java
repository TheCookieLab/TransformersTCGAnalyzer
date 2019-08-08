package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class HandTest {

    public HandTest() {
    }

    @Test
    public void testContainsGenericCardByPips() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();
        battleCards.push(BattleCard.FORCE_FIELD);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.contains(BattleCard.SINGLE_WHITE));
    }

    @Test
    public void testContainsSpecificCardDoesNotMatchGeneric() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();
        battleCards.push(BattleCard.SINGLE_WHITE);

        Hand hand = new Hand(battleCards);

        assertFalse(hand.contains(BattleCard.FORCE_FIELD));
    }

    @Test
    public void testContainsBattleCard() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.contains(BattleCard.BASHING_SHIELD));
        assertTrue(hand.contains(BattleCard.PRESS_THE_ADVANTAGE));
        assertTrue(hand.contains(BattleCard.FORCE_FIELD));
        assertTrue(hand.contains(BattleCard.DOUBLE_ORANGE));
        assertTrue(hand.contains(BattleCard.SINGLE_WHITE));

        assertFalse(hand.contains(BattleCard.BLACK_BLUE));
        assertFalse(hand.contains(BattleCard.MATRIX_OF_LEADERSHIP));
    }

    @Test
    public void testContainsAllBattleCards() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.containsAll(BattleCard.BASHING_SHIELD, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.FORCE_FIELD));
        assertTrue(hand.containsAll(BattleCard.PRESS_THE_ADVANTAGE));

        assertFalse(hand.containsAll(BattleCard.BASHING_SHIELD, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.FORCE_FIELD, BattleCard.MATRIX_OF_LEADERSHIP));
    }

    @Test
    public void testContainsAnyOfBattleCards() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.containsAny(BattleCard.BASHING_SHIELD, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.FORCE_FIELD));
        assertTrue(hand.containsAny(BattleCard.PRESS_THE_ADVANTAGE));

        assertFalse(hand.containsAny(BattleCard.IMPROVISED_SHIELD));
        assertFalse(hand.containsAny(BattleCard.I_STILL_FUNCTION, BattleCard.MATRIX_OF_LEADERSHIP));
    }

    @Test
    public void testContainsAnyOfBattleCardsWhenEmptyList() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertFalse(hand.containsAny());
    }

    @Test
    public void testContainsAnyOfBattleCardsWhenListIsNull() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertFalse(hand.containsAny(null));
    }

    @Test
    public void testContainsAllOfBattleCardsWhenEmptyList() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertFalse(hand.containsAll());
    }

    @Test
    public void testContainsAllOfBattleCardsWhenListIsNull() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertFalse(hand.containsAll(null));
    }
}
