package com.cf.tcg;

import com.cf.tcg.model.Pip;
import com.cf.tcg.model.Deck;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class ScenarioResult {

    public final List<FlipResult> flipResults;
    public final Pip subjectPip;
    public final Deck deck;

    public ScenarioResult(Deck deck, Pip subjectPip) {
        this.flipResults = new ArrayList<>();
        this.subjectPip = subjectPip;
        this.deck = deck;
    }

    public boolean addFlipResult(FlipResult flipResult) {
        return this.flipResults.add(flipResult);
    }

    public BigDecimal getChancesOfFlippingEqualTo(int numberOfPips) {
        int count = 0;
        for (FlipResult flipResult : this.flipResults) {
            if (flipResult.getTotalNumberOfPipsFlipped(subjectPip) == numberOfPips) {
                count++;
            }
        }

        double percentage = count / this.flipResults.size();
        return BigDecimal.valueOf(percentage);
    }

    public Integer getTotalNumberOfPipsFlipped() {
        int count = 0;
        for (FlipResult flipResult : this.flipResults) {
            count += flipResult.getTotalNumberOfPipsFlipped(this.subjectPip);
        }
        return 0;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
