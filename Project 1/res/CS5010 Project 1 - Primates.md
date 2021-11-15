# CS5010 Project 1 - Primates
### Qiuan Wu
##
## About/Overview

Implement a sanctuary system that provide housing for different species of monkeys. 

Isolation is used to keep monkeys when they first arrive at the sanctuary and anytime they are receiving medical attention. Isolation consists of n number of cages each of which can house a single animal. Enclosures can host a single troop of monkeys. Each troop consists of a single species which includes: drill, guereza, howler, mangabey, saki, spider, squirrel, and tamarin.

The sanctuary consist of one isolation with n number of troops and m number of enclosures.

## Features
- isolate the monkey first and then move them to the enclosure
- expand the sanctuary if necessary
- Report the species that are currently being housed in alphabetical order.
- Look up where a particular species is currently housed. 
- Produce a sign for a given enclosure that lists each individual monkey that is currently housed there. 
- Produce an alphabetical list (by name) of all of the monkeys housed in the Sanctuary. 
- Produce a shopping list of the favorite foods of the inhabitants of the Sanctuary. 

## How To Run
To run the jar file:
```sh
%java -jar res/"Project 1.jar"
```

To run the program in IDE (recommend):
Locate the StartWorking.java file and run the main method.

To run the program in terminal(not recommend):
```sh
%cd src  
%javac sanctuary/StartWorking.java
%java sanctuary/StartWorking
```

## How to Use the Program
Implement the sanctuary methods and functionalities in the main class 


## Example Run
#Start putting the first group of monkeys to isolation.....

#Move all the currently isolated monkeys to enclosures.....
Success: Move in successfully
Success: Move in successfully
Success: Move in successfully
Success: Move in successfully
Success: Move in successfully
Success: Move in successfully
Failure: All of the enclosures are out of space

#report species.....
drill: Enclosure
guereza: Enclosure
howler: Enclosure
mangabey: Enclosure
tamarin: Isolation

#Expand the sanctuary by adding 2 cages and 2 troops.....
2 cages and 2 troops has been added to the sanctuary

#Start putting the second group of monkeys to isolation.....

#Look up where all the species are at.....
drill reside in both isolation and enclosures
howler reside in enclosure
spider reside in isolation
guereza reside in enclosure
mangabey reside in both isolation and enclosures
squirrel is not found in the sanctuary
tamarin reside in isolation
saki reside in isolation

#Get enclosure sign.....
Troop 0:
Name: Drill1, Sex: male, Favorite food: fruits
Name: Drill2, Sex: female, Favorite food: insects
Troop 1:
Name: Howler1, Sex: male, Favorite food: leaves
Name: Howler2, Sex: female, Favorite food: nuts
Troop 2:
Name: Mangabey1, Sex: male, Favorite food: seeds
Troop 3:
Name: Guereza1, Sex: female, Favorite food: fruits

#List all monkeys.....
Name: Drill1, Species: drill, House: Enclosure
Name: Drill2, Species: drill, House: Enclosure
Name: Drill3, Species: drill, House: Isolation
Name: Guereza1, Species: guereza, House: Enclosure
Name: Howler1, Species: howler, House: Enclosure
Name: Howler2, Species: howler, House: Enclosure
Name: Mangabey1, Species: mangabey, House: Enclosure
Name: Mangabey2, Species: mangabey, House: Isolation
Name: Mangabey3, Species: mangabey, House: Isolation
Name: Saki1, Species: saki, House: Isolation
Name: Saki2, Species: saki, House: Isolation
Name: Spider1, Species: spider, House: Isolation
Name: Tamarin1, Species: tamarin, House: Isolation
Name: Tamarin2, Species: tamarin, House: Isolation

#List favorite foods.....
eggs: 250
fruits: 1100
insects: 1000
leaves: 500
nuts: 500
seeds: 1100
treeSap: 750

## Design/Model Changes
- Update the implementSanctuary class 
- Add attributes for unknown animal species
- Add new test cases for get enclosure sign

## Assumptions
- Users are free to expand the sanctuary if there are not enough space.
- If both enclosure and isolation are full, it will no longer add new monkeys to the sanctuary.
- Monkeys that are currently living in enclosures will stay there permanently and will not be taken out.
- All new monkeys must go through isolation before been moved to enclosures.

## Limitation
This program assumes only one sanctuary is created, it will not consider any new monkeys that are not able to get in due to space shortages. There is no contact between different sanctuaries. So this program is not able to track monkeys that are in other sanctuaries.
## Citation
https://northeastern.instructure.com/courses/90394/assignments/1103220
