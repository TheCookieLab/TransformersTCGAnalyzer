package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author David
 */
public class BinxyOrangeAggroDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BinxyOrangeAggroDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new BinxyOrangeAggroDeckTester().runAttackSimulation();
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
//        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
//                .withDoubleOrangeCards(7)
//                .withSingleOrangeCards(21)
//                .withOrangeGreenCards(9)
//                .withSingleWhiteCards(3)
//                .build();
        
                DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(7)
                .withSingleOrangeCards(21)
                .withOrangeGreenCards(8)
                .withSingleWhiteCards(4)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public int getBold() {
        return 5;
    }

    @Override
    public int getTough() {
        return 1;
    }
}
