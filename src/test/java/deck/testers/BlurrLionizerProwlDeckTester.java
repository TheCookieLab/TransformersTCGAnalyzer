package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.Arrays;
import java.util.List;

/**
 * @author David
 */
@TestMethodOrder(OrderAnnotation.class)
public class BlurrLionizerProwlDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    private final static int MAX_BOLD = 6;
    private final static int BOLD = 4;
    private final static int TOUGH = 0;

    public BlurrLionizerProwlDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStatsForPrivateLionizer(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().runAttackSimulation(BOLD);
    }

    @Test
    @Order(2)
    public void getAttackStatsForPrivateFiredrive(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().runAttackSimulation(0);
    }

    @Test
    @Order(3)
    public void getAttackStatsForBlurWithSaberAndProwlAbility(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().runAttackSimulation(MAX_BOLD);
    }

    @Test
    @Order(4)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().runDefenseSimulation(TOUGH);
    }

    @Test
    @Order(5)
    public void getChancesOfHavingWayToConvertLionizerOnTurn2(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(2, BOLD, TOUGH, BattleCard.ONE_SHALL_STAND_ONE_SHALL_FALL, BattleCard.PEACE_THROUGH_TYRANNY);
    }

    @Test
    @Order(6)
    public void getChancesOfHavingUpgradeOnTurn2(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(2, BOLD, TOUGH, BattleCard.FORCE_FIELD, BattleCard.TURBO_BOOSTERS, BattleCard.MATRIX_OF_LEADERSHIP);
    }

    @Test
    @Order(7)
    public void getChancesOfHavingUntapOnTurn3(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().getChancesOfHavingAllCardsOnTurn(3, BOLD, TOUGH, BattleCard.START_YOUR_ENGINES, BattleCard.TURBO_BOOSTERS);
    }

    @Test
    @Order(8)
    public void getChancesOfHavingDamageBoostActionOnTurn3(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(3, BOLD, TOUGH, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.RECKLESS_CHARGE);
    }

    @Test
    @Order(9)
    public void getChancesOfHavingAlmostIdealHandTurn3(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BlurrLionizerProwlDeckTester().getChancesOfHavingAllCardsOnTurn(3, BOLD, TOUGH, BattleCard.START_YOUR_ENGINES, BattleCard.MATRIX_OF_LEADERSHIP);
    }

    @Test
    @Order(10)
    public void getChancesOfHavingIdealHandTurn3WithTurboBoostersUntap(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());

        List<BattleCard> allRequiredBattleCards = Arrays.asList(BattleCard.TURBO_BOOSTERS);
        List<BattleCard> anyOfBattleCards = Arrays.asList(BattleCard.RECKLESS_CHARGE, BattleCard.PRESS_THE_ADVANTAGE);

        new BlurrLionizerProwlDeckTester().getChanceOfHavingCombinationCardsOnTurn(3, BOLD, TOUGH, allRequiredBattleCards, anyOfBattleCards);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCards(
                        BattleCard.BASHING_SHIELD, BattleCard.BASHING_SHIELD,
                        BattleCard.CONFIDENCE, BattleCard.CONFIDENCE, BattleCard.CONFIDENCE,
                        BattleCard.ERRATIC_LIGHTNING, BattleCard.ERRATIC_LIGHTNING, BattleCard.ERRATIC_LIGHTNING,
                        BattleCard.GRENADE_LAUNCHER, BattleCard.GRENADE_LAUNCHER, BattleCard.GRENADE_LAUNCHER,
                        BattleCard.ONE_SHALL_STAND_ONE_SHALL_FALL, BattleCard.ONE_SHALL_STAND_ONE_SHALL_FALL,
                        BattleCard.I_STILL_FUNCTION, BattleCard.I_STILL_FUNCTION,
                        BattleCard.IMPROVISED_SHIELD, BattleCard.IMPROVISED_SHIELD, BattleCard.IMPROVISED_SHIELD,
                        BattleCard.PEACE_THROUGH_TYRANNY, BattleCard.PEACE_THROUGH_TYRANNY, BattleCard.PEACE_THROUGH_TYRANNY,
                        BattleCard.RECKLESS_CHARGE, BattleCard.RECKLESS_CHARGE, BattleCard.RECKLESS_CHARGE,
                        BattleCard.TURBO_BOOSTERS, BattleCard.TURBO_BOOSTERS, BattleCard.TURBO_BOOSTERS,
                        BattleCard.RAMMING_SPEED,
                        BattleCard.PRESS_THE_ADVANTAGE, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.PRESS_THE_ADVANTAGE,
                        BattleCard.START_YOUR_ENGINES, BattleCard.START_YOUR_ENGINES, BattleCard.START_YOUR_ENGINES,
                        BattleCard.FORCE_FIELD, BattleCard.FORCE_FIELD, BattleCard.FORCE_FIELD,
                        BattleCard.MATRIX_OF_LEADERSHIP, BattleCard.MATRIX_OF_LEADERSHIP, BattleCard.MATRIX_OF_LEADERSHIP
                )
                .build();

        return new Deck(deckComp);
    }
}
