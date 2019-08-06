package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class InsecticonOrangeDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public InsecticonOrangeDeckTester() {
    }

    @Test
    public void getAttackStats() {
        new InsecticonOrangeDeckTester().runAttackSimulation(0);
    }

    @Test
    public void getDefenseStats() {
        new InsecticonOrangeDeckTester().runDefenseSimulation(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withSingleOrangeCards(26)
                .withOrangeGreenCards(2)
                .withSingleWhiteCards(3)
                .withBlankCards(3)
                .build();
        return new Deck(deckComp);
    }
}
