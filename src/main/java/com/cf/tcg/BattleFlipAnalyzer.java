package com.cf.tcg;

import com.cf.tcg.model.BattleCard;
import com.cf.tcg.model.Deck;
import com.cf.tcg.model.Pip;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author David
 */
public class BattleFlipAnalyzer {

    public static int NUM_OF_DESIRED_WHITE_CARDS = 2;
    public static int NUMBER_OF_ITERATIONS = 100;
    public static int MAX_NUM_CARDS_TO_FLIP = 4;
    public static int DECK_MIN_WHITE_CARDS = 10;
    public static int DECK_MAX_WHITE_CARDS = 20;
    public static int DECK_TOTAL_CARDS = 40;

    public static void main(String[] args) {
        new BattleFlipAnalyzer().run();
    }

    public void run() {
        Map<String, ScenarioResult> results = battleFlipForWhitePips();
        this.report(results);
    }

    private void report(Map<String, ScenarioResult> results) {
        LogManager.getLogger().info("Raw Results: {}", results);

        for (int i = 0; i <= 2; i++) {
            final int desiredFlips = i;
            results.entrySet().stream().filter((result) -> {
                ScenarioResult scenarioResult = result.getValue();
                scenarioResult.flipResults.stream().filter((flipResult) -> {
                    return flipResult.getTotalNumberOfPipsFlipped(Pip.WHITE) == desiredFlips;
                });
                
                return false;
            });

            LogManager.getLogger().info("Chances of Flipping {} White Pips: {}", i, results);
        }

        LogManager.getLogger().info("Raw Results: {}", results);

//        s.flipResults.stream().filter((flipResult) -> {
//                    return flipResult.getTotalNumberOfPipsFlipped(Pip.WHITE) == i;
//                });
    }

    private Map<String, ScenarioResult> battleFlipForWhitePips() {

        List<Scenario> scenarios = new ArrayList<>();
        for (int numInDeck = 5; numInDeck < 12; numInDeck++) {
            Scenario scenario = new Scenario(DECK_TOTAL_CARDS, Pip.WHITE, numInDeck, 1, DrawScenario.BATTLE);
            scenarios.add(scenario);
        }

        Map<String, ScenarioResult> scenarioResults = new ConcurrentHashMap<>();

        scenarios.parallelStream().forEach((scenario) -> {
            ScenarioResult result = testScenario(scenario);
            scenarioResults.put(result.toString(), result);
        });

        return scenarioResults;
    }

    private ScenarioResult testScenario(Scenario scenario) {

        Deck deck = new Deck.DeckBuilder(scenario.deckSize)
                .withNumberOfWhiteCards(scenario.numOfDesiredPip)
                .build();

        ScenarioResult result = new ScenarioResult(deck, scenario.desiredPip);

        IntStream.rangeClosed(1, NUMBER_OF_ITERATIONS).forEach((iteration) -> {
            FlipResult flipResult = new FlipResult();

            for (int j = 0; j < scenario.drawScenario.cardsToDraw; j++) {
                BattleCard battleCard = deck.drawCard();
                flipResult.addFlippedCard(battleCard);
                deck.scrap(battleCard);
            }

            result.addFlipResult(flipResult);
            deck.reshuffleScrapIntoDeck();
        });

        return result;
    }
}
