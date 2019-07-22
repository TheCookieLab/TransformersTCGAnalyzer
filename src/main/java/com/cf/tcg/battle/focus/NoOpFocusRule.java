package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;

public class NoOpFocusRule implements FocusRule {


    public NoOpFocusRule() {
    }

    public void setAttacking() {
    }

    public void setDefending() {
    }

    @Override
    public int getFocus() {
        return 0;
    }

    @Override
    public boolean shouldScrap(BattleCard battleCard) {
        return false;
    }
}
