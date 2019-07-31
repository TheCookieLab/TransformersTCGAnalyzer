package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
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
public class VSNewGenOp3WideDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public VSNewGenOp3WideDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new VSNewGenOp3WideDeckTester().runAttackSimulation();
    }

    @Test
    public void getDefenseStats() {
        new VSNewGenOp3WideDeckTester().runDefenseSimulation();
    }
    
    @Test
    public void getChancesOfTriggeringDragstripDrawAbility() {
        new VSNewGenOp3WideDeckTester().getChancesOfFlippingPips(Pip.ORANGE, Pip.BLUE);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withSingleOrangeCards(18)
                .withOrangeGreenCards(4)
                .withSingleBlueCards(5)
                .withBlueGreenCards(2)
                .withBlueOrangeCards(3)
                .withBlankCards(2)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public int getBold() {
        return 3;
    }

    @Override
    public int getTough() {
        return 1;
    }
}
