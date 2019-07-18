package com.cf.tcg.battle.cards;

import com.cf.tcg.BattleFlipAnalyzer;
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
        battleCards.push(DOUBLE_ORANGE);
        battleCards.push(DOUBLE_ORANGE);
        battleCards.push(DOUBLE_ORANGE);
        battleCards.push(DOUBLE_ORANGE);
        battleCards.push(DOUBLE_ORANGE);
        battleCards.push(DOUBLE_ORANGE);

        // 3 White
        battleCards.push(SINGLE_WHITE);
        battleCards.push(SINGLE_WHITE);
        battleCards.push(SINGLE_WHITE);

        // 3 Blank
        battleCards.push(BLANK);
        battleCards.push(BLANK);
        battleCards.push(BLANK);

        // 2 Orange-Green
        battleCards.push(ORANGE_GREEN);
        battleCards.push(ORANGE_GREEN);

        // 26 Single Orange
        for (int i = 0; i < 26; i++) {
            battleCards.push(SINGLE_ORANGE);
        }

        Deck deck = new Deck(battleCards);
        deck.shuffleDeck();

        return deck;
    }
}
