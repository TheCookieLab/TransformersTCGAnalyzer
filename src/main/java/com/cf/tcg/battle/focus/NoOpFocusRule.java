package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;

public class NoOpFocusRule implements FocusRule {


    public NoOpFocusRule() {
    }

    @Override
    public void setAttacking() {
    }

    @Override
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
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
