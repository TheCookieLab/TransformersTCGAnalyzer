package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
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
 *
 * @author David
 */
public class TrypticonDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public TrypticonDeckTester() {
    }

    @AfterEach
    public void afterEach() {
        ThreadContext.clearAll();
    }

    @Test
    @Order(1)
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new TrypticonDeckTester().runAttackSimulation(1);
    }

    @Test
    @Order(2)
    public void getChanceOfHavingThemeCardOnTurn1(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new TrypticonDeckTester().getChancesOfHavingAnyCardsOnTurn(1, 0, 0, BattleCard.BATTLEFIELD_INCURSION, BattleCard.RELENTLESS_INVASION);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.RELENTLESS_INVASION)
                .withBattleCard(BattleCard.HAZARDOUS_SHIELD)
                .withBattleCard(BattleCard.POCKET_PROCESSOR)
                .withBattleCard(BattleCard.I_STILL_FUNCTION, 3)
                .withBattleCard(BattleCard.BATTLEFIELD_INCURSION, 3)
                .withBattleCard(BattleCard.CALCULATED_STRIKE, 2)
                .withBattleCard(BattleCard.ERRATIC_ENERGY_GRENADE, 3)
                .withBattleCard(BattleCard.CROWBAR, 3)
                .withBattleCard(BattleCard.COMBAT_DAGGER, 3)
                .withBattleCard(BattleCard.MINOR_MEDIC_KIT, 3)
                .withBattleCard(BattleCard.FIGHT_FOR_POSITION, 3)
                .withBattleCard(BattleCard.HIDING_SPOT, 1)
                .withBattleCard(BattleCard.FORCE_FIELD, 1)
                .withBattleCard(BattleCard.INCREASED_DURABILITY, 2)
                .withBattleCard(BattleCard.SPECIAL_DELIVERY, 1)
                .withBattleCard(BattleCard.ROCK_TOSS, 3)
                .withBattleCard(BattleCard.STURDY_JAVELIN, 3)
                .withBattleCard(BattleCard.WEDGE_FORMATION, 3)
                .build();

        return new Deck(deckComp);
    }
}
