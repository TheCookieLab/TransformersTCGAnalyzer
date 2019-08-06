package com.cf.tcg.battle;

import com.cf.tcg.model.Deck;
import deck.testers.BlurLionizerProwlDeckTester;
import org.apache.logging.log4j.LogManager;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DrawSimulatorTest {

    public DrawSimulatorTest() {
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
