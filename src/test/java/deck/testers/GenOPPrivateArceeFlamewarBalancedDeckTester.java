package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
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
public class GenOPPrivateArceeFlamewarBalancedDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public GenOPPrivateArceeFlamewarBalancedDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runAttackSimulation();
    }

    @Test
    public void getDefenseStats() {
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runDefenseSimulation();
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {

        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withDoubleBlueCards(6)
                .withSingleOrangeCards(3)
                .withBlueGreenCards(3)
                .withOrangeGreenCards(4)
                .withSingleBlueCards(10)
                .withSingleWhiteCards(1)
                .withBlueOrangeCards(6)
                .withWhiteOrangeBlueCards(1)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public FocusRule getFocusRule() {
        return new ScrapOffColorFocusRule(1);
    }

    @Override
    public int getBold() {
        return 1;
    }

    @Override
    public int getTough() {
        return 2;
    }
}
