package sanctuary;

import java.util.ArrayList;
import java.util.List;

/**
 * Start implementing the sanctuary class.
 */
public class StartWorking {

  /**
   * Main method for implementSanctuary.
   *
   * @param str useless
   */
  public static void main(String[] str) {
    List<Animal> monkeyGroup1 = new ArrayList<>();
    List<Animal> monkeyGroup2 = new ArrayList<>();
    List<Animal> monkeyGroup3 = new ArrayList<>();

    // Create many monkey objects
    // Split monkeys into three groups
    // for each group, monkeys will be put into sanctuary at the same time

    Animal monkey1 = new Animal("Drill1", Animal.Species.drill, Animal.Sex.male,
            35, 50, 5, Animal.FavFoods.fruits, "Isolation");
    Animal monkey2 = new Animal("Drill2", Animal.Species.drill, Animal.Sex.female,
            30, 40, 4, Animal.FavFoods.insects, "Isolation");
    Animal monkey3 = new Animal("Howler1", Animal.Species.howler, Animal.Sex.male,
            25, 30, 3, Animal.FavFoods.leaves, "Isolation");
    Animal monkey4 = new Animal("Howler2", Animal.Species.howler, Animal.Sex.female,
            20, 35, 2, Animal.FavFoods.nuts, "Isolation");
    Animal monkey5 = new Animal("Mangabey1", Animal.Species.mangabey, Animal.Sex.male,
            22, 32, 6, Animal.FavFoods.seeds, "Isolation");
    Animal monkey6 = new Animal("Guereza1", Animal.Species.guereza, Animal.Sex.female,
            35, 43, 3, Animal.FavFoods.fruits, "Isolation");
    Animal monkey7 = new Animal("Tamarin1", Animal.Species.tamarin, Animal.Sex.male,
            6, 10, 2, Animal.FavFoods.seeds, "Isolation");

    monkeyGroup1.add(monkey1);
    monkeyGroup1.add(monkey2);
    monkeyGroup1.add(monkey3);
    monkeyGroup1.add(monkey4);
    monkeyGroup1.add(monkey5);
    monkeyGroup1.add(monkey6);
    monkeyGroup1.add(monkey7);

    Animal monkey8 = new Animal("Tamarin2", Animal.Species.tamarin, Animal.Sex.female,
            5, 9, 3, Animal.FavFoods.fruits, "Isolation");
    Animal monkey9 = new Animal("Mangabey2", Animal.Species.mangabey, Animal.Sex.female,
            19, 25, 3, Animal.FavFoods.treeSap, "Isolation");
    Animal monkey10 = new Animal("Mangabey3", Animal.Species.mangabey, Animal.Sex.male,
            15, 21, 2, Animal.FavFoods.eggs, "Isolation");
    Animal monkey11 = new Animal("Drill3", Animal.Species.drill, Animal.Sex.female,
            30, 41, 4, Animal.FavFoods.seeds, "Isolation");
    Animal monkey12 = new Animal("Saki1", Animal.Species.saki, Animal.Sex.male,
            32, 46, 6, Animal.FavFoods.insects, "Isolation");
    Animal monkey13 = new Animal("Saki2", Animal.Species.saki, Animal.Sex.female,
            36, 51, 4, Animal.FavFoods.treeSap, "Isolation");
    Animal monkey14 = new Animal("Spider1", Animal.Species.spider, Animal.Sex.male,
            12, 15, 2, Animal.FavFoods.nuts, "Isolation");

    monkeyGroup2.add(monkey8);
    monkeyGroup2.add(monkey9);
    monkeyGroup2.add(monkey10);
    monkeyGroup2.add(monkey11);
    monkeyGroup2.add(monkey12);
    monkeyGroup2.add(monkey13);
    monkeyGroup2.add(monkey14);

    Animal monkey15 = new Animal("Spider2", Animal.Species.spider, Animal.Sex.female,
            9, 9, 1, Animal.FavFoods.seeds, "Isolation");
    Animal monkey16 = new Animal("Guereza2", Animal.Species.guereza, Animal.Sex.male,
            36, 38, 2, Animal.FavFoods.fruits, "Isolation");
    Animal monkey17 = new Animal("Guereza3", Animal.Species.guereza, Animal.Sex.male,
            39, 40, 4, Animal.FavFoods.eggs, "Isolation");
    Animal monkey18 = new Animal("Squirrel", Animal.Species.squirrel, Animal.Sex.male,
            8, 10, 4, Animal.FavFoods.fruits, "Isolation");
    Animal monkey19 = new Animal("Mangabey4", Animal.Species.mangabey, Animal.Sex.female,
            15, 20, 2, Animal.FavFoods.insects, "Isolation");
    Animal monkey20 = new Animal("Mangabey5", Animal.Species.mangabey, Animal.Sex.male,
            23, 24, 3, Animal.FavFoods.leaves, "Isolation");
    Animal monkey21 = new Animal("Drill4", Animal.Species.drill, Animal.Sex.female,
            26, 34, 3, Animal.FavFoods.leaves, "Isolation");

    monkeyGroup3.add(monkey15);
    monkeyGroup3.add(monkey16);
    monkeyGroup3.add(monkey17);
    monkeyGroup3.add(monkey18);
    monkeyGroup3.add(monkey19);
    monkeyGroup3.add(monkey20);
    monkeyGroup3.add(monkey21);

    // Initialize sanctuary based on parameters
    // n, m, and troop capacity
    int n = 18;
    int m = 4;
    int troopCapacity = 35;
    Sanctuary sanctuary = new ImplementSanctuary(n, m, troopCapacity);

    // Start putting first group of monkeys
    // into sanctuary for isolation
    System.out.println("\n#Start putting the "
            + "first group of monkeys to isolation.....");
    for (Animal a : monkeyGroup1) {
      sanctuary.isolateFirst(a);
    }

    // Move all the currently isolated monkeys to enclosures
    System.out.println("\n#Move all the currently "
            + "isolated monkeys to enclosures.....");
    sanctuary.startMoving();

    System.out.println("\n#report species.....");
    sanctuary.reportSpecies();

    // Expand the sanctuary:
    // add 2 cages and 2 troops
    System.out.println("\n#Expand the sanctuary "
            + "by adding 2 cages and 2 troops.....");
    sanctuary.expand(2, 2);


    // Start putting second group of monkeys
    // into sanctuary for isolation
    System.out.println("\n#Start putting the second "
            + "group of monkeys to isolation.....");
    for (Animal a : monkeyGroup2) {
      sanctuary.isolateFirst(a);
    }

    // Look up a species
    System.out.println("\n#Look up where all the species are at.....");
    sanctuary.lookUpSpecies(Animal.Species.drill.toString());
    sanctuary.lookUpSpecies(Animal.Species.howler.toString());
    sanctuary.lookUpSpecies(Animal.Species.spider.toString());
    sanctuary.lookUpSpecies(Animal.Species.guereza.toString());
    sanctuary.lookUpSpecies(Animal.Species.mangabey.toString());
    sanctuary.lookUpSpecies(Animal.Species.squirrel.toString());
    sanctuary.lookUpSpecies(Animal.Species.tamarin.toString());
    sanctuary.lookUpSpecies(Animal.Species.saki.toString());

    // Get enclosure sign
    System.out.println("\n#Get enclosure sign.....");
    for (int i = 0; i < m; i++) {
      System.out.println("Troop " + i + ":");
      sanctuary.getEnclosureSign(i);
    }

    // List all animals
    System.out.println("\n#List all monkeys.....");
    sanctuary.listAllAnimals();

    // List favorite foods
    System.out.println("\n#List favorite foods.....");
    sanctuary.listFavFoods();

    // Expand the sanctuary again
    // Add 5 cages and 2 troops
    System.out.println("\n#Add 5 cages and 2 troops.....");
    sanctuary.expand(5, 2);

    // Move all the currently isolated monkeys to enclosures
    System.out.println("\n#Move all the currently "
            + "isolated monkeys to enclosures.....");
    sanctuary.startMoving();

    System.out.println("\n#report species.....");
    sanctuary.reportSpecies();


    // Start putting the third group of monkeys
    // into sanctuary for isolation
    System.out.println("\n#Start putting the third "
            + "group of monkeys to isolation.....");
    for (Animal a : monkeyGroup3) {
      sanctuary.isolateFirst(a);
    }


    // Look up a species
    System.out.println("\n#Look up where all the species are at.....");
    sanctuary.lookUpSpecies(Animal.Species.drill.toString());
    sanctuary.lookUpSpecies(Animal.Species.howler.toString());
    sanctuary.lookUpSpecies(Animal.Species.spider.toString());
    sanctuary.lookUpSpecies(Animal.Species.guereza.toString());
    sanctuary.lookUpSpecies(Animal.Species.mangabey.toString());
    sanctuary.lookUpSpecies(Animal.Species.squirrel.toString());
    sanctuary.lookUpSpecies(Animal.Species.tamarin.toString());
    sanctuary.lookUpSpecies(Animal.Species.saki.toString());

    // Get enclosure sign
    System.out.println("\n#Get enclosure sign.....");
    for (int i = 0; i < m; i++) {
      System.out.println("Troop " + i + ":");
      sanctuary.getEnclosureSign(i);
    }

    // List all animals
    System.out.println("\n#List all monkeys.....");
    sanctuary.listAllAnimals();

    // List favorite foods
    System.out.println("\n#List favorite foods.....");
    sanctuary.listFavFoods();

    // Move all the currently isolated monkeys to enclosures
    System.out.println("\n#Move all the currently "
            + "isolated monkeys to enclosures.....");
    sanctuary.startMoving();

    System.out.println("\n#report species.....");
    sanctuary.reportSpecies();
  }
}
