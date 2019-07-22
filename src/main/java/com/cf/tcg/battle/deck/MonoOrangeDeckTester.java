package com.cf.tcg.battle.deck;

import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MonoOrangeDeckTester implements DeckTester {

    private final static Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        new MonoOrangeDeckTester().runAttackSimulation();
    }

    public Deck buildDeck() {
        Integer doubleOrange = 6;
        Integer OrangeGreen = 2;
        Integer singleWhite = 3;
        Integer blank = 3;
        Integer singleOrange = 26;

        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(doubleOrange)
                .withSingleOrangeCards(singleOrange)
                .withOrangeGreenCards(OrangeGreen)
                .withSingleWhiteCards(singleWhite)
                .withBlankCards(blank)
                .build();

        return new Deck(deckComp);
    }

    public Logger getLogger() {
        return LOG;
    }

    @Override
    public int getBold() {
        return 0;
    }
}
