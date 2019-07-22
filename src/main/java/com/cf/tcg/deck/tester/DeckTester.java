
package com.cf.tcg.deck.tester;

import com.cf.tcg.BattleFlipSimulator;
import com.cf.tcg.FlipResult;
import com.cf.tcg.FlipResultInterpreter;
import com.cf.tcg.model.Deck;
import java.util.List;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author David
 */
public interface DeckTester {
    public default void run() {
        Deck deck = buildDeck();
        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);

        List<FlipResult> flipResults = simulator.simulate();
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        getLogger().info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
    }
    
    public Logger getLogger();

    public Deck buildDeck();
    
}
