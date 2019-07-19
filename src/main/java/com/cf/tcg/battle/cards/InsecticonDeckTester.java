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

import static com.cf.tcg.battle.cards.BattleCardTypes.*;

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

        LOG.info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        LOG.info("Average Armor Bonus: {}", interpreter.getAverageArmorBonus());
        LOG.info("Average Pierce Bonus: {}", interpreter.getAveragePierceBonus());

    }

    public static Deck buildDeck() {
        Stack<BattleCard> battleCards = new Stack<>();

        // 6 Double Orange
        int numDoubleOrange = 6;
        for (int i = 0; i < numDoubleOrange; i++) {
            battleCards.push(DOUBLE_ORANGE);
        }

        // 3 White
        int numWhite = 3;
        for (int i = 0; i < numWhite; i++) {
            battleCards.push(SINGLE_WHITE);
        }

        // 3 Blank
        int numBlank = 3;
        for (int i = 0; i < numBlank; i++) {
            battleCards.push(BLANK);
        }

        // 2 Orange-Green
        int numOrangeGreen = 2;
        for (int i = 0; i < numOrangeGreen; i++) {
            battleCards.push(ORANGE_GREEN);
        }

        // 26 Single Orange
        int numSingleOrange = 26;
        for (int i = 0; i < numSingleOrange; i++) {
            battleCards.push(SINGLE_ORANGE);
        }

        Deck deck = new Deck(battleCards);
        deck.shuffleDeck();

        return deck;
    }
}
