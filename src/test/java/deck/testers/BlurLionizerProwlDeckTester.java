package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David
 */
public class BlurLionizerProwlDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BlurLionizerProwlDeckTester() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAttackStats() {
        new BlurLionizerProwlDeckTester().runAttackSimulation();
    }

    @Test
    public void getDefenseStats() {
        new BlurLionizerProwlDeckTester().runDefenseSimulation();
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

    @Override
    public int getBold() {
        return 6;
    }

    @Override
    public int getTough() {
        return 0;
    }
}
