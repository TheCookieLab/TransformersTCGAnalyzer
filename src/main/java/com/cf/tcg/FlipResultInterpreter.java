package com.cf.tcg;

import com.cf.tcg.model.Pip;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class FlipResultInterpreter {

    public final List<FlipResult> flipResults;

    private final DescriptiveStatistics whitePipStats;
    private final DescriptiveStatistics orangePipStats;
    private final DescriptiveStatistics bluePipStats;
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
    }

    public double getAverageDamageBonus() {
        return this.orangePipStats.getMean();
    }

    public double getDamageBonusVariance() {
        return this.orangePipStats.getVariance();
    }

    public double getAverageArmorBonus() {
        return this.bluePipStats.getMean();
    }

    public double getAveragePierceBonus() {
        return this.blackPipStats.getMean();
    }

    public Integer getFlipResultCount() {
        return this.flipResults.size();
    }
}
