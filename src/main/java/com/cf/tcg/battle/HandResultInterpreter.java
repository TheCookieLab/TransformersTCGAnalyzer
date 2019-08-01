package com.cf.tcg.battle;

import com.cf.tcg.model.Pip;
import org.apache.commons.math3.random.EmpiricalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class HandResultInterpreter {

    public final List<Hand> hands;

    private final DescriptiveStatistics whitePipStats;
    private final EmpiricalDistribution whiteDistribution;

    private final DescriptiveStatistics orangePipStats;
    private final EmpiricalDistribution orangeDistribution;

    private final DescriptiveStatistics bluePipStats;
    private final EmpiricalDistribution blueDistribution;

    private final DescriptiveStatistics blackPipStats;
    private final DescriptiveStatistics greenPipStats;

    public HandResultInterpreter(List<Hand> hands) {
        this.hands = hands;
        this.whitePipStats = new DescriptiveStatistics();
        this.orangePipStats = new DescriptiveStatistics();
        this.bluePipStats = new DescriptiveStatistics();
        this.blackPipStats = new DescriptiveStatistics();
        this.greenPipStats = new DescriptiveStatistics();

        for (Hand hand : this.hands) {

        }

        this.whiteDistribution = new EmpiricalDistribution();
        this.whiteDistribution.load(this.whitePipStats.getSortedValues());

        this.orangeDistribution = new EmpiricalDistribution();
        this.orangeDistribution.load(this.orangePipStats.getSortedValues());

        this.blueDistribution = new EmpiricalDistribution();
        this.blueDistribution.load(this.bluePipStats.getSortedValues());
    }

    public double getAverageDamageBonus() {
        return this.orangePipStats.getMean();
    }

    public double getChanceDamageBonusIsLessThanOrEqualTo(double n) {
        return this.orangeDistribution.cumulativeProbability(n);
    }
    
    public double getChanceDamageBonusGreaterThan(double n) {
        return this.orangeDistribution.probability(n, n+100);
    }

    public double getChanceOfFlippingMoreThanOneWhite() {
        return this.whiteDistribution.probability(1, 100);
    }


    public double getChanceOfTriggeringMetroplexBotAbility() {
        double chanceOfFlippingMoreThanOneWhite = this.getChanceOfFlippingMoreThanOneWhite();
        double chanceOfFlippingMoreThanOneBlue = this.blueDistribution.probability(1, 100);
        double chanceOfFlippingMoreThanOneOrange = this.orangeDistribution.probability(1, 100);

        return chanceOfFlippingMoreThanOneWhite * chanceOfFlippingMoreThanOneBlue * chanceOfFlippingMoreThanOneOrange;
    }

    public double getAverageArmorBonus() {
        return this.bluePipStats.getMean();
    }

    public double getChanceArmorBonusIsLessThanOrEqualTo(double n) {
        return this.blueDistribution.cumulativeProbability(n);
    }

    public double getChanceArmorBonusGreaterThan(double n) {
        return this.blueDistribution.probability(n, n+100);
    }

    public double getAveragePierceBonus() {
        return this.blackPipStats.getMean();
    }

    public Integer getFlipResultCount() {
        return this.hands.size();
    }
}
