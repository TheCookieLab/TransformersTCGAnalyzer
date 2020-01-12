package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.battle.card.BattleCard;
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
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runAttackSimulation(1);
    }

    @Test
    @Order(2)
    public void getAttackStatsWithFocus(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runAttackSimulation(1, new ScrapOffColorFocusRule(1));
    }

    @Test
    @Order(3)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new GenOPPrivateArceeFlamewarBalancedDeckTester().runDefenseSimulation(2);
    }

    @Test
    @Order(4)
    public void getDefenseStatsWithFocus(TestInfo testInfo) {
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
                .withBattleCard(BattleCard.FUEL_CACHE)
                .withBattleCard(BattleCard.HANDHELD_BLASTER,3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT,3)
                .withBattleCard(BattleCard.THE_BIGGER_THEY_ARE,3)
                .withBattleCard(BattleCard.MARKSMANSHIP,3)
                .withBattleCard(BattleCard.VAPORIZE,2)
                .withBattleCard(BattleCard.SMELT,1)
                .withBattleCard(BattleCard.ION_BLASTER_OF_OPTIMUS_PRIME,2)
                .withBattleCard(BattleCard.NOBLES_BLASTER,1)
                .withBattleCard(BattleCard.SCOUNDRELS_BLASTER,1)
                .withBattleCard(BattleCard.PEACE_THROUGH_TYRANNY,3)
                .withBattleCard(BattleCard.IMPROVISED_SHIELD,3)
                .withBattleCard(BattleCard.GRENADE_LAUNCHER,1)
                .withBattleCard(BattleCard.STURDY_JAVELIN,2)
                .withBattleCard(BattleCard.RAMMING_SPEED,1)
                .withBattleCard(BattleCard.SPARRING_GEAR,3)
                .withBattleCard(BattleCard.MATRIX_OF_LEADERSHIP,3)
                .withBattleCard(BattleCard.ROLL_OUT,3)
                .withBattleCard(BattleCard.FORCE_FIELD,1)
                .build();

        return new Deck(deckComp);
    }
}
