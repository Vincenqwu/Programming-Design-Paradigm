# CS5010 Project 3 - Dungeon Model
### Qiuan Wu
##
## About/Overview

Implement a dungeons model that consists of interconnected caves and tunnels where the player can explore via traveling cave by cave through the tunnels.
Some caves also contains treasures that the player can collect. A player will start from a random cave and try to get to the destination cave.

## Features
- Each location can have 1 to 4 entrances
- The dungeon can wrap from one side to the other side of the grid
- The dungeon is generated randomly based the input interconnectivity
- 20% of the caves contains different amount of treasures
- The player in a cave can randomly choose any entrance in order to move to other locations

## How To Run
To run the jar file:
```sh
%java -jar res/"Project 3.jar"
```

To run the program in IDE (Eclipse/Intellij IDEA):
Locate the Driver.java file and run the main method

In the terminal, manually type in the dungeon dimensions, wrapping or not, and interconnectivity


## How to Use the Program
Implement the Dungeons methods in the main class:
- Create dungeons class
- Generate the dungeon
- Set up the player
- Let player move in the dungeon


## Example Run
Enter the number of rows: 
8
Enter the number of columns: 
9
Does the dungeon wrap or not? (1/0) :
0
Enter the interconnectivity: 
10

Player's start Cave: 42, (5, 6)
Player's end Cave: 66, (8, 3)

Player in cave 42, (5, 6), with the following entrances:
North South West East 
The play has collected the following treasures:
None
This cave has the following treasures:
None

Player in cave 32, (4, 5), with the following entrances:
North West East 
The play has collected the following treasures:
None
This cave has the following treasures:
None

Player in cave 31, (4, 4), with the following entrances:
North South West East 
The play has collected the following treasures:
None
This cave has the following treasures:
Diamonds*3 Rubies*0 Sapphires*0

Player in cave 30, (4, 3), with the following entrances:
North South West East 
The play has collected the following treasures:
Diamonds*3 Rubies*0 Sapphires*0
This cave has the following treasures:
None

Player in cave 38, (5, 2), with the following entrances:
North South East 
The play has collected the following treasures:
Diamonds*3 Rubies*0 Sapphires*0
This cave has the following treasures:
None

Player in cave 47, (6, 2), with the following entrances:
North South West East 
The play has collected the following treasures:
Diamonds*3 Rubies*0 Sapphires*0
This cave has the following treasures:
None

Player in cave 56, (7, 2), with the following entrances:
North South West East 
The play has collected the following treasures:
Diamonds*3 Rubies*0 Sapphires*0
This cave has the following treasures:
Diamonds*0 Rubies*1 Sapphires*2

Player in cave 57, (7, 3), with the following entrances:
North South West East 
The play has collected the following treasures:
Diamonds*3 Rubies*1 Sapphires*2
This cave has the following treasures:
None

Player in cave 66, (8, 3), with the following entrances:
North West East 
The play has collected the following treasures:
Diamonds*3 Rubies*1 Sapphires*2
This cave has the following treasures:
None

Player has reached the end


## Design/Model Changes
- Add Location interface and abstract class for the Cave and Tunnel class
- Add interface for the People class
- Implement Kruskal's algorithm in the ApplyKruskal class

## Assumptions
- The player can only move from cave to caves; Even if the player can go through tunnels, the player can only stop at a cave
- Only 20% if the caves can store treasures
- The distance between the start and end location is at least 5

## Limitation
- Since the distance between start and end locaion is at least 5, if the dungeon is too small, it is not possible to choose two locations with enough distance
- If the input interconnectivity is too large for the dungeon, an error may be throwed

## Citation
https://www.javatpoint.com/kruskal-algorithm-java
(Kruskal algorithm implemention in java)