package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.deck.InsecticonDeckTester;
import com.cf.tcg.model.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsecticonDeckTesterTest {

    public InsecticonDeckTesterTest() {
    }

    @Test
    public void testBuildDeck() {
        Integer doubleOrange = 6;
        Integer OrangeGreen = 2;
        Integer singleWhite = 3;
        Integer blank = 3;
        Integer singleOrange = 28;

        Deck deck = InsecticonDeckTester.buildDeck(doubleOrange, OrangeGreen, singleWhite, blank, singleOrange);
        assertEquals(40, deck.getRemainingDeckCount());
    }
}
