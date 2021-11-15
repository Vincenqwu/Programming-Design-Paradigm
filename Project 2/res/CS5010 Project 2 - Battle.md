# CS5010 Project 2 - Battle
### Qiuan Wu
##
## About/Overview

Two players will come to the arena with their basic abilities.  Players then equip themselves with gears and weapon and take turns to attack one another until one of the players is dead. If after 30 rounds and nobody wins, the battle is a draw. Player with the higher charisma will start first.  After the first battle, a rematch is available.

## Features
- Player can do the battle without been equipped
- Player's 4 basic abilities, attack striking power, weapon damage can vary given a range
- Maximun 30 rounds of attack 
- Player can equip a maximum of 20 gears
- If the battle result is not desirable, a ramatch is availabe

## How To Run
To run the jar file:
```sh
%java -jar res/"Project 2.jar"
```

To run the program in IDE (Eclipse/Intellij IDEA):
Locate the Driver.java file and run the main method.


## How to Use the Program
Implement the RunBattle methods in the main class 


## Example Run
Battle game Started.....
Two players will come to the arena with their basic abilities.  Players then equip themselves with gears and weapon and take turns to attack one another until one of the players is dead. If after 30 rounds and nobody wins, the battle is a draw. Player with the higher charisma will start first.  After the first battle, a rematch is available.

Players come into the battle arena.....


Player 1 comes in:
strength = 13
constitution = 11
dexterity = 13
charisma = 11

Player 2 comes in:
strength = 12
constitution = 13
dexterity = 10
charisma = 9

Players start to equip gears and weapons.....

Player 1 has equipped the following gears:
Name: pt1, Strength Effect: 1, Constitution Effect: 0, Dexterity Effect: 2, Charisma Effect: 0
Name: pt10, Strength Effect: 0, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 5
Name: pt11, Strength Effect: 8, Constitution Effect: 0, Dexterity Effect: 0, Charisma Effect: 2
Name: pt14, Strength Effect: 4, Constitution Effect: -1, Dexterity Effect: -2, Charisma Effect: 0
Name: pt15, Strength Effect: -1, Constitution Effect: 0, Dexterity Effect: 3, Charisma Effect: -2
Name: pt3, Strength Effect: 8, Constitution Effect: 0, Dexterity Effect: 0, Charisma Effect: 0
Name: pt4, Strength Effect: 0, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 0
Name: pt5, Strength Effect: 6, Constitution Effect: 2, Dexterity Effect: 0, Charisma Effect: 0
Name: pt7, Strength Effect: 4, Constitution Effect: 5, Dexterity Effect: 5, Charisma Effect: 0
Name: pt8, Strength Effect: 0, Constitution Effect: 1, Dexterity Effect: -3, Charisma Effect: 0
Name: pt9, Strength Effect: 6, Constitution Effect: 0, Dexterity Effect: 4, Charisma Effect: 0
Name: bt_large4, Size: LARGE, Strength Effect: 8, Constitution Effect: 0, Dexterity Effect: 0, Charisma Effect: 1
Name: bt_med2, Size: MEDIUM, Strength Effect: 0, Constitution Effect: 4, Dexterity Effect: 0, Charisma Effect: 1
Name: bt_med4, Size: MEDIUM, Strength Effect: -1, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 0
Name: bt_small1, Size: SMALL, Strength Effect: 5, Constitution Effect: 0, Dexterity Effect: -1, Charisma Effect: 0
Name: bt_small2, Size: SMALL, Strength Effect: 0, Constitution Effect: 1, Dexterity Effect: 1, Charisma Effect: 0
Name: fw+5, Strength Effect: 0, Constitution Effect: 0, Dexterity Effect: 5, Charisma Effect: 0

Player 1 has received the following weapon:
Weapon: FRAILS

Player 2 has equipped the following gears:
Name: hg+5, Strength Effect: 0, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 0
Name: pt1, Strength Effect: 1, Constitution Effect: 0, Dexterity Effect: 2, Charisma Effect: 0
Name: pt10, Strength Effect: 0, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 5
Name: pt12, Strength Effect: 8, Constitution Effect: 0, Dexterity Effect: 0, Charisma Effect: 5
Name: pt13, Strength Effect: -1, Constitution Effect: 3, Dexterity Effect: -1, Charisma Effect: 2
Name: pt15, Strength Effect: -1, Constitution Effect: 0, Dexterity Effect: 3, Charisma Effect: -2
Name: pt2, Strength Effect: 2, Constitution Effect: 6, Dexterity Effect: 0, Charisma Effect: 0
Name: pt4, Strength Effect: 0, Constitution Effect: 5, Dexterity Effect: 0, Charisma Effect: 0
Name: pt8, Strength Effect: 0, Constitution Effect: 1, Dexterity Effect: -3, Charisma Effect: 0
Name: pt9, Strength Effect: 6, Constitution Effect: 0, Dexterity Effect: 4, Charisma Effect: 0
Name: bt_large2, Size: LARGE, Strength Effect: 0, Constitution Effect: 6, Dexterity Effect: 0, Charisma Effect: 5
Name: bt_large5, Size: LARGE, Strength Effect: 0, Constitution Effect: 0, Dexterity Effect: -5, Charisma Effect: 8
Name: bt_small2, Size: SMALL, Strength Effect: 0, Constitution Effect: 1, Dexterity Effect: 1, Charisma Effect: 0
Name: bt_small3, Size: SMALL, Strength Effect: -1, Constitution Effect: 0, Dexterity Effect: -2, Charisma Effect: 0
Name: fw-2, Strength Effect: 0, Constitution Effect: 0, Dexterity Effect: -2, Charisma Effect: 0

Player 2 has received the following weapon:
Weapon: KATANAS

Decide who go first based on higher charisma.....

Start Battle.....

Nobody wins yet, the battle is a draw

Next Player: 2
Player 2 with striking power of 30 make attack with damage 36
Failed. The attack is avoided by the opponent with avoidability of 31

Next Player: 1
Player 1 with striking power of 62 make attack with damage 72
Success! The attack has successfully hit the opponent with damage of 27

Next Player: 2
Player 2 with striking power of 33 make attack with damage 36
Failed. The attack does not get through the constitution of opponent

Next Player: 1
Player 1 with striking power of 67 make attack with damage 72
Success! The attack has successfully hit the opponent with damage of 27

Battle is over, and the winner is Player 1

Rematch.....

Players has been reset

Decide who go first based on higher charisma.....

Start Battle.....

Next Player: 1
Player 1 with striking power of 45 make attack with damage 54
Success! The attack has successfully hit the opponent with damage of 30

Next Player: 2
Player 2 with striking power of 55 make attack with damage 57
Success! The attack has successfully hit the opponent with damage of 30

Next Player: 1
Player 1 with striking power of 49 make attack with damage 55
Success! The attack has successfully hit the opponent with damage of 31

Battle is over, and the winner is Player 1
## Design/Model Changes
- Add interfaces and abstract classes for the gear and weapon 
- Create classes for every single type of gear and weapon
- Add interface for player class

## Assumptions
- Players can enter into the arena and battle with only basic abilites (No gears)
- Player can only wear one headgear, one footwear, and maximum 10 units of belts
- Rematch will reset everything to the player, including basic abilities, gears, and weapon

## Limitation
- The Player can do the battle without gears, but they must have weapon to battle
- The RunBattle class do not have the obtion to run the battle automatically. Driver function is needed to use the methods of RunBattle in a certain order.

## Citation
https://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
(This is for how to generate a random number within a certain range)