package com.cf.tcg.battle;

import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class BattleFlipSimulator {

    public final Deck deck;
    public final int iterations;
    public final boolean resetDeckOnEachFlip;


    public BattleFlipSimulator(Deck deck, int iterations, boolean resetDeckOnEachFlip) {
        this.deck = deck;
        this.iterations = iterations;
        this.resetDeckOnEachFlip = resetDeckOnEachFlip;
    }

    public BattleFlipSimulator(Deck deck) {
        this(deck, 10_000, false);
    }

    public List<FlipResult> simulate() {
        return this.simulate(0, null);
    }

    public List<FlipResult> simulate(int extraCardFlips, FocusRule focusRule) {
        List<FlipResult> results = new ArrayList<>();

        for (int i = 0; i < this.iterations; i++) {
            results.add(this.getFlipResult(2 + extraCardFlips, focusRule));

            if (resetDeckOnEachFlip) {
                this.deck.resetDeck();
            }
        }

        return results;
    }

    public FlipResult getFlipResult(int flipCount, FocusRule focusRule) {
        if (focusRule != null) {
            BattleCard topCard = deck.peekCard();

            if (focusRule.shouldScrap(topCard)) {
                deck.scrapCardsFromTopOfDeck(1);
                LogManager.getLogger().debug("Focus scrapped {}", topCard);
            }
        }

        FlipResult result = deck.flipCards(flipCount, true);

        return result;
    }


}
