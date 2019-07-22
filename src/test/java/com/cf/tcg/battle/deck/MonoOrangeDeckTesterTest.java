package com.cf.tcg.battle.deck;

import com.cf.tcg.model.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonoOrangeDeckTesterTest {

    public MonoOrangeDeckTesterTest() {
    }

    @Test
    public void testBuildDeckHas40Cards() {
        Deck deck = new MonoOrangeDeckTester().buildDeck();
        assertEquals(40, deck.getRemainingDeckCount());
    }
}
