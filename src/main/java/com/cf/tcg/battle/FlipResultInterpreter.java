package com.cf.tcg.battle;

import com.cf.tcg.model.Pip;
import com.cf.tcg.model.battle.card.BattleCardType;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;
import org.apache.commons.math3.random.EmpiricalDistribution;

public class FlipResultInterpreter {

    public final List<FlipResult> flipResults;

    private final DescriptiveStatistics whitePipStats;
    private final EmpiricalDistribution whiteDistribution;

    private final DescriptiveStatistics orangePipStats;
    private final EmpiricalDistribution orangeDistribution;

    private final DescriptiveStatistics bluePipStats;
    private final EmpiricalDistribution blueDistribution;

    private final DescriptiveStatistics blackPipStats;
    private final DescriptiveStatistics greenPipStats;

    public FlipResultInterpreter(List<FlipResult> flipResults) {
        this.flipResults = flipResults;
        this.whitePipStats = new DescriptiveStatistics();
        this.orangePipStats = new DescriptiveStatistics();
        this.bluePipStats = new DescriptiveStatistics();
        this.blackPipStats = new DescriptiveStatistics();
        this.greenPipStats = new DescriptiveStatistics();

        for (FlipResult flipResult : this.flipResults) {
            this.whitePipStats.addValue(flipResult.getTotalNumberOfPipsFlipped(Pip.WHITE).doubleValue());
            this.orangePipStats.addValue(flipResult.getTotalNumberOfPipsFlipped(Pip.ORANGE).doubleValue());
            this.bluePipStats.addValue(flipResult.getTotalNumberOfPipsFlipped(Pip.BLUE).doubleValue());
            this.blackPipStats.addValue(flipResult.getTotalNumberOfPipsFlipped(Pip.BLACK).doubleValue());
            this.greenPipStats.addValue(flipResult.getTotalNumberOfPipsFlipped(Pip.GREEN).doubleValue());
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
    
    public double getChanceOfFlippingPips(Pip...pips) {
        double occurrence = 0;
        
        for (FlipResult flipResult : this.flipResults) {
            if (flipResult.containsPips(pips)) {
                occurrence++;
            }
        }
        
        return occurrence / this.flipResults.size();
    }

    public double getChanceOfFlippingBattleCardType(BattleCardType battleCardType) {
        double occurrence = 0;

        for (FlipResult flipResult : this.flipResults) {
            if (flipResult.containsBattleCardType(battleCardType)) {
                occurrence++;
            }
        }

        return occurrence / this.flipResults.size();
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

    public Integer getCount() {
        return this.flipResults.size();
    }
}
