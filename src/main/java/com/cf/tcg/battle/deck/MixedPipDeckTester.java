package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MixedPipDeckTester implements DeckTester {

    public final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        new MixedPipDeckTester().runAttackSimulation();
        new MixedPipDeckTester().runDefenseSimulation();
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        Integer doubleOrange = 6;
        Integer doubleBlue = 6;
        Integer orangeGreen = 3;
        Integer blueGreen = 3;
        Integer singleWhite = 2;
        Integer singleOrange = 4;
        Integer singleBlue = 9;
        Integer blueOrange = 6;
        Integer whiteOrangeBlue = 1;

        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(doubleOrange)
                .withDoubleBlueCards(doubleBlue)
                .withSingleOrangeCards(singleOrange)
                .withSingleBlueCards(singleBlue)
                .withSingleWhiteCards(singleWhite)
                .withOrangeGreenCards(orangeGreen)
                .withBlueGreenCards(blueGreen)
                .withBlueOrangeCards(blueOrange)
                .withWhiteOrangeBlueCards(whiteOrangeBlue)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public FocusRule getFocusRule() {
        return new ScrapOffColorFocusRule(1);
    }

    public int getBold() {
        return 1;
    }

    ;

    public int getTough() {
        return 2;
    }
}
