
package com.cf.tcg.model.battle.card;

import com.cf.tcg.model.Pip;

/**
 *
 * @author David
 */
public interface Card {
    public String getName();
    public Integer getPipCount(Pip pip);
    
    @Override
    public boolean equals(Object obj); 
}
