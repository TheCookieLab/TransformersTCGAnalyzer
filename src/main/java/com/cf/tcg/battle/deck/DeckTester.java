package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.*;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.model.battle.card.BattleCard;
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

    /**
     * *
     *
     * @param bold
     */
    public default void runAttackSimulation(int bold) {
        this.runAttackSimulation(bold, new NoOpFocusRule());
    }

    /**
     * *
     *
     * @param bold
     * @param focusRule
     */
    public default void runAttackSimulation(int bold, FocusRule focusRule) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running offensive stats simulation for Bold {}, {} Focus rule, and deck: {}", bold, focusRule, deck);

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

    /**
     * *
     *
     * @param tough
     */
    public default void runDefenseSimulation(int tough) {
        this.runDefenseSimulation(tough, new NoOpFocusRule());
    }

    /**
     * *
     *
     * @param tough
     * @param focusRule
     */
    public default void runDefenseSimulation(int tough, FocusRule focusRule) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running offensive stats simulation for Tough {}, {} Focus rule, and deck: {}", tough, focusRule, deck);

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

    /**
     * *
     *
     * @param bold
     * @param pips
     */
    public default void getChancesOfFlippingPips(int bold, Pip... pips) {
        this.getChancesOfFlippingPips(bold, new NoOpFocusRule(), pips);
    }

    /**
     * *
     *
     * @param bold
     * @param focusRule
     * @param pips
     */
    public default void getChancesOfFlippingPips(int bold, FocusRule focusRule, Pip... pips) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running chances of flipping {} with focus rule {} and deck: {}", pips, focusRule, deck);

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of flipping {}: {}", pips, numberFormat.format(interpreter.getChanceOfFlippingPips(pips)));
    }

    /**
     * *
     *
     * @param bold
     */
    public default void runMetroplexAbilityOdds(int bold) {
        this.runMetroplexAbilityOdds(bold, new NoOpFocusRule());
    }

    /**
     * *
     *
     * @param bold
     * @param focusRule
     */
    public default void runMetroplexAbilityOdds(int bold, FocusRule focusRule) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running chances of triggering Metroplex Bot-mode ability for deck: {}", deck);

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of triggering Metroplex bot-mode ability: {}", numberFormat.format(interpreter.getChanceOfTriggeringMetroplexBotAbility()));
    }

    /**
     * *
     *
     * @param turn
     * @param battleCards
     */
    public default void getChancesOfHavingAllCardsOnTurn(int turn, BattleCard... battleCards) {
        this.getChancesOfHavingAllCardsOnTurn(turn, 0, 0, battleCards);
    }

    /**
     * *
     *
     * @param turn
     * @param bold
     * @param tough
     * @param battleCards
     */
    public default void getChancesOfHavingAllCardsOnTurn(int turn, int bold, int tough, BattleCard... battleCards) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running chances of having all {} in hand on turn {} for deck {}", battleCards, turn, deck);

        DrawSimulator drawSimulator = new DrawSimulator(deck);
        List<Hand> hands = drawSimulator.simulate(turn, bold, tough);

        HandResultInterpreter interpreter = new HandResultInterpreter(deck, hands);
        Double probability = interpreter.getChanceOfHavingAllCards(battleCards);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Probability of having all of {} in hand on turn {} is: {}", battleCards, turn, numberFormat.format(probability));
    }

    /**
     * *
     *
     * @param turn
     * @param battleCards
     */
    public default void getChancesOfHavingAnyCardsOnTurn(int turn, BattleCard... battleCards) {
        this.getChancesOfHavingAnyCardsOnTurn(turn, 0, 0, battleCards);
    }

    /**
     * *
     *
     * @param turn
     * @param bold
     * @param tough
     * @param battleCards
     */
    public default void getChancesOfHavingAnyCardsOnTurn(int turn, int bold, int tough, BattleCard... battleCards) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running chances of having any {} in hand on turn {} for deck {}", battleCards, turn, deck);

        DrawSimulator drawSimulator = new DrawSimulator(deck);
        List<Hand> hands = drawSimulator.simulate(turn, bold, tough);

        HandResultInterpreter interpreter = new HandResultInterpreter(deck, hands);
        Double probability = interpreter.getChanceOfHavingAnyOfCards(battleCards);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Probability of having any of {} in hand on turn {} is: {}", battleCards, turn, numberFormat.format(probability));
    }

    public Logger getLogger();

    public Deck buildDeck();
}
