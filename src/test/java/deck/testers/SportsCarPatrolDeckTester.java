package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 *
 * @author David
 */
public class SportsCarPatrolDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public SportsCarPatrolDeckTester() {
    }

    @Test
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new SportsCarPatrolDeckTester().runAttackSimulation(0);
    }

    @Test
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new SportsCarPatrolDeckTester().runDefenseSimulation(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.LASER_CUTLASS, 3)
                .withBattleCard(BattleCard.PIERCING_BLASTER, 3)
                .withBattleCard(BattleCard.START_YOUR_ENGINES, 3)
                .withBattleCard(BattleCard.OPPORTUNE_OFFENSIVE, 3)
                .withBattleCard(BattleCard.SMOKE_CLOAK, 3)
                .withBattleCard(BattleCard.TURBO_BOOSTERS, 3)
                .withBattleCard(BattleCard.CROWBAR, 3)
                .withBattleCard(BattleCard.STEADY_SHOT, 3)
                .withBattleCard(BattleCard.THE_BIGGER_THEY_ARE, 3)
                .withBattleCard(BattleCard.HANDHELD_BLASTER, 3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT, 3)
                .withBattleCard(BattleCard.FIELD_COMMUNICATOR, 2)
                .withBattleCard(BattleCard.REINFORCED_PLATING, 2)
                .withBattleCard(BattleCard.CALCULATED_STRIKE, 3)
                .build();

        return new Deck(deckComp);
    }
}
