package com.cf.tcg.battle.focus;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Pip;

public class ScrapSinglePipsKeepWhiteFocusRule implements FocusRule {

    private boolean isAttacking;
    private final int focus;

    public ScrapSinglePipsKeepWhiteFocusRule(int focus) {
        this.focus = focus;
    }

    @Override
    public void setAttacking() {
        this.isAttacking = true;
    }

    @Override
    public void setDefending() {
        this.isAttacking = false;
    }

    @Override
    public int getFocus() {
        return focus;
    }

    @Override
    public boolean shouldScrap(BattleCard battleCard) {
        if (isAttacking) {
            return !(battleCard.getPipCount(Pip.ORANGE) > 1) || !battleCard.pips.contains(Pip.WHITE);
        } else {
            return !(battleCard.getPipCount(Pip.BLUE) > 1) || !battleCard.pips.contains(Pip.WHITE);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.focus;
    }
}
