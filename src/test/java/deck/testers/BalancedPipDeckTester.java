package deck.testers;

import com.cf.tcg.battle.deck.DeckTester;
import com.cf.tcg.battle.focus.FocusRule;
import com.cf.tcg.battle.focus.ScrapOffColorFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
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
public class BalancedPipDeckTester implements DeckTester {

    private final Logger LOG = LogManager.getLogger();

    public BalancedPipDeckTester() {
    }

    @Test
    public void getAttackStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runAttackSimulation(0);
    }

    @Test
    public void getAttackStatsFocus1(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runAttackSimulation(0, new ScrapOffColorFocusRule(1));
    }

    @Test
    public void getAttackStatsFocus2(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runAttackSimulation(0, new ScrapOffColorFocusRule(2));
    }

    @Test
    public void getAttackStatsFocus3(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runAttackSimulation(0, new ScrapOffColorFocusRule(3));
    }

    @Test
    public void getDefenseStats(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runDefenseSimulation(0);
    }

    @Test
    public void getChancesOfTriggeringDragstripDrawAbility(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.BLUE);
    }

    @Test
    public void getChancesOfTriggeringMetroplexAbility1(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().getChancesOfFlippingPips(0, Pip.ORANGE, Pip.ORANGE, Pip.BLUE, Pip.BLUE, Pip.WHITE, Pip.WHITE);
    }

    @Test
    public void getChancesOfTriggeringMetroplexAbilityOriginal(TestInfo testInfo) {
        ThreadContext.put("identity", testInfo.getTestMethod().get().getName());
        new BalancedPipDeckTester().runMetroplexAbilityOdds(0);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public Deck buildDeck() {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withDoubleBlueCards(6)
                .withSingleOrangeCards(10)
                .withSingleBlueCards(10)
                .withSingleWhiteCards(7)
                .withWhiteOrangeBlueCards(1)
                .build();

        return new Deck(deckComp);
    }
}
