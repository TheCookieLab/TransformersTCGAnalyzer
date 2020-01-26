package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.*;

/**
 * @author David
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AristocarsDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public AristocarsDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new AristocarsDeckTester().runAttackSimulation(0);
    }

    @Test
    @Order(2)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new AristocarsDeckTester().runDefenseSimulation(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.ENERGON_AXE, 2)
                .withBattleCard(BattleCard.GRENADE_LAUNCHER, 3)
                .withBattleCard(BattleCard.NOBLES_BLASTER, 2)
                .withBattleCard(BattleCard.HANDHELD_BLASTER, 3)
                .withBattleCard(BattleCard.REINFORCED_PLATING, 3)
                .withBattleCard(BattleCard.SPARRING_GEAR, 1)
                .withBattleCard(BattleCard.BASHING_SHIELD, 1)
                .withBattleCard(BattleCard.FORCE_FIELD, 2)
                .withBattleCard(BattleCard.MATRIX_OF_LEADERSHIP, 3)
                .withBattleCard(BattleCard.STURDY_JAVELIN, 3)
                .withBattleCard(BattleCard.SABOTAGED_ARMAMENTS, 3)
                .withBattleCard(BattleCard.ROLL_OUT, 3)
                .withBattleCard(BattleCard.LEAP_INTO_BATTLE, 3)
                .withBattleCard(BattleCard.START_YOUR_ENGINES, 3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT, 3)
                .withBattleCard(BattleCard.THE_BIGGER_THEY_ARE, 3)
                .build();

        return new Deck(deckComp);
    }
}
