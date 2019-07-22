package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;

public class ScrapOffColorKeepWhitesFocusRule implements FocusRule {

    private boolean isAttacking;

    public ScrapOffColorKeepWhitesFocusRule(boolean isAttacking) {
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
            return !battleCard.pips.contains(Pip.ORANGE) || !battleCard.pips.contains(Pip.WHITE);
        } else {
            return !battleCard.pips.contains(Pip.BLUE) || !battleCard.pips.contains(Pip.WHITE);
        }
    }
}
