package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
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
public class SoundwaveBlueBlackDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public SoundwaveBlueBlackDeckTester() {
    }

    @Test
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new SoundwaveBlueBlackDeckTester().runAttackSimulation(0);
    }

    @Test
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new SoundwaveBlueBlackDeckTester().runDefenseSimulation(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.HIDDEN_FORTIFICATION, 1)
                .withBattleCard(BattleCard.STURDY_ARMOR, 2)
                .withBattleCard(BattleCard.STURDY_JAVELIN, 2)
                .withBattleCard(BattleCard.SCOUNDRELS_BLASTER, 2)
                .withBattleCard(BattleCard.REFLEX_CIRCUITS, 1)
                .withBattleCard(BattleCard.SMELT, 1)
                .withBattleCard(BattleCard.VAPORIZE, 2)
                .withBattleCard(BattleCard.SABOTAGED_ARMAMENTS, 2)
                .withBattleCard(BattleCard.LASER_CUTLASS, 3)
                .withBattleCard(BattleCard.DESIGNATED_TARGET, 3)
                .withBattleCard(BattleCard.THE_BIGGER_THEY_ARE, 3)
                .withBattleCard(BattleCard.REINFORCED_PLATING, 2)
                .withBattleCard(BattleCard.SMOKE_CLOAK, 1)
                .withBattleCard(BattleCard.LEAP_INTO_BATTLE, 3)
                .withBattleCard(BattleCard.STEADY_SHOT, 3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT, 3)
                .withBattleCard(BattleCard.HANDHELD_BLASTER, 3)
                .withBattleCard(BattleCard.ENERGON_AXE, 3)
                .build();

        return new Deck(deckComp);
    }
}
