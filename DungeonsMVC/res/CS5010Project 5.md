# CS5010 Project 5 - Graphical Adventure Game
### Qiuan Wu
##
## About/Overview

Implement a dungeons model that consists of interconnected caves and tunnels where the player can explore via traveling cave by cave through the tunnels.Some caves has treasures and arrows that the player can pickup. A kind of dangerious monster called the Otyugh resides in some of the caves. In order to survive from been eaten by the monster, the player need to use the arrow to kill the monster. If the player accidentially come into a cave that resides a monster, the player will be eaten and the game is over. A player will start from a random cave and try to get to the destination cave without been eaten by the monster.

## Features
- Each location can have 1 to 4 entrances
- The dungeon can wrap from one side to the other side of the grid
- The dungeon is generated randomly based the input interconnectivity
- To begin with, the player is automatically equipped with three arrows
- Player can pick up arrows or treasures
- The player will scan the monster smell when moving to a new location
- If the arrow bag is empty, the player cannot shoot unless picking up new arrows in caves or tunnels
- In order to hit the monster with arrow, the player must set the direction and number of caves for the arrow to travel
- The player can shoot arrows in both caves and tunnels

## How To Run
To run the jar file:
```sh
%java -jar res/Project_5.jar
```

To run the program in IDE (Eclipse/Intellij IDEA):
Run the Main.java file under src/view folder


## How to Use the Program
- In the game window, click on the Menu, then select "New Game". A new window will pop up and user will type in the information about the dungeons
- To move from location to location, user can either click on the NESW buttons or press the arrow keys
- To shoot an arrow, press S on the keyboard together with an arrow key. A new window will pop up and user will select the distance
- To pick up arrows, press A on the keyboard
- To pick up treasures, press T on the keyboard
- If user is unsatisfied with the current game prograss, restart is available in the Menu
- User can quit the game anytime

### Example Run
Shown in the Screen shot folder

## Design/Model Changes
- Add a DungeonsView interface and DungeonsViewImpl class
- Add a DungeonsController interface and DungeonsController class
- Add a SettingDialog class
- Add a InstructionDialog class
- Add a ShootDialog class
- Add a DungeonsViewInfo class

## Assumptions
- The percentage of the arrows and treasures are the same 
- The player can stop at both caves and tunnels
- The player can shoot at both caves and tunnels
- The monster never exist in the start cave, but always exist in the end cave
- THe arrow can be found in both caves and tunnels, while the treasures exists in caves
- The distance between the start and end location is at least 5

## Limitation
- Since the distance between start and end locaion is at least 5, if the dungeon is too small, it is not possible to choose two locations with enough distance
- Each cave can only exist at most one monster

## Citation
https://www.javatpoint.com/kruskal-algorithm-java
(Kruskal algorithm implemention in java)
https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
(Java Swing)