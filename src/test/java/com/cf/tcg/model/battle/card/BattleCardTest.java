package com.cf.tcg.model.battle.card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author David
 */
public class BattleCardTest {

    public final static Logger LOG = LogManager.getLogger();

    public BattleCardTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSameCardsAreEqual() {
        BattleCard battleCard1 = BattleCard.BASHING_SHIELD;
        BattleCard battleCard2 = BattleCard.BASHING_SHIELD;

        assertEquals(battleCard1, battleCard2);
    }

    @Test
    public void testDifferentCardsAreNotEqual() {
        BattleCard battleCard1 = BattleCard.BASHING_SHIELD;
        BattleCard battleCard2 = BattleCard.FORCE_FIELD;

        assertNotEquals(battleCard1, battleCard2);
    }

    @Test
    public void testCardsWithSamePipsAreEqual() {
        BattleCard battleCard1 = BattleCard.DOUBLE_ORANGE;
        BattleCard battleCard2 = BattleCard.DOUBLE_ORANGE;

        assertEquals(battleCard1, battleCard2);
    }

    @Test
    public void testCardsWithDifferentPipsAreNotEqual() {
        BattleCard battleCard1 = BattleCard.SINGLE_BLUE;
        BattleCard battleCard2 = BattleCard.BLUE_ORANGE_WHITE;

        assertNotEquals(battleCard1, battleCard2);
    }

    @Test
    public void testCardWithNoNameIsEqualToNamedIfSamePips() {
        BattleCard battleCard1 = BattleCard.BASHING_SHIELD;
        BattleCard battleCard2 = BattleCard.ORANGE_GREEN;

        assertTrue(battleCard1.equalsIgnoreName(battleCard2));
    }

    @Test
    public void testCardsWithNoNameAreNotEqualIfDifferentPips() {
        BattleCard battleCard1 = BattleCard.SINGLE_ORANGE;
        BattleCard battleCard2 = BattleCard.ORANGE_GREEN;

        assertFalse(battleCard1.equalsIgnoreName(battleCard2));
    }

    @Test
    public void testCardsWithNoNamesAreEqualIfSamePips() {
        BattleCard battleCard1 = BattleCard.ORANGE_GREEN;
        BattleCard battleCard2 = BattleCard.ORANGE_GREEN;

        assertTrue(battleCard1.equalsIgnoreName(battleCard2));
    }
}
