package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * @author David
 */
public class GenOPPrivateArceeFlamewarBalancedDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    public GenOPPrivateArceeFlamewarBalancedDeckTester() {
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runAttackSimulation(1, new ScrapOffColorFocusRule(1));
    }

    @Test
    @Order(2)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runDefenseSimulation(2, new ScrapOffColorFocusRule(1));
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
}
