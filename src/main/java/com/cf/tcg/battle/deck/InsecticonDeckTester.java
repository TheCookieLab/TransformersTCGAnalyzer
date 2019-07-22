package com.cf.tcg.battle.deck;

import com.cf.tcg.battle.BattleFlipSimulator;
import com.cf.tcg.battle.FlipResult;
import com.cf.tcg.battle.FlipResultInterpreter;
import com.cf.tcg.battle.focus.ScrapSinglePipsFocusRule;
import com.cf.tcg.battle.focus.ScrapSinglePipsKeepWhiteFocusRule;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.meta.DeckComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class InsecticonDeckTester {

    private final static Logger LOG = LogManager.getLogger();
    private final NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);

    public static void main(String[] args) {
        new InsecticonDeckTester().run();
    }

    public void run() {
        Integer doubleOrange = 6;
        Integer OrangeGreen = 2;
        Integer singleWhite = 3;
        Integer blank = 3;
        Integer singleOrange = 28;

        Deck deck = buildDeck(doubleOrange, OrangeGreen, singleWhite, blank, singleOrange);
        LOG.info("Deck: {}", deck);
        
        BattleFlipSimulator simulator = new BattleFlipSimulator(deck);
        List<FlipResult> flipResults = simulator.simulate(0, new ScrapSinglePipsKeepWhiteFocusRule(true));
        FlipResultInterpreter interpreter = new FlipResultInterpreter(flipResults);
        
        LOG.info("Average Damage Bonus: {}", interpreter.getAverageDamageBonus());
        LOG.info("Chance of adding less than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusIsLessThan(2)));
        LOG.info("Chance of adding more than 2 damage: {}", numberFormat.format(interpreter.getChanceDamageBonusGreaterThan(2)));
        LOG.info("Chance of flipping more than 1 white: {}", numberFormat.format(interpreter.getChanceOfFlippingMoreThanOneWhite()));
    }

    public static Deck buildDeck(Integer doubleOrange, Integer OrangeGreen, Integer singleWhite, Integer blank, Integer singleOrange) {
        DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(doubleOrange)
                .withSingleOrangeCards(singleOrange)
                .withOrangeGreenCards(OrangeGreen)
                .withSingleWhiteCards(singleWhite)
                .withBlankCards(blank)
                .build();

        Deck deck = new Deck(deckComp);
        deck.shuffleDeck();

        return deck;
    }
}
