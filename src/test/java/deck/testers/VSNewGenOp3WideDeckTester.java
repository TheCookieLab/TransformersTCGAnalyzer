package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.*;

/**
 * @author David
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VSNewGenOp3WideDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public VSNewGenOp3WideDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new VSNewGenOp3WideDeckTester().runAttackSimulation(1);
    }

    @Test
    @Order(2)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new VSNewGenOp3WideDeckTester().runDefenseSimulation(1);
    }

    @Test
    @Order(3)
    public void getChancesOfTriggeringDragstripDrawAbility(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new VSNewGenOp3WideDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.BLUE);
    }

    @Test
    @Order(4)
    public void getChancesOfTriggeringDragstripDrawAbilityWithBold1(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new VSNewGenOp3WideDeckTester().getChancesOfFlippingPips(1, Pip.ORANGE, Pip.BLUE);
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
}
