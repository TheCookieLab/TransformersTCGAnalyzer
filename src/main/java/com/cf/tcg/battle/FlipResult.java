package com.cf.tcg.battle;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class FlipResult {

    public final List<BattleCard> flippedCards;

    public FlipResult(List<BattleCard> flippedCards) {
        this.flippedCards = flippedCards;
    }

    public FlipResult(BattleCard... flippedCards) {
        this(Arrays.asList(flippedCards));
    }

    public FlipResult() {
        this(new ArrayList<>());
    }

    public boolean addFlippedCard(BattleCard flippedCard) {
        return this.flippedCards.add(flippedCard);
    }

    public Integer getTotalNumberOfPipsFlipped(Pip pip) {
        int count = 0;
        for (BattleCard battleCard : this.flippedCards) {
            count += battleCard.pips.stream().filter((flippedPip) -> {
                return flippedPip == pip;
            }).count();
        }

        return count;
    }

    public Integer getTotalAttackBonus() {
        return this.getTotalNumberOfPipsFlipped(Pip.ORANGE);
    }

    public Integer getTotalDefenseBonus() {
        return this.getTotalNumberOfPipsFlipped(Pip.BLUE);
    }

    public Integer getTotalPierceBonus() {
        return this.getTotalNumberOfPipsFlipped(Pip.BLACK);
    }

    public Integer getTotalNumberOfCardsFlipped() {
        return this.flippedCards.size();
    }
}