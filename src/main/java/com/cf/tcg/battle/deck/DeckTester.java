
package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.BattleFlipSimulator;
import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.FlipResultInterpreter;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.battle.focus.ScrapSinglePipsFocusRule;
import com.cf.tcg.model.Deck;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;


/**
 * @author David
 */
public interface DeckTester {
    public default void runAttackSimulation() {
        Deck deck = buildDeck();
        getLogger().info("Deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(getBold(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        getLogger().info("Chance of adding less than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThan(2)));
        getLogger().info("Chance of adding more than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }

    public default void runDefenseSimulation() {
        Deck deck = buildDeck();
        getLogger().info("Deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setDefending();

        List<FlipResult> flipResults = simulator.simulate(getTough(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Armor Bonus: {}", interpreter.getAverageArmorBonus());
        getLogger().info("Chance of adding less than 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusIsLessThan(2)));
        getLogger().info("Chance of adding more than 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }


    public Logger getLogger();

    public Deck buildDeck();

    public default FocusRule getFocusRule() {
        return new NoOpFocusRule();
    }

    public default int getBold() {
        return 0;
    }

    public default int getTough() {
        return 0;
    }

}
