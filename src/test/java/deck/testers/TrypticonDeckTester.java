package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class TrypticonDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public TrypticonDeckTester() {
    }

    @Test
    public void getAttackStats() {
        new TrypticonDeckTester().runAttackSimulation(1);
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
                .withBattleCard(BattleCard.REPROCESS)
                .withBattleCard(BattleCard.PERSONAL_TARGETING_DRONE)
                .withBattleCard(BattleCard.COMPOSITE_ARMOR)
                .withBattleCard(BattleCard.I_STILL_FUNCTION, 3)
                .withBattleCard(BattleCard.BATTLEFIELD_INCURSION, 3)
                .withBattleCard(BattleCard.CALCULATED_STRIKE, 2)
                .withBattleCard(BattleCard.ERRATIC_ENERGY_GRENADE, 2)
                .withBattleCard(BattleCard.CROWBAR, 3)
                .withBattleCard(BattleCard.COMBAT_DAGGER, 3)
                .withBattleCard(BattleCard.DESIGNATED_TARGET, 3)
                .withBattleCard(BattleCard.FIGHT_FOR_POSITION, 3)
                .withBattleCard(BattleCard.HIDING_SPOT, 3)
//                .withBattleCard(BattleCard.FORCE_FIELD, 3)
                .withBattleCard(BattleCard.INCREASED_DURABILITY, 2)
                .withBattleCard(BattleCard.SPECIAL_DELIVERY, 2)
                .withBattleCard(BattleCard.ROCK_TOSS, 3)
                .withBattleCard(BattleCard.STURDY_JAVELIN, 3)
                .withBattleCard(BattleCard.WEDGE_FORMATION, 3)
                .build();

        return new Deck(deckComp);
    }
}
