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
        double averageDamageBonus = interpreter.getAverageDamageBonus();
        getLogger().info("Average Damage Bonus (Bold {}): {}", bold, averageDamageBonus);

        for (int i = 0; i <= Math.ceil(averageDamageBonus); i++) {
            double chance = interpreter.getChanceDamageBonusIsLessThanOrEqualTo(i);

            if (chance >= 0.01) {
                getLogger().info("Chance of adding less than or equal to {} damage: {}", i, numberFormat.format(chance));
            }
        }

        double averagePierceBonus = interpreter.getAveragePierceBonus();
        getLogger().info("Average Pierce Bonus (Bold {}): {}", bold, averagePierceBonus);

        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
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

        getLogger().debug("Running defensive stats simulation for Tough {}, {} Focus rule, and deck: {}", tough, focusRule, deck);

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        focusRule.setDefending();

        List<FlipResult> flipResults = simulator.simulate(tough, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        double averageArmorBonus = interpreter.getAverageArmorBonus();
        getLogger().info("Average Armor Bonus (Tough {}): {}", tough, averageArmorBonus);

        for (int i = 0; i <= Math.ceil(averageArmorBonus); i++) {
            double chance = interpreter.getChanceArmorBonusIsLessThanOrEqualTo(i);

            if (chance >= 0.01) {
                getLogger().info("Chance of adding less than or equal to {} armor: {}", i, numberFormat.format(chance));
            }
        }

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

        getLogger().debug("Running chances of flipping {} with Bold {}, focus rule {} and deck: {}", pips, bold, focusRule, deck);

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        focusRule.setAttacking();

        List<FlipResult> flipResults = simulator.simulate(bold, focusRule);
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Chance of flipping {} with Bold {}, and focus rule {} : {}", pips, bold, focusRule, numberFormat.format(interpreter.getChanceOfFlippingPips(pips)));
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

    public default void getChanceOfHavingCombinationCardsOnTurn(int turn, int bold, int tough, List<BattleCard> allOfBattleCards, List<BattleCard> anyOfBattleCards) {
        Deck deck = buildDeck();
        deck.shuffleDeck();

        getLogger().debug("Running chances of having all of {} and any of {} in hand on turn {} for deck {}", allOfBattleCards, anyOfBattleCards, turn, deck);

        DrawSimulator drawSimulator = new DrawSimulator(deck);
        List<Hand> hands = drawSimulator.simulate(turn, bold, tough);

        HandResultInterpreter interpreter = new HandResultInterpreter(deck, hands);
        Double probability = interpreter.getChanceOfHavingCombinationOfCards(allOfBattleCards, anyOfBattleCards);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
        getLogger().info("Running chances of having all of {} and any of {} in hand on turn {} for deck {}", allOfBattleCards, anyOfBattleCards, turn, numberFormat.format(probability));
    }

    public Logger getLogger();

    public Deck buildDeck();
}
