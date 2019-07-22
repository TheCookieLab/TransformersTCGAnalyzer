
package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.BattleFlipSimulator;
import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.FlipResultInterpreter;
import com.cf.tcg.model.Deck;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;


/**
 *
 * @author David
 */
public interface DeckTester {
    public default void run() {
        Deck deck = buildDeck();
        getLogger().info("Deck: {}", deck);

        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        List<FlipResult> flipResults = simulator.simulate();
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

        getLogger().info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        getLogger().info("Chance of adding less than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThan(2)));
        getLogger().info("Chance of adding more than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusGreaterThan(2)));
        getLogger().info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }
    
    public Logger getLogger();

    public Deck buildDeck();
    
}
