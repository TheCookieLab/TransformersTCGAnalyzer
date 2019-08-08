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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


/**
 * @author David
 */
@TestMethodOrder(OrderAnnotation.class)
public class JetBlueDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    private final static int AIMLESS_TOUGH = 3;
    private final static int ARCEE_TOUGH = 0;
    private final static int JETFIRE_TOUGH = 0;

    public JetBlueDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getDefenseStatsForAimless(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(AIMLESS_TOUGH);
    }

    @Test
    @Order(2)
    public void getDefenseStatsForAimlessWithTough2(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(AIMLESS_TOUGH + 2);
    }

    @Test
    @Order(3)
    public void getDefenseStatsForAimlessWithTough4(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(AIMLESS_TOUGH + 4);
    }

    @Test
    @Order(4)
    public void getDefenseStatsForJetfire(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(JETFIRE_TOUGH);
    }

    @Test
    @Order(5)
    public void getDefenseStatsForJetfireWithTough2(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(JETFIRE_TOUGH + 2);
    }

    @Test
    @Order(6)
    public void getDefenseStatsForJetfireWithTough4(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(JETFIRE_TOUGH + 4);
    }

    @Test
    @Order(7)
    public void getDefenseStatsForPrivateArcee(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(ARCEE_TOUGH);
    }

    @Test
    @Order(8)
    public void getDefenseStatsForPrivateArceeWithTough1(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(ARCEE_TOUGH + 1);
    }

    @Test
    @Order(9)
    public void getDefenseStatsForPrivateArceeWithTough3(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(ARCEE_TOUGH + 3);
    }

    @Test
    @Order(10)
    public void getDefenseStatsForPrivateArceeWithTough5(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().runDefenseSimulation(ARCEE_TOUGH + 5);
    }

    @Test
    @Order(11)
    public void getChanceOfTriggeringManifoldIonParticleBlaster(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new JetBlueDeckTester().getChancesOfFlippingPips(1, Pip.BLUE, Pip.BLUE, Pip.BLUE);
    }


    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCards(
                        BattleCard.HANDHELD_BLASTER, BattleCard.HANDHELD_BLASTER, BattleCard.HANDHELD_BLASTER,
                        BattleCard.ARMED_HOVERCRAFT, BattleCard.ARMED_HOVERCRAFT, BattleCard.ARMED_HOVERCRAFT,
                        BattleCard.NOBLES_BLASTER,
                        BattleCard.REINFORCED_PLATING, BattleCard.REINFORCED_PLATING, BattleCard.REINFORCED_PLATING,
                        BattleCard.SUPERIOR_PLATING, BattleCard.SUPERIOR_PLATING, BattleCard.SUPERIOR_PLATING,
                        BattleCard.BASHING_SHIELD,
                        BattleCard.FORCE_FIELD, BattleCard.FORCE_FIELD,
                        BattleCard.SUPERIOR_JETPACK, BattleCard.SUPERIOR_JETPACK,
                        BattleCard.SPARE_PARTS,
                        BattleCard.BRAVERY, BattleCard.BRAVERY,
                        BattleCard.SECURITY_CHECKPOINT, BattleCard.SECURITY_CHECKPOINT, BattleCard.SECURITY_CHECKPOINT,
                        BattleCard.INSPIRING_LEADERSHIP, BattleCard.INSPIRING_LEADERSHIP, BattleCard.INSPIRING_LEADERSHIP,
                        BattleCard.THE_BIGGER_THEY_ARE, BattleCard.THE_BIGGER_THEY_ARE, BattleCard.THE_BIGGER_THEY_ARE,
                        BattleCard.QUARTERMASTER, BattleCard.QUARTERMASTER, BattleCard.QUARTERMASTER,
                        BattleCard.MARKSMANSHIP, BattleCard.MARKSMANSHIP, BattleCard.MARKSMANSHIP,
                        BattleCard.TECH_RESEARCH,
                        BattleCard.VAPORIZE, BattleCard.VAPORIZE,
                        BattleCard.SMELT
                )
                .build();

        return new Deck(deckComp);
    }
}
