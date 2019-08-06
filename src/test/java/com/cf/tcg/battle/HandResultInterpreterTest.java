package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;
import deck.testers.BlurLionizerProwlDeckTester;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HandResultInterpreterTest {

    public HandResultInterpreterTest() {
    }

    @Test
    public void testGetChanceOfHaving3OfCardInOpeningHand() {
        Deck deck = new BlurLionizerProwlDeckTester().buildDeck();
        int iterations = 100;
        DrawSimulator drawSimulator = new DrawSimulator(deck, iterations);

        int referenceTurn = 1;
        List<Hand> hands = drawSimulator.simulate(referenceTurn);

        HandResultInterpreter interpreter = new HandResultInterpreter(deck, hands);
        assertEquals(hands.size(), interpreter.getCount().intValue());

        Double chanceOfHavingForceFieldInOpeningHand = interpreter.getChanceOfHavingCard(BattleCard.FORCE_FIELD);
        assertEquals(0.276, chanceOfHavingForceFieldInOpeningHand, 0.1);
    }

    @Test
    public void testChanceOfHavingCardIs100() {
        LinkedList<BattleCard> battleCards = new LinkedList<>();

        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.BASHING_SHIELD);
        battleCards.push(BattleCard.BASHING_SHIELD);

        Deck deck = new Deck(battleCards);

        int iterations = 100;
        DrawSimulator drawSimulator = new DrawSimulator(deck, iterations);

        int referenceTurn = 1;
        List<Hand> hands = drawSimulator.simulate(referenceTurn);

        HandResultInterpreter interpreter = new HandResultInterpreter(deck, hands);

        Double chanceOfHavingForceField = interpreter.getChanceOfHavingCard(BattleCard.FORCE_FIELD);
        assertEquals(0, chanceOfHavingForceField, 0.001);

        Double chanceOfHavingBashingShield = interpreter.getChanceOfHavingCard(BattleCard.BASHING_SHIELD);
        assertEquals(1, chanceOfHavingBashingShield, 0.001);

        Double chanceOfHavingOrangeGreenCard = interpreter.getChanceOfHavingCard(BattleCard.ORANGE_GREEN);
        assertEquals(1, chanceOfHavingOrangeGreenCard, 0.001);
    }

}
