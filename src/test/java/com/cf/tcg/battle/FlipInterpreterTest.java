package com.cf.tcg.battle;

import com.cf.tcg.model.battle.card.BattleCard;
import com.cf.tcg.model.Pip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class FlipInterpreterTest {

    public FlipInterpreterTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBattleStats() {
        List<FlipResult> flipResults = new ArrayList<>();
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE), new BattleCard(Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.BLACK, Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE), new BattleCard(Pip.GREEN)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE, Pip.BLUE, Pip.ORANGE)));

        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        assertEquals(0.4, interpreter.getAverageDamageBonus(), 0.001);
        assertEquals(0.6, interpreter.getAverageArmorBonus(), 0.001);
        assertEquals(0.2, interpreter.getAveragePierceBonus(), 0.001);
    }

    @Test
    public void getChanceOfFlippingPipsIsPositive1() {
        List<FlipResult> flipResults = new ArrayList<>();
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE), new BattleCard(Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.BLACK, Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE), new BattleCard(Pip.GREEN)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE, Pip.BLUE, Pip.ORANGE)));

        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        assertEquals(0.4, interpreter.getChanceOfFlippingPips(Pip.BLUE, Pip.ORANGE), 0.001);
    }
    
    @Test
    public void getChanceOfFlippingPipsIsZero() {
        List<FlipResult> flipResults = new ArrayList<>();
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE), new BattleCard(Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.BLACK, Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE), new BattleCard(Pip.GREEN)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE, Pip.BLUE, Pip.ORANGE)));

        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        assertEquals(0.0, interpreter.getChanceOfFlippingPips(Pip.ORANGE, Pip.ORANGE), 0.001);
    }
    
    @Test
    public void getChanceOfFlippingPipsIsPositive2() {
        List<FlipResult> flipResults = new ArrayList<>();
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE, Pip.ORANGE, Pip.BLUE, Pip.BLUE, Pip.WHITE, Pip.WHITE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE), new BattleCard(Pip.WHITE), new BattleCard(Pip.ORANGE), new BattleCard(Pip.ORANGE), new BattleCard(Pip.BLUE), new BattleCard(Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.BLUE, Pip.BLUE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.WHITE)));
        flipResults.add(new FlipResult(new BattleCard(Pip.ORANGE)));

        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);

        assertEquals(0.4, interpreter.getChanceOfFlippingPips(Pip.ORANGE, Pip.ORANGE, Pip.BLUE, Pip.BLUE, Pip.WHITE, Pip.WHITE), 0.001);
    }
}
