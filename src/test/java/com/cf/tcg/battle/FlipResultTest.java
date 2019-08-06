package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Pip;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class FlipResultTest {

    public FlipResultTest() {
    }

    @Test
    public void getTotalNumberOfPipsFlipped() {
        BattleCard battleCard1 = new BattleCard(Pip.BLACK, Pip.BLUE, Pip.WHITE);
        BattleCard battleCard2 = new BattleCard(Pip.BLACK);
        BattleCard battleCard3 = new BattleCard(Pip.ORANGE, Pip.BLUE);

        FlipResult subject = new FlipResult(battleCard1, battleCard2, battleCard3);

        assertEquals(3, subject.getTotalNumberOfCardsFlipped().intValue());
        assertEquals(2, subject.getTotalNumberOfPipsFlipped(Pip.BLACK).intValue());
        assertEquals(2, subject.getTotalNumberOfPipsFlipped(Pip.BLUE).intValue());
        assertEquals(1, subject.getTotalNumberOfPipsFlipped(Pip.WHITE).intValue());
        assertEquals(1, subject.getTotalNumberOfPipsFlipped(Pip.ORANGE).intValue());
        assertEquals(0, subject.getTotalNumberOfPipsFlipped(Pip.GREEN).intValue());
    }

    @Test
    public void containsPips() {
        BattleCard battleCard1 = new BattleCard(Pip.BLACK, Pip.BLUE, Pip.WHITE);
        BattleCard battleCard2 = new BattleCard(Pip.BLACK);
        BattleCard battleCard3 = new BattleCard(Pip.ORANGE, Pip.BLUE);

        FlipResult subject = new FlipResult(battleCard1, battleCard2, battleCard3);

        assertTrue(subject.containsPips(Pip.BLACK));
        assertFalse(subject.containsPips(Pip.GREEN));

        assertTrue(subject.containsPips(Pip.BLACK, Pip.ORANGE));
        assertFalse(subject.containsPips(Pip.BLACK, Pip.BLACK, Pip.BLACK));
    }

    @Test
    public void containsPipsNegativeTests() {
        BattleCard battleCard1 = new BattleCard(Pip.BLACK, Pip.BLUE, Pip.WHITE);

        FlipResult subject = new FlipResult(battleCard1);

        assertFalse(subject.containsPips(Pip.BLACK, Pip.ORANGE));
        assertFalse(subject.containsPips(Pip.BLACK, Pip.BLUE, Pip.WHITE, Pip.GREEN));
    }

}
