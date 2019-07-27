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
public class BalancedPipDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BalancedPipDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new BalancedPipDeckTester().runAttackSimulation();
    }

    @Test
    public void getDefenseStats() {
        new BalancedPipDeckTester().runDefenseSimulation();
    }
    
    @Test
    public void getChancesOfTriggeringDragstripDrawAbility() {
        new BalancedPipDeckTester().getChancesOfFlippingPips(Pip.ORANGE, Pip.BLUE);
    }
    
    @Test
    public void getChancesOfTriggeringMetroplexAbility1() {
        new BalancedPipDeckTester().getChancesOfFlippingPips(Pip.ORANGE, Pip.ORANGE, Pip.BLUE, Pip.BLUE, Pip.WHITE, Pip.WHITE);
    }
    
    @Test
    public void getChancesOfTriggeringMetroplexAbilityOriginal() {
        new BalancedPipDeckTester().runMetroplexAbilityOdds();
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
                .withSingleOrangeCards(10)
                .withSingleBlueCards(10)
                .withSingleWhiteCards(7)
                .withWhiteOrangeBlueCards(1)
                .build();

        return new Deck(deckComp);
    }

    @Override
    public int getBold() {
        return 0;
    }

    @Override
    public int getTough() {
        return 0;
    }
}
