package com.cf.tcg.model.meta;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class DeckComposition {

    public final Integer blank;
    public final Integer doubleOrange;
    public final Integer singleOrange;

    public final Integer doubleBlue;
    public final Integer singleBlue;

    public final Integer doubleBlack;
    public final Integer singleBlack;

    public final Integer white;
    public final Integer whiteGreen;
    public final Integer whiteOrangeBlue;

    public final Integer green;

    public final Integer blueOrange;
    public final Integer blueBlack;
    public final Integer orangeBlack;

    public final Integer totalCards;

    public DeckComposition(Deck deck) {
        this.doubleOrange = this.getCountMatchingPips(deck, BattleCard.DOUBLE_ORANGE.pips);
        this.singleOrange = this.getCountMatchingPips(deck, BattleCard.SINGLE_ORANGE.pips);

        this.doubleBlue = this.getCountMatchingPips(deck, BattleCard.DOUBLE_BLUE.pips);
        this.singleBlue = this.getCountMatchingPips(deck, BattleCard.SINGLE_BLUE.pips);

        this.doubleBlack = this.getCountMatchingPips(deck, BattleCard.DOUBLE_BLACK.pips);
        this.singleBlack = this.getCountMatchingPips(deck, BattleCard.SINGLE_BLACK.pips);

        this.white = this.getCountMatchingPips(deck, BattleCard.SINGLE_WHITE.pips);
        this.whiteGreen = this.getCountMatchingPips(deck, BattleCard.WHITE_GREEN.pips);

        this.green = this.getCountMatchingPips(deck, BattleCard.SINGLE_GREEN.pips);

        this.blueOrange = this.getCountMatchingPips(deck, BattleCard.BLUE_ORANGE.pips);
        this.blueBlack = this.getCountMatchingPips(deck, BattleCard.BLACK_BLUE.pips);
        this.orangeBlack = this.getCountMatchingPips(deck, BattleCard.BLACK_ORANGE.pips);

        this.whiteOrangeBlue = this.getCountMatchingPips(deck, BattleCard.BLUE_ORANGE_WHITE.pips);
        this.blank = this.getCountMatchingPips(deck, BattleCard.BLANK.pips);
        this.totalCards = deck.battleCards.size();
    }

    private DeckComposition(Integer blank, Integer doubleOrange, Integer singleOrange, Integer doubleBlue, Integer singleBlue, Integer doubleBlack, Integer singleBlack, Integer white, Integer whiteGreen, Integer whiteOrangeBlue, Integer green, Integer blueOrange, Integer blueBlack, Integer orangeBlack, Integer totalCards) {
        this.blank = blank;
        this.doubleOrange = doubleOrange;
        this.singleOrange = singleOrange;
        this.doubleBlue = doubleBlue;
        this.singleBlue = singleBlue;
        this.doubleBlack = doubleBlack;
        this.singleBlack = singleBlack;
        this.white = white;
        this.whiteGreen = whiteGreen;
        this.whiteOrangeBlue = whiteOrangeBlue;
        this.green = green;
        this.blueOrange = blueOrange;
        this.blueBlack = blueBlack;
        this.orangeBlack = orangeBlack;
        this.totalCards = totalCards;
    }

    private Integer getCountMatchingPips(Deck deck, List<Pip> pips) {
        return getCountMatchingPips(deck, pips.toArray(new Pip[0]));
    }

    private Integer getCountMatchingPips(Deck deck, Pip... pips) {
        List<Pip> referencePips = Arrays.asList(pips);
        int count = 0;

        for (BattleCard battleCard : deck.battleCards) {
            if (battleCard.pips.equals(referencePips)) {
                count++;
            }
        }

        if (count == 0) {
            return null;
        }
        return count;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static class DeckCompositionBuilder {

        private int blank;
        private int doubleOrange;
        private int singleOrange;

        private int doubleBlue;
        private int singleBlue;

        private int doubleBlack;
        private int singleBlack;

        private int white;
        private int whiteGreen;
        private int whiteOrangeBlue;

        private int green;

        private int blueOrange;
        private int blueBlack;
        private int orangeBlack;

        public DeckCompositionBuilder() {
        }

        public DeckCompositionBuilder withBlankCards(int blank) {
            this.blank = blank;
            return this;
        }

        public DeckCompositionBuilder withDoubleOrangeCards(int doubleOrange) {
            this.doubleOrange = doubleOrange;
            return this;
        }

        public DeckCompositionBuilder withSingleOrangeCards(int singleOrange) {
            this.singleOrange = singleOrange;
            return this;
        }

        public DeckCompositionBuilder withDoubleBlueCards(int doubleBlue) {
            this.doubleBlue = doubleBlue;
            return this;
        }

        public DeckCompositionBuilder withSingleBlueCards(int singleBlue) {
            this.singleBlue = singleBlue;
            return this;
        }

        public DeckCompositionBuilder withDoubleBlackCards(int doubleBlack) {
            this.doubleBlack = doubleBlack;
            return this;
        }

        public DeckCompositionBuilder withSingleBlackCards(int singleBlack) {
            this.singleBlack = singleBlack;
            return this;
        }

        public DeckCompositionBuilder withSingleWhiteCards(int white) {
            this.white = white;
            return this;
        }

        public DeckCompositionBuilder withWhiteGreenCards(int whiteGreen) {
            this.whiteGreen = whiteGreen;
            return this;
        }

        public DeckCompositionBuilder withWhiteOrangeBlueCards(int whiteOrangeBlue) {
            this.whiteOrangeBlue = whiteOrangeBlue;
            return this;
        }

        public DeckCompositionBuilder withGreenCards(int green) {
            this.green = green;
            return this;
        }

        public DeckCompositionBuilder withBlueOrangeCards(int blueOrange) {
            this.blueOrange = blueOrange;
            return this;
        }

        public DeckCompositionBuilder withBlueBlackCards(int blueBlack) {
            this.blueBlack = blueBlack;
            return this;
        }

        public DeckCompositionBuilder withOrangeBlackCards(int orangeBlack) {
            this.orangeBlack = orangeBlack;
            return this;
        }

        public DeckComposition build() {
            int totalCards = blank + doubleOrange + singleOrange + doubleBlue + singleBlue + doubleBlack + singleBlack + white + whiteGreen + whiteOrangeBlue + green + blueOrange + blueBlack + orangeBlack;
            DeckComposition deckComposition = new DeckComposition(blank, doubleOrange, singleOrange, doubleBlue, singleBlue, doubleBlack, singleBlack, white, whiteGreen, whiteOrangeBlue, green, blueOrange, blueBlack, orangeBlack, totalCards);
            return deckComposition;
        }
    }
}
