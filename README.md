# TransformersTCGAnalyzer
Transformers TCG Deck and Flip Simulator v0.0.1

* How much bonus attack damage does my deck provide on flips?
* If I had to choose between the two, what has more expected value in my deck, Power Punch or Erratic Lightning?
* Is there a deck list where Shock Absorbers is better than Reinforced Plating?
* How good is Focus N in my mixed pip deck? How about my mono-orange deck?

Looking to answer all these questions and more!

### User's Guide

This software is currently under heavy construction so things are not guaranteed to work as you'd expect (or at all). That said, using this client will be as simple as defining your deck, instantiating a BattleFlipSimulator, simulating, and interpreting the results!

### Creating a Deck With Generic Pip-only Cards
```java
DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withDoubleOrangeCards(6)
                .withOrangeGreen(2)
                .withSingleWhiteCards(3)
                .withBlankCards(3)
                .withSingleOrangeCards(28)
                .build();
        
Deck deck = new Deck(deckComp);
```

### Creating a Deck With Specific Battle Cards
```java
DeckComposition deckComp = new DeckComposition.DeckCompositionBuilder()
                .withBattleCards(
                        BattleCard.HANDHELD_BLASTER, BattleCard.HANDHELD_BLASTER, BattleCard.HANDHELD_BLASTER,
                        BattleCard.ARMED_HOVERCRAFT, BattleCard.ARMED_HOVERCRAFT, BattleCard.ARMED_HOVERCRAFT,
                        BattleCard.NOBLES_BLASTER,
                        BattleCard.REINFORCED_PLATING, BattleCard.REINFORCED_PLATING, BattleCard.REINFORCED_PLATING,
                        BattleCard.SUPERIOR_PLATING, BattleCard.SUPERIOR_PLATING, BattleCard.SUPERIOR_PLATING,
                        BattleCard.BASHING_SHIELD,
                        BattleCard.FORCE_FIELD, BattleCard.FORCE_FIELD,
                        BattleCard.SUPERIOR_JETPACK, BattleCard.SUPERIOR_JETPACK,
                        BattleCard.SPARE_PARTS,
                        BattleCard.BRAVERY, BattleCard.BRAVERY,
                        BattleCard.SECURITY_CHECKPOINT, BattleCard.SECURITY_CHECKPOINT, BattleCard.SECURITY_CHECKPOINT,
                        BattleCard.INSPIRING_LEADERSHIP, BattleCard.INSPIRING_LEADERSHIP, BattleCard.INSPIRING_LEADERSHIP,
                        BattleCard.THE_BIGGER_THEY_ARE, BattleCard.THE_BIGGER_THEY_ARE, BattleCard.THE_BIGGER_THEY_ARE,
                        BattleCard.QUARTERMASTER, BattleCard.QUARTERMASTER, BattleCard.QUARTERMASTER,
                        BattleCard.MARKSMANSHIP, BattleCard.MARKSMANSHIP, BattleCard.MARKSMANSHIP,
                        BattleCard.TECH_RESEARCH,
                        BattleCard.VAPORIZE, BattleCard.VAPORIZE,
                        BattleCard.SMELT
                )
                .build();

        return new Deck(deckComp);
```

### Simply create a new class that implements the DeckTester interface...

```java
public class JetBlueDeckTester implements DeckTester 
```

### ...And Invoke One of Many Default Simulations!
```java
int tough = 1;
new JetBlueDeckTester().runDefenseSimulation(tough);
```

### Here's One For Attack Stats!
```java
int bold = 0;
new JetBlueDeckTester().runAttackSimulation(bold);
```

### Finding Probabilities of Having Specific Battle Cards In Hand On a Specific Turn!
```java
int turn = 2;
int bold = 4;
int tough = 0;

new BlurrLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(turn, bold, tough, BattleCard.FORCE_FIELD, BattleCard.TURBO_BOOSTERS, BattleCard.MATRIX_OF_LEADERSHIP);
```

