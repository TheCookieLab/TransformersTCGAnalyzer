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
public class GalaxyPrimeDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public GalaxyPrimeDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GalaxyPrimeDeckTester().runAttackSimulation(0);
    }

    @Test
    @Order(2)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GalaxyPrimeDeckTester().runDefenseSimulation(1);
    }

    @Test
    @Order(3)
    public void getChancesOfTriggeringOptimusBotModeAbility(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GalaxyPrimeDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.BLUE);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.ENERGON_AXE, 2)
                .withBattleCard(BattleCard.HANDHELD_BLASTER, 2)
                .withBattleCard(BattleCard.ION_BLASTER_OF_OPTIMUS_PRIME, 2)
                .withBattleCard(BattleCard.LASER_CUTLASS, 1)
                .withBattleCard(BattleCard.NOBLES_BLASTER, 1)
                .withBattleCard(BattleCard.SPARRING_GEAR, 2)
                .withBattleCard(BattleCard.ENERGY_PACK, 3)
                .withBattleCard(BattleCard.FIELD_COMMUNICATOR, 2)
                .withBattleCard(BattleCard.MATRIX_OF_LEADERSHIP, 3)
                .withBattleCard(BattleCard.DISARM, 2)
                .withBattleCard(BattleCard.EQUIPMENT_ENTHUSIAST, 2)
                .withBattleCard(BattleCard.INCOMING_TRANSMISSION, 2)
                .withBattleCard(BattleCard.LEAP_INTO_BATTLE, 2)
                .withBattleCard(BattleCard.MARKSMANSHIP, 3)
                .withBattleCard(BattleCard.REPROCESS, 1)
                .withBattleCard(BattleCard.SABOTAGED_ARMAMENTS, 3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT, 3)
                .withBattleCard(BattleCard.THE_BIGGER_THEY_ARE, 2)
                .withBattleCard(BattleCard.WORK_OVERTIME, 2)
                .build();

        return new Deck(deckComp);
    }
}
