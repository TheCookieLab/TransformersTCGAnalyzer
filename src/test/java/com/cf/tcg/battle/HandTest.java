package com.cf.tcg.battle;

import com.cf.tcg.model.BattleCard;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testContainsBattleCard() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.containsBattleCard(BattleCard.BASHING_SHIELD));
        assertTrue(hand.containsBattleCard(BattleCard.PRESS_THE_ADVANTAGE));
        assertTrue(hand.containsBattleCard(BattleCard.FORCE_FIELD));
        assertTrue(hand.containsBattleCard(BattleCard.DOUBLE_ORANGE));
        assertTrue(hand.containsBattleCard(BattleCard.SINGLE_WHITE));

        assertFalse(hand.containsBattleCard(BattleCard.BLACK_BLUE));
        assertFalse(hand.containsBattleCard(BattleCard.MATRIX_OF_LEADERSHIP));
    }

    @Test
    public void testContainsBattleCards() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.PRESS_THE_ADVANTAGE);
        battleCards.push(BattleCard.FORCE_FIELD);
        battleCards.push(BattleCard.DOUBLE_ORANGE);

        Hand hand = new Hand(battleCards);

        assertTrue(hand.containsBattleCards(BattleCard.BASHING_SHIELD, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.FORCE_FIELD));
        assertTrue(hand.containsBattleCards(BattleCard.PRESS_THE_ADVANTAGE));

        assertFalse(hand.containsBattleCards(BattleCard.BASHING_SHIELD, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.FORCE_FIELD, BattleCard.MATRIX_OF_LEADERSHIP));
    }
}
