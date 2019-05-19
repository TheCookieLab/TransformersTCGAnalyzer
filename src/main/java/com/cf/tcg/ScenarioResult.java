package com.cf.tcg;

import com.cf.tcg.model.Pip;
import com.cf.tcg.model.Deck;
import com.google.gson.Gson;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
