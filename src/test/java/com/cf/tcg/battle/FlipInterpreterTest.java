
package com.cf.tcg.battle;

import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.FlipResultInterpreter;
import com.cf.tcg.model.BattleCard;
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
     public void hello() {
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
}
