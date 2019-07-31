package com.cf.tcg.battle;

import com.cf.tcg.model.BattleCard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author David
 */
public class DrawResult {

    private final FlipResult flipResult;

    public DrawResult(List<BattleCard> flippedCards) {
        this.flipResult = new FlipResult(flippedCards);
    }

    public DrawResult(BattleCard... flippedCards) {
        this(Arrays.asList(flippedCards));
    }

    public DrawResult() {
        this(new ArrayList<>());
    }

    public boolean addDrawnCard(BattleCard drawnCard) {
        return this.flipResult.addFlippedCard(drawnCard);
    }

    public List<BattleCard> getDrawnCards() {
        return this.flipResult.flippedCards;
    }

    public Map<BattleCard, Long> getDrawnCardsAsMap() {
        return this.flipResult.flippedCards.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
    }
}
