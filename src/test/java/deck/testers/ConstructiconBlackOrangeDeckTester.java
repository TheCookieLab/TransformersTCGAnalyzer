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
public class ConstructiconBlackOrangeDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public ConstructiconBlackOrangeDeckTester() {
    }

    @Test
    public void getAttackStats() {
        new ConstructiconBlackOrangeDeckTester().runAttackSimulation(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCard(BattleCard.BASHING_SHIELD)
                .withBattleCard(BattleCard.DATA_BANK, 1)
                .withBattleCard(BattleCard.POCKET_PROCESSOR)
                .withBattleCard(BattleCard.HIDING_SPOT, 1)
                .withBattleCard(BattleCard.WEDGE_FORMATION, 2)
                .withBattleCard(BattleCard.CONSTRUCTICON_ENIGMA, 2)
                .withBattleCard(BattleCard.SMOKETHROWER, 2)
                .withBattleCard(BattleCard.I_STILL_FUNCTION, 3)
                .withBattleCard(BattleCard.DESIGNATED_TARGET, 1)
                .withBattleCard(BattleCard.CALCULATED_STRIKE, 2)
                .withBattleCard(BattleCard.ERRATIC_ENERGY_GRENADE, 3)
                .withBattleCard(BattleCard.CROWBAR, 3)
                .withBattleCard(BattleCard.COMBAT_DAGGER, 3)
                .withBattleCard(BattleCard.HEAT_OF_BATTLE, 3)
                .withBattleCard(BattleCard.FIGHT_FOR_POSITION, 3)
                .withBattleCard(BattleCard.RR_DISRUPTOR_BLADE, 3)
                .withBattleCard(BattleCard.ROCK_TOSS, 3)
                .withBattleCard(BattleCard.MINOR_MEDIC_KIT, 3)
                .build();

        return new Deck(deckComp);
    }
}
