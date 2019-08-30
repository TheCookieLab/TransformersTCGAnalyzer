package com.cf.tcg.character;

import com.cf.tcg.CardRarity;

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
        private String name;
        private Integer stars;
        private String subName;
        private CardRarity rarity;
        private Integer cardNumber;
        private String wave;

        private Map<CharacterMode, CharacterModeDetail> modes;

        


   }
}
