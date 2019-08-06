package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.*;
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

    public default void runAttackSimulation(int bold) {
        this.runAttackSimulation(bold, new NoOpFocusRule());
    }

    public default void runAttackSimulation(int bold, FocusRule focusRule) {
        Deck deck = buildDeck();
        getLogger().info("Running offensive stats simulation for Bold {}, {} Focus rule, and deck: {}", bold, focusRule, deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        getLogger().info("Chance of adding less than or equal to 1 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThanOrEqualTo(1)));
        getLogger().info("Chance of adding less than or equal to 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThanOrEqualTo(2)));
        getLogger().info("Chance of adding more than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));

        getLogger().info("Average Pierce Bonus: {}", interpreter.getAveragePierceBonus());
    }

    public default void runDefenseSimulation(int tough) {
        this.runDefenseSimulation(tough, new NoOpFocusRule());
    }

    public default void runDefenseSimulation(int tough, FocusRule focusRule) {
        Deck deck = buildDeck();
        getLogger().info("Running offensive stats simulation for Tough {}, {} Focus rule, and deck: {}", tough, focusRule, deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        focusRule.setDefending();

        List<FlipResult> flipResults = simulator.simulate(tough, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Armor Bonus: {}", interpreter.getAverageArmorBonus());
        getLogger().info("Chance of adding less than or equal to 1 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusIsLessThanOrEqualTo(1)));
        getLogger().info("Chance of adding less than or equal to 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusIsLessThanOrEqualTo(2)));
        getLogger().info("Chance of adding more than 2 armor: {}", numberFormat.format(interpreter.getChanceArmorBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }

    public default void getChancesOfFlippingPips(int bold, Pip... pips) {
        this.getChancesOfFlippingPips(bold, new NoOpFocusRule(), pips);
    }

    public default void getChancesOfFlippingPips(int bold, FocusRule focusRule, Pip... pips) {
        Deck deck = buildDeck();
        getLogger().info("Running chances of flipping {} with focus rule {} and deck: {}", pips, focusRule, deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of flipping {}: {}", pips, numberFormat.format(interpreter.getChanceOfFlippingPips(pips)));
    }

    public default void runMetroplexAbilityOdds(int bold) {
        this.runMetroplexAbilityOdds(bold, new NoOpFocusRule());
    }

    public default void runMetroplexAbilityOdds(int bold, FocusRule focusRule) {
        Deck deck = buildDeck();
        getLogger().info("Running chances of triggering Metroplex Bot-mode ability for deck: {}", deck);
        deck.shuffleDeck();

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of triggering Metroplex bot-mode ability: {}", numberFormat.format(interpreter.getChanceOfTriggeringMetroplexBotAbility()));
    }

    public default void runInitialDrawOdds(int numberOfTurns) {
        Deck deck = buildDeck();
        getLogger().info("Running chances of drawing each card in deck: {}", deck);
        deck.shuffleDeck();

        DrawSimulator simulator = new DrawSimulator(deck);

        List<Hand> hands = simulator.simulate(numberOfTurns);

    }

    public Logger getLogger();

    public Deck buildDeck();
}
