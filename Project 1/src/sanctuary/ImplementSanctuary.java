package sanctuary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implement the sanctuary interface, given the number of
 * cages, number of enclosures, and troop capacity.
 */

public class ImplementSanctuary implements Sanctuary {

  private int troopCapacity;
  private Isolation isolationCages;
  private List<Enclosure> listOfTroops;
  private int n;
  private int m;

  /**
   * Construct a sanctuary given the number of
   * cages, number of enclosures, and troop capacity.
   *
   * @param n             number of cages in isolation
   * @param m             number of enclosure troops
   * @param troopCapacity the capacity of
   *                      each troop in square meter
   */
  public ImplementSanctuary(int n, int m, int troopCapacity) {
    this.troopCapacity = troopCapacity;
    if (n <= 0) {
      throw new IllegalArgumentException("Isolation "
              + "cage number must be a positive integer");
    }
    if (m <= 0) {
      throw new IllegalArgumentException("Enclosure "
              + "number must be a positive integer");
    }
    this.n = n;
    this.m = m;
    this.isolationCages = new Isolation(n);
    this.listOfTroops = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      this.listOfTroops.add(
              new Enclosure(troopCapacity, Animal.Species.unknown));
    }
  }

  /**
   * Get number of cages.
   *
   * @return num of cages
   */
  public int getNumCages() {
    return this.n;
  }

  /**
   * Get number of troops.
   * @return num of troops
   */
  public int getNumTroops() {
    return this.m;
  }

  /**
   * Put given monkey to isolation.
   *
   * @param member Monkey object
   */
  public void isolateFirst(Animal member) {
    if (this.isolationCages.getFreeSpace() > 0) {
      this.isolationCages.addMember(member);
    }
  }

  /**
   * Increase the number of cages in isolations and number of troops.
   *
   * @param nNewCages  number of cages in isolation
   * @param nNewTroops number of troops
   */
  public void expand(int nNewCages, int nNewTroops) {
    this.n += nNewCages;
    this.m += nNewTroops;
    this.isolationCages.addCages(nNewCages);
    for (int i = 0; i < nNewTroops; i++) {
      this.listOfTroops.add(
              new Enclosure(this.troopCapacity, Animal.Species.unknown));
    }
    StringBuilder sb = new StringBuilder();
    sb.append(nNewCages + " cages and " + nNewTroops
            + " troops has been added to the sanctuary");
    System.out.println(sb);
  }

  /**
   * Extract all the monkeys currently in isolations
   * and put them into troops.
   */
  public void startMoving() {
    while (!isolationCages.getIsolatedAnimals().isEmpty()) {
      Animal removedMonkey = isolationCages.removeMember();
      boolean success = this.moveToEnclosures(removedMonkey);
      if (!success) {
        isolationCages.addMember(removedMonkey);
        break;
      }
    }
  }

  private boolean moveToEnclosures(Animal member) {
    boolean success = false;
    for (Enclosure t : this.listOfTroops) {
      if (t.getSpecies() == member.getSpecies()
              && t.getAvailableSpace() >= this.getSpace(member.getSize())) {
        t.addResident(member);
        success = true;
        break;
      }
      if (t.isEmpty()) {
        t.addResident(member);
        success = true;
        break;
      }
    }
    if (success) {
      member.changeHouse("Enclosure");
      System.out.println("Success: Move in successfully");
    } else {
      System.out.println(("Failure: All of the enclosures are out of space"));
    }
    return success;
  }

  /**
   * Report the species that are currently being housed in alphabetical
   * order. The list should include where in the sanctuary each species
   * is (both in enclosures and in isolation).
   */
  public void reportSpecies() {
    Map<String, List<String>> speciesHouses = new TreeMap<>();

    for (Animal a : this.isolationCages.getIsolatedAnimals()) {
      if (!speciesHouses.containsKey(a.getSpecies().toString())) {
        List<String> l = new ArrayList<>();
        l.add("Isolation");
        speciesHouses.put(a.getSpecies().toString(), l);
      }
    }
    for (Enclosure t : this.listOfTroops) {
      if (!speciesHouses.containsKey(t.getSpecies().toString())) {
        List<String> l = new ArrayList<>();
        l.add("Enclosure");
        speciesHouses.put(t.getSpecies().toString(), l);
      } else if (speciesHouses.containsKey(t.getSpecies().toString())
              && speciesHouses.get(t.getSpecies().toString()).get(0).equals("Isolation")) {
        List<String> l = new ArrayList<>();
        l.add("Isolation");
        l.add("Enclosure");
        speciesHouses.put(t.getSpecies().toString(), l);
      }
    }
    for (String key : speciesHouses.keySet()) {
      List<String> value = speciesHouses.get(key);
      String listHouse = String.join(", ", value);
      System.out.println(key + ": " + listHouse);
    }

  }

  /**
   * look up where a particular species is currently housed.
   * If none of a particular species is currently being housed,
   * it should report this fact.
   *
   * @param species species of a monkey.
   */
  public void lookUpSpecies(String species) {
    boolean inCage = false;
    boolean inTroop = false;
    for (Animal a : this.isolationCages.getIsolatedAnimals()) {
      if (a.getSpecies().toString().equals(species)) {
        inCage = true;
        break;
      }
    }
    for (Enclosure t : this.listOfTroops) {
      if (t.getSpecies().toString().equals(species)) {
        inTroop = true;
        break;
      }
    }
    if (inCage && inTroop) {
      System.out.println(species + " reside in both isolation and enclosures");
    } else if (inCage) {
      System.out.println(species + " reside in isolation");
    } else if (inTroop) {
      System.out.println((species + " reside in enclosure"));
    } else {
      System.out.println(species + " is not found in the sanctuary");
    }
  }

  /**
   * Get the sign of the enclosure based on index.
   *
   * @param index index of the enclosure.
   */
  public void getEnclosureSign(int index) {
    this.listOfTroops.get(index).sign();
  }

  /**
   * Produce an alphabetical list (by name) of all of the monkeys housed in the
   * Sanctuary. This information should include where each monkey can be found.
   */
  public void listAllAnimals() {
    Map<String, String> animalMap = new TreeMap<>();
    for (Animal a : this.isolationCages.getIsolatedAnimals()) {
      animalMap.put(a.getName(), a.toString());
    }
    for (Enclosure t : this.listOfTroops) {
      for (Animal a : t.getResidents()) {
        animalMap.put(a.getName(), a.toString());
      }
    }
    for (String key : animalMap.keySet()) {
      String value = animalMap.get(key);
      System.out.println(value);
    }
  }

  /**
   * Produce a shopping list of the favorite foods of the inhabitants of the Sanctuary.
   * For each food listed, the quantity needed should also be listed.
   * Large monkeys should receive 500 gr of their favorite food while medium
   * and small monkeys require 250 gr and 100 gr respectively.
   */
  public void listFavFoods() {
    Map<String, Integer> shoppingList = new TreeMap<>();
    int foodWeight;
    for (Animal a : this.isolationCages.getIsolatedAnimals()) {
      foodWeight = this.getFoodWeight(a.getSize());
      if (shoppingList.containsKey(a.getFavFoods().toString())) {
        shoppingList.put(a.getFavFoods().toString(),
                shoppingList.get(a.getFavFoods().toString()) + foodWeight);
      } else {
        shoppingList.put(a.getFavFoods().toString(), foodWeight);
      }
    }
    for (Enclosure t : this.listOfTroops) {
      for (Animal a : t.getResidents()) {
        foodWeight = this.getFoodWeight(a.getSize());
        if (shoppingList.containsKey(a.getFavFoods().toString())) {
          shoppingList.put(a.getFavFoods().toString(),
                  shoppingList.get(a.getFavFoods().toString()) + foodWeight);
        } else {
          shoppingList.put(a.getFavFoods().toString(), foodWeight);
        }
      }
    }
    for (String key : shoppingList.keySet()) {
      int value = shoppingList.get(key);
      System.out.println(key + ": " + value);
    }
  }

  private int getFoodWeight(int size) {
    if (size < 10) {
      return 100;
    } else if (size > 20) {
      return 500;
    } else {
      return 250;
    }
  }

  private int getSpace(int size) {
    if (size < 10) {
      return 1;
    } else if (size > 20) {
      return 10;
    } else {
      return 5;
    }
  }

}
