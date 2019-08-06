package com.cf.tcg.battle.focus;

import com.cf.tcg.model.battle.card.BattleCard;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ScrapSinglePipsFocusRuleTest {

    public ScrapSinglePipsFocusRuleTest() {

    }

    @Test
    public void scrapsSingleOrangeWhenAttacking() {
        BattleCard battleCard = BattleCard.SINGLE_ORANGE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(1);
        focusRule.setAttacking();

        assertTrue(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void keepsDoubleOrangeWhenAttacking() {
        BattleCard battleCard = BattleCard.DOUBLE_ORANGE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(1);
        focusRule.setAttacking();

        assertFalse(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void scrapsDoubleBlueWhenAttacking() {
        BattleCard battleCard = BattleCard.DOUBLE_BLUE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(1);
        focusRule.setAttacking();

        assertTrue(focusRule.shouldScrap(battleCard));
    }

    @Test
    public void scrapsBlueOrangeWhiteWhenAttacking() {
        BattleCard battleCard = BattleCard.BLUE_ORANGE_WHITE;

        ScrapSinglePipsFocusRule focusRule = new ScrapSinglePipsFocusRule(1);
        focusRule.setAttacking();

        assertTrue(focusRule.shouldScrap(battleCard));
    }
}
