package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author David
 */
public class HandTest {

    public HandTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContainsGenericCardByPips() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();
        battleCards.push(BattleCard.FORCE_FIELD);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.contains(BattleCard.SINGLE_WHITE));
    }

    @Ignore
    @Test
    /**
     * *
     * // TODO: Refactor Issue #1
     */
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

        // TODO: Refactor Issue #1
        //assertFalse(hand.containsAny(BattleCard.IMPROVISED_SHIELD));
        assertFalse(hand.containsAny(BattleCard.I_STILL_FUNCTION, BattleCard.MATRIX_OF_LEADERSHIP));
    }
}
