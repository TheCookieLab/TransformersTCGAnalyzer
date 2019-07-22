package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;

public class ScrapSinglePipsKeepWhiteFocusRule implements FocusRule {

    private boolean isAttacking;

    public ScrapSinglePipsKeepWhiteFocusRule(boolean isAttacking) {
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
            return !(battleCard.getPipCount(Pip.ORANGE) > 1) || !battleCard.pips.contains(Pip.WHITE);
        } else {
            return !(battleCard.getPipCount(Pip.BLUE) > 1) || !battleCard.pips.contains(Pip.WHITE);
        }
    }
}
