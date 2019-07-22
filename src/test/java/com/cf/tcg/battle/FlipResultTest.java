package com.cf.tcg.battle;

import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Pip;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author David
 */
public class FlipResultTest {

    public FlipResultTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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
}
