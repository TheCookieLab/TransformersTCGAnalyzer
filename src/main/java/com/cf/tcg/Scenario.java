package com.cf.tcg;

import com.cf.tcg.model.Pip;
import com.google.gson.Gson;

/**
 *
 * @author David
 */
public class Scenario {

    public final int deckSize;
    public final Pip desiredPip;
    public final int numOfDesiredPip;
    public final int desiredFlipCount;
    public final DrawScenario drawScenario;

    public Scenario(int deckSize, Pip desiredPip, int numOfDesiredPip, int desiredFlipCount, DrawScenario drawScenario) {
        this.deckSize = deckSize;
        this.desiredPip = desiredPip;
        this.drawScenario = drawScenario;
        this.numOfDesiredPip = numOfDesiredPip;
        this.desiredFlipCount = desiredFlipCount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
