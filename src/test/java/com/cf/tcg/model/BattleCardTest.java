package com.cf.tcg.model;

import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.battle.focus.ScrapSinglePipsFocusRule;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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
    public void testCardsWithNoNameAreEqualAreEqualIfSamePips() {
        BattleCard battleCard1 = BattleCard.BASHING_SHIELD;
        BattleCard battleCard2 = BattleCard.ORANGE_GREEN;

        assertEquals(battleCard1, battleCard2);
    }

    @Test
    public void testMapGetOperationsFindsCardsWithoutNameButMatchingPips() {
        BattleCard bashingShield = BattleCard.BASHING_SHIELD;
        BattleCard orangeGreen = BattleCard.ORANGE_GREEN;

        Set<BattleCard> battleCards = new HashSet<>();
        battleCards.add(bashingShield);

        assertTrue(battleCards.contains(orangeGreen));
    }

}
