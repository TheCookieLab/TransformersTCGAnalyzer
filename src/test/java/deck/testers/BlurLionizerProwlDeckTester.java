package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * @author David
 */
public class BlurLionizerProwlDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    private final static int MAX_BOLD = 6;
    private final static int BOLD = 4;
    private final static int TOUGH = 0;

    public BlurLionizerProwlDeckTester() {
    }

    @Test
    public void getAttackStatsForLionizer() {
        new BlurLionizerProwlDeckTester().runAttackSimulation(BOLD);
    }

    @Test
    public void getAttackStatsForBlurWithSaberAndProwlAbility() {
        new BlurLionizerProwlDeckTester().runAttackSimulation(MAX_BOLD);
    }

    @Test
    public void getDefenseStats() {
        new BlurLionizerProwlDeckTester().runDefenseSimulation(TOUGH);
    }

    @Test
    public void getChancesOfHavingWayToConvertLionizerOnTurn2() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(2, BOLD, TOUGH, BattleCard.ONE_SHALL_STAND_ONE_SHALL_FALL, BattleCard.PEACE_THROUGH_TYRANNY);
    }

    @Test
    public void getChancesOfHavingUpgradeOnTurn2() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(2, BOLD, TOUGH, BattleCard.FORCE_FIELD, BattleCard.TURBO_BOOSTERS, BattleCard.MATRIX_OF_LEADERSHIP);
    }

    @Test
    public void getChancesOfHavingUntapOnTurn3() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAllCardsOnTurn(3, BOLD, TOUGH, BattleCard.START_YOUR_ENGINES, BattleCard.TURBO_BOOSTERS);
    }

    @Test
    public void getChancesOfHavingDamageBoostActionOnTurn3() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(3, BOLD, TOUGH, BattleCard.PRESS_THE_ADVANTAGE, BattleCard.RECKLESS_CHARGE);
    }

    @Test
    public void getChancesOfHavingAlmostIdealHandTurn3() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAllCardsOnTurn(3, BOLD, TOUGH, BattleCard.START_YOUR_ENGINES, BattleCard.MATRIX_OF_LEADERSHIP);
    }

    @Test
    public void getChancesOfHavingIdealHandTurn3() {
        new BlurLionizerProwlDeckTester().getChancesOfHavingAllCardsOnTurn(3, BOLD, TOUGH, BattleCard.TURBO_BOOSTERS, BattleCard.PRESS_THE_ADVANTAGE);
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
