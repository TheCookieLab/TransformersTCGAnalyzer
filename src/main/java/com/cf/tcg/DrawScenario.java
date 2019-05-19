package com.cf.tcg;

/**
 *
 * @author David
 */
public enum DrawScenario {
    INITIAL_DRAW(3),
    BATTLE(2),
    TURN(1);

    public final int cardsToDraw;

    DrawScenario(int cardsToDraw) {
        this.cardsToDraw = cardsToDraw;
    }

    public int getCardsToDraw() {
        return this.cardsToDraw;
    }

}
