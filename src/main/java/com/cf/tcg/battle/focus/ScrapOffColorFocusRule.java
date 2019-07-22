package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;

public class ScrapOffColorFocusRule implements FocusRule {

    private boolean isAttacking;

    public ScrapOffColorFocusRule(boolean isAttacking) {
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
            return !battleCard.pips.contains(Pip.ORANGE);
        } else {
            return !battleCard.pips.contains(Pip.BLUE);
        }
    }
}
