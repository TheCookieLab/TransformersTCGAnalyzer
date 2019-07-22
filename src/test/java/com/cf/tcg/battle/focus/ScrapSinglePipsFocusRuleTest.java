package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import org.junit.Test;


import static org.junit.Assert.*;

public class ScrapSinglePipsFocusRuleTest {

    public ScrapSinglePipsFocusRuleTest() {

    }

    @Test
    public void scrapsSingleOrangeWhenAttacking() {
        BattleCard battleCard = BattleCard.SINGLE_ORANGE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        assertTrue(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void keepsDoubleOrangeWhenAttacking() {
        BattleCard battleCard = BattleCard.DOUBLE_ORANGE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        assertFalse(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void scrapsDoubleBlueWhenAttacking() {
        BattleCard battleCard = BattleCard.DOUBLE_BLUE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        assertTrue(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void scrapsBlueOrangeWhiteWhenAttacking() {
        BattleCard battleCard = BattleCard.BLUE_ORANGE_WHITE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(true);

        assertTrue(focusRule.shouldScrap(battleCard));
    }
}
