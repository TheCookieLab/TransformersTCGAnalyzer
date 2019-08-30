package com.cf.tcg;

public enum CardRarity {
    COMMON("CT"), UNCOMMON("UT"), RARE("RT"), SUPER_RARE("SRT");

    private String abbreviation;

    CardRarity(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    @Override
    public String toString() {
        return this.getAbbreviation();
    }
}
