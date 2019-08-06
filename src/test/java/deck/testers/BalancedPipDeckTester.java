package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author David
 */
public class BalancedPipDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();    

    public BalancedPipDeckTester() {
    }

    @Test
    public void getAttackStats() {
        new BalancedPipDeckTester().runAttackSimulation(0);
    }

    @Test
    public void getDefenseStats() {
        new BalancedPipDeckTester().runDefenseSimulation(0);
    }
    
    @Test
    public void getChancesOfTriggeringDragstripDrawAbility() {
        new BalancedPipDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.BLUE);
    }
    
    @Test
    public void getChancesOfTriggeringMetroplexAbility1() {
        new BalancedPipDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.ORANGE, Pip.BLUE, Pip.BLUE, Pip.WHITE, Pip.WHITE);
    }
    
    @Test
    public void getChancesOfTriggeringMetroplexAbilityOriginal() {
        new BalancedPipDeckTester().runMetroplexAbilityOdds(0);
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
}
