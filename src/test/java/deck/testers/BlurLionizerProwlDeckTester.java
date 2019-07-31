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
 * @author David
 */
public class BlurLionizerProwlDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BlurLionizerProwlDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new BlurLionizerProwlDeckTester().runAttackSimulation();
    }

    @Test
    public void getDefenseStats() {
        new BlurLionizerProwlDeckTester().runDefenseSimulation();
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withSingleOrangeCards(16)
                .withOrangeGreenCards(5)
                .withSingleWhiteCards(3)
                .withBlueOrangeCards(3)
                .withSingleBlueCards(3)
                .withBlankCards(4)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public int getBold() {
        return 6;
    }

    @Override
    public int getTough() {
        return 0;
    }
}