### Example Console Output
```
Running deck.testers.BlurrLionizerProwlDeckTester
2019-08-08 19:04:03,650 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Average Damage Bonus (Bold 4): 6.1171
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of adding less than or equal to 3 damage: 3%
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of adding less than or equal to 4 damage: 13%
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of adding less than or equal to 5 damage: 34%
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of adding less than or equal to 6 damage: 60%
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of adding less than or equal to 7 damage: 83%
2019-08-08 19:04:03,653 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateLionizer] INFO - Chance of flipping more than 1 white: 9%

2019-08-08 19:04:03,703 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Average Damage Bonus (Bold 0): 2.0660999999999996
2019-08-08 19:04:03,704 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Chance of adding less than or equal to 0 damage: 3%
2019-08-08 19:04:03,704 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Chance of adding less than or equal to 1 damage: 25%
2019-08-08 19:04:03,704 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Chance of adding less than or equal to 2 damage: 71%
2019-08-08 19:04:03,704 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Chance of adding less than or equal to 3 damage: 95%
2019-08-08 19:04:03,704 BlurrLionizerProwlDeckTester [getAttackStatsForPrivateFiredrive] INFO - Chance of flipping more than 1 white: 2%

2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Average Damage Bonus (Bold 6): 8.099399999999997
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of adding less than or equal to 5 damage: 5%
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of adding less than or equal to 6 damage: 16%
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of adding less than or equal to 7 damage: 36%
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of adding less than or equal to 8 damage: 60%
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of adding less than or equal to 9 damage: 81%
2019-08-08 19:04:03,789 BlurrLionizerProwlDeckTester [getAttackStatsForBlurWithSaberAndProwlAbility] INFO - Chance of flipping more than 1 white: 15%

2019-08-08 19:04:03,818 BlurrLionizerProwlDeckTester [getDefenseStats] INFO - Average Armor Bonus (Tough 0): 0.3415000000000001
2019-08-08 19:04:03,818 BlurrLionizerProwlDeckTester [getDefenseStats] INFO - Chance of adding less than or equal to 0 armor: 69%
2019-08-08 19:04:03,818 BlurrLionizerProwlDeckTester [getDefenseStats] INFO - Chance of adding less than or equal to 1 armor: 97%
2019-08-08 19:04:03,818 BlurrLionizerProwlDeckTester [getDefenseStats] INFO - Chance of flipping more than 1 white: 2%

2019-08-08 19:04:03,905 BlurrLionizerProwlDeckTester [getChancesOfHavingWayToConvertLionizerOnTurn2] INFO - Probability of having any of [{"name":"One Shall Stand, One Shall Fall","pips":[]}, {"name":"Peace Through Tyranny","pips":["ORANGE","ORANGE"]}] in hand on turn 2 is: 50%
2019-08-08 19:04:03,953 BlurrLionizerProwlDeckTester [getChancesOfHavingUpgradeOnTurn2] INFO - Probability of having any of [{"name":"Force Field","pips":["WHITE"]}, {"name":"Turbo Boosters","pips":["ORANGE"]}, {"name":"Matrix of Leadership","pips":["ORANGE","BLUE"]}] in hand on turn 2 is: 73%
2019-08-08 19:04:04,046 BlurrLionizerProwlDeckTester [getChancesOfHavingUntapOnTurn3] INFO - Probability of having all of [{"name":"Start Your Engines","pips":["BLUE"]}, {"name":"Turbo Boosters","pips":["ORANGE"]}] in hand on turn 3 is: 14%
2019-08-08 19:04:04,097 BlurrLionizerProwlDeckTester [getChancesOfHavingDamageBoostActionOnTurn3] INFO - Probability of having any of [{"name":"Press The Advantage","pips":["ORANGE","GREEN"]}, {"name":"Reckless Charge","pips":["ORANGE"]}] in hand on turn 3 is: 64%
2019-08-08 19:04:04,146 BlurrLionizerProwlDeckTester [getChancesOfHavingAlmostIdealHandTurn3] INFO - Probability of having all of [{"name":"Start Your Engines","pips":["BLUE"]}, {"name":"Matrix of Leadership","pips":["ORANGE","BLUE"]}] in hand on turn 3 is: 13%
2019-08-08 19:04:04,207 BlurrLionizerProwlDeckTester [getChancesOfHavingIdealHandTurn3WithTurboBoostersUntap] INFO - Running chances of having all of [{"name":"Turbo Boosters","pips":["ORANGE"]}] and any of [{"name":"Reckless Charge","pips":["ORANGE"]}, {"name":"Press The Advantage","pips":["ORANGE","GREEN"]}] in hand on turn 3 for deck 23%
```


### And More!