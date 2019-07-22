package com.cf.tcg.battle.focus;

import com.cf.tcg.model.BattleCard;

public interface FocusRule {
    public boolean shouldScrap(BattleCard battleCard);
}
