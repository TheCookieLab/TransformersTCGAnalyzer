package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;

public class ScrapSinglePipsFocusRule implements FocusRule {

    private boolean isAttacking;

    public ScrapSinglePipsFocusRule(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public void setAttacking() {
        this.isAttacking = true;
    }

    public void setDefending() {
        this.isAttacking = false;
    }

    @Override
    public boolean shouldScrap(BattleCard battleCard) {
        if (isAttacking) {
            return !(battleCard.getPipCount(Pip.ORANGE) > 1);
        } else {
            return !(battleCard.getPipCount(Pip.BLUE) > 1);
        }
    }
}
