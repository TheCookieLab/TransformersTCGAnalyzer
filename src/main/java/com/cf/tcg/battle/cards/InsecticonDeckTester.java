package com.cf.tcg.battle.cards;

import com.cf.tcg.BattleFlipSimulator;
import com.cf.tcg.FlipResult;
import com.cf.tcg.FlipResultInterpreter;
import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Stack;

public class InsecticonDeckTester {

    private final static Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        new InsecticonDeckTester().run();
    }

    public void run() {
        Deck deck = buildDeck();
        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        List<FlipResult> flipResults = simulator.simulate();
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        LOG.info("Deck: {}", deck);
        LOG.info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        LOG.info("Damage Bonus Variance: {}", interpreter.getDamageBonusVariance());
    }

    public static Deck buildDeck() {
        Stack<BattleCard> battleCards = new Stack<>();

        int totalCards = 40;
        int numDoubleOrange = 6;
        int numOrangeGreen = 2;
        int numWhite = 5;
        int numBlank = 3;

        for (int i = 0; i < numDoubleOrange; i++) {
            battleCards.push(BattleCard.DOUBLE_ORANGE);
        }

        for (int i = 0; i < numWhite; i++) {
            battleCards.push(BattleCard.SINGLE_WHITE);
        }

        for (int i = 0; i < numBlank; i++) {
            battleCards.push(BattleCard.BLANK);
        }

        for (int i = 0; i < numOrangeGreen; i++) {
            battleCards.push(BattleCard.ORANGE_GREEN);
        }

        int numSingleOrange = totalCards - numDoubleOrange - numOrangeGreen - numWhite - numBlank;
        for (int i = 0; i < numSingleOrange; i++) {
            battleCards.push(BattleCard.SINGLE_ORANGE);
        }

        Deck deck = new Deck(battleCards);
        deck.shuffleDeck();

        return deck;
    }
}