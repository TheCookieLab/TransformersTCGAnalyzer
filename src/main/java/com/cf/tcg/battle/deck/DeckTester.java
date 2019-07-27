
package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.BattleFlipSimulator;
import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.FlipResultInterpreter;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;

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
        getLogger().info("Running Offensive Stats for Deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(getBold(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        getLogger().info("Chance of adding less than or equal to 1 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThanOrEqualTo(1)));
        getLogger().info("Chance of adding less than or equal to 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThanOrEqualTo(2)));
        getLogger().info("Chance of adding more than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));

        getLogger().info("Average Pierce Bonus: {}", interpreter.getAveragePierceBonus());
    }

    public default void runDefenseSimulation() {
        Deck deck = buildDeck();
        getLogger().info("Running Defensive Stats for Deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setDefending();

        List<FlipResult> flipResults = simulator.simulate(getTough(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Armor Bonus: {}", interpreter.getAverageArmorBonus());
        getLogger().info("Chance of adding less than or equal to 1 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusIsLessThanOrEqualTo(1)));
        getLogger().info("Chance of adding less than or equal to 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusIsLessThanOrEqualTo(2)));
        getLogger().info("Chance of adding more than 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }
    
    public default void getChancesOfFlippingPips(Pip...pips) {
        Deck deck = buildDeck();
        getLogger().info("Running chances of flipping {} with deck: {}", pips, deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(getBold(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of flipping {}: {}", pips, numberFormat.format(interpreter.getChanceOfFlippingPips(pips)));
    }
    
    public default void runMetroplexAbilityOdds() {
        Deck deck = buildDeck();
        getLogger().info("Running chances of triggering Metroplex Bot-mode ability for Deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        FocusRule focusRule = getFocusRule();
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(getBold(), focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of triggering Metroplex bot-mode ability: {}", numberFormat.format(interpreter.getChanceOfTriggeringMetroplexBotAbility()));
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
