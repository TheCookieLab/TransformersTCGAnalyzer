package com.cf.tcg.character;

import com.cf.tcg.CardRarity;

import java.util.HashMap;
import java.util.Map;

public class CharacterCard {
    public final String name;
    public final Integer stars;
    public final String subName;
    public final CardRarity rarity;
    public final Integer cardNumber;
    public final String wave;

    public final Map<CharacterMode, CharacterModeDetail> modes;

    private CharacterCard(String name, Integer stars, String subName, CardRarity rarity, Integer cardNumber, String wave, Map<CharacterMode, CharacterModeDetail> modes) {
        this.name = name;
        this.stars = stars;
        this.subName = subName;
        this.rarity = rarity;
        this.cardNumber = cardNumber;
        this.wave = wave;
        this.modes = modes;
    }

    public static class CharacterCardBuilder {
        private String name = null;
        private Integer stars = null;
        private String subName = null;
        private CardRarity rarity = null;
        private Integer cardNumber = null;
        private String wave = null;

        private Map<CharacterMode, CharacterModeDetail> modes = new HashMap<>();

        public CharacterCardBuilder() {
        }

        public CharacterCardBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CharacterCardBuilder withStars(Integer stars) {
            this.stars = stars;
            return this;
        }

        public CharacterCardBuilder withSubName(String subName) {
            this.subName = subName;
            return this;
        }

        public CharacterCardBuilder withRarity(CardRarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public CharacterCardBuilder withCardNumber(Integer cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CharacterCardBuilder withWave(String wave) {
            this.wave = wave;
            return this;
        }

        public CharacterCardBuilder withMode(CharacterMode modeName, CharacterModeDetail modeDetail) {
            this.modes.put(modeName, modeDetail);
            return this;
        }

        public CharacterCard build() {
            return new CharacterCard(name, stars, subName, rarity, cardNumber, wave, modes);
        }

    }
}
