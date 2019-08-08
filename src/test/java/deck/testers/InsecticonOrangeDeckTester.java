package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.*;

/**
 * @author David
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsecticonOrangeDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public InsecticonOrangeDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new InsecticonOrangeDeckTester().runAttackSimulation(0);
    }

    @Test
    @Order(2)
    public void getAttackStatsForKickback(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new InsecticonOrangeDeckTester().runAttackSimulation(6);
    }

    @Test
    @Order(3)
    public void getAttackStatsForBarrageAttackingDamagedTarget(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new InsecticonOrangeDeckTester().runAttackSimulation(2);
    }

    @Test
    @Order(5)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
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
