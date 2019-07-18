package com.cf.tcg.battle.cards;

import com.cf.tcg.model.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsecticonDeckTesterTest {

    public InsecticonDeckTesterTest() {
    }

    @Test
    public void testBuildDeck() {
        Deck deck = InsecticonDeckTester.buildDeck();
        assertEquals(40, deck.getRemainingDeckCount());
    }
}
