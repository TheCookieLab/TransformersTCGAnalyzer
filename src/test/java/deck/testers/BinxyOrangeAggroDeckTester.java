package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author David
 */
public class BinxyOrangeAggroDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BinxyOrangeAggroDeckTester() {
    }

    @Test
    public void getAttackStatsForLionizer() {
        new BinxyOrangeAggroDeckTester().runAttackSimulation(5);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
                DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(7)
                .withSingleOrangeCards(21)
                .withOrangeGreenCards(8)
                .withSingleWhiteCards(4)
                .build();

        return new Deck(deckComp);
    }
}
