package com.cf.tcg.battle;

import com.cf.tcg.model.Deck;
import deck.testers.BlurLionizerProwlDeckTester;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import static org.junit.Assert.assertEquals;

public class DrawSimulatorTest {
    public DrawSimulatorTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimulateReturnsCorrectHandResults() {
        Deck deck = new BlurLionizerProwlDeckTester().buildDeck();
        int iterations = 10;
        DrawSimulator drawSimulator = new DrawSimulator(deck, iterations);

        int referenceTurn = 1;
        List<Hand> hands = drawSimulator.simulate(referenceTurn);

        assertEquals(iterations, hands.size());

        for (Hand hand : hands) {
            assertEquals(3 + referenceTurn, hand.getCount().intValue());
            LogManager.getLogger().debug(hand);
        }
    }


}
