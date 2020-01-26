package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.NoOpFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
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
public class WindbladeOverwhelmingAdvantageDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    public WindbladeOverwhelmingAdvantageDeckTester() {
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new WindbladeOverwhelmingAdvantageDeckTester().runAttackSimulation(0);
    }

    @Test
    @Order(2)
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new WindbladeOverwhelmingAdvantageDeckTester().runDefenseSimulation(0);
    }

    @Test
    @Order(3)
    public void getChanceOfFlippingOnePipOfEachColor(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new WindbladeOverwhelmingAdvantageDeckTester().getChancesOfFlippingPips(2,
    new NoOpFocusRule(),
            Pip.WHITE, Pip.ORANGE, Pip.BLACK, Pip.BLUE, Pip.GREEN);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.AERIAL_RECON,3)
                .withBattleCard(BattleCard.BACKUP_BEAM,3)
                .withBattleCard(BattleCard.BASHING_SHIELD,1)
                .withBattleCard(BattleCard.FIELD_COMMUNICATOR,1)
                .withBattleCard(BattleCard.FUEL_CACHE)
                .withBattleCard(BattleCard.HANDHELD_BLASTER,3)
                .withBattleCard(BattleCard.ESCAPE_ROUTE, 1)
                .withBattleCard(BattleCard.OVERWHELMING_ADVANTAGE,3)
                .withBattleCard(BattleCard.REFLEX_CIRCUITS,2)
                .withBattleCard(BattleCard.ROLL_OUT,3)
                .withBattleCard(BattleCard.FIGHT_FOR_POSITION,3)
                .withBattleCard(BattleCard.SCOUNDRELS_BLASTER,2)
                .withBattleCard(BattleCard.SECRET_DEALINGS,3)
                .withBattleCard(BattleCard.SECURITY_CHECKPOINT,3)
                .withBattleCard(BattleCard.SMOKE_CLOAK,3)
                .withBattleCard(BattleCard.SPARE_PARTS,1)
                .withBattleCard(BattleCard.STURDY_ARMOR,1)
                .withBattleCard(BattleCard.WEDGE_FORMATION,3)
                .build();

        Deck deck = new Deck(deckComp);

        getLogger().info("Pip breakdown: {}", deck.getDeckPipBreakdown());
        getLogger().info("BattleCard breakdown: {}", deck.getDeckCardTypeBreakdown());

        return deck;
    }
}
