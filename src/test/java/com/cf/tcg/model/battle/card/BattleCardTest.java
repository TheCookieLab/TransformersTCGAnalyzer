package com.cf.tcg.model.battle.card;

import com.cf.tcg.model.Pip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * @author David
 */
public class BattleCardTest {

    public final static Logger LOG = LogManager.getLogger();

    public BattleCardTest() {
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

    @Test
    public void testContainsAllPips() {
        BattleCard battleCard1 = BattleCard.ORANGE_GREEN;

        assertTrue(battleCard1.containsAllPips(Pip.ORANGE, Pip.GREEN));
        assertFalse(battleCard1.containsAllPips(Pip.ORANGE, Pip.GREEN, Pip.BLACK));
        assertFalse(battleCard1.containsAllPips(Pip.ORANGE, Pip.ORANGE, Pip.GREEN));
        assertTrue(battleCard1.containsAllPips());
        
        Pip[] requiredPips = null;
        assertTrue(battleCard1.containsAllPips(requiredPips));
    }

    @Test
    public void testContainsAnyOfPips1() {
        BattleCard battleCard1 = BattleCard.ORANGE_GREEN;

        assertTrue(battleCard1.containsAnyOfPips(Pip.ORANGE));
        assertTrue(battleCard1.containsAnyOfPips(Pip.GREEN));

        assertFalse(battleCard1.containsAnyOfPips(Pip.BLACK, Pip.BLUE, Pip.WHITE));
        assertTrue(battleCard1.containsAnyOfPips());
        
        Pip[] requiredPips = null;
        assertTrue(battleCard1.containsAnyOfPips(requiredPips));
    }
}
