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

### Creating a Deck Tester For Your Deck
Create a new class that implements the DeckTester interface:

```java
public class JetBlueDeckTester implements DeckTester 
```

### Running the Simulator and Getting Defense Results!
```java
int tough = 1;
new JetBlueDeckTester().runDefenseSimulation(tough);
```

### Running the Simulator and Getting Offense Results!
```java
int bold = 0;
new JetBlueDeckTester().runAttackSimulation(bold);
```

### Finding Probabilities of Having Specific Battle Cards In Hand On a Specific Turn
```java
int turn = 2;
int bold = 4;
int tough = 0;

new BlurrLionizerProwlDeckTester().getChancesOfHavingAnyCardsOnTurn(turn, bold, tough, BattleCard.FORCE_FIELD, BattleCard.TURBO_BOOSTERS, BattleCard.MATRIX_OF_LEADERSHIP);
```

### And More!