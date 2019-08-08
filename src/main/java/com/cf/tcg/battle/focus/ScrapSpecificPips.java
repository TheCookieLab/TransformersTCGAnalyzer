package com.cf.tcg.battle.focus;

import com.cf.tcg.model.Pip;
import com.cf.tcg.model.battle.card.BattleCard;

public class ScrapSpecificPips implements FocusRule {

    private boolean isAttacking;
    private final int focus;
    private final Pip[] desiredPips;

    public ScrapSpecificPips(int focus, Pip... desiredPips) {
        this.focus = focus;
        this.desiredPips = desiredPips;
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
            return !battleCard.pips.contains(Pip.ORANGE) || !battleCard.pips.contains(Pip.WHITE);
        } else {
            return !battleCard.pips.contains(Pip.BLUE) || !battleCard.pips.contains(Pip.WHITE);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.focus;
    }
}
