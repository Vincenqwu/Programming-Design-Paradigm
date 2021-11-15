package sanctuary;

import java.util.ArrayList;
import java.util.List;

/**
 * Enclosure represents a troop that contains a
 * groups of monkeys of the same species.
 */
public class Enclosure {
  private final int capacity;
  private int availableSpace;
  private List<Animal> residents;
  private Animal.Species species;

  /**
   * Construct an enclosure that that can host a single troop,
   * and each troop has a given capacity.
   *
   * @param capacity space of the enclosure.
   * @param species  species of the residing monkeys.
   * @throws new IllegalArgument Exception
   */
  public Enclosure(int capacity, Animal.Species species) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Troop capacity must be a positive integer");
    }
    this.capacity = capacity;
    this.availableSpace = capacity;
    this.residents = new ArrayList<Animal>();
    this.species = species;
  }

  /**
   * Return the capacity of enclosure.
   *
   * @return Capacity
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Return the available space of enclosure.
   *
   * @return Available space
   */
  public int getAvailableSpace() {
    return this.availableSpace;
  }

  /**
   * Return the list of animals in the enclosure.
   *
   * @return List of animals
   */
  public List<Animal> getResidents() {
    return this.residents;
  }

  /**
   * Return the species of animal in the enclosure.
   *
   * @return Species type
   */
  public Animal.Species getSpecies() {
    return this.species;
  }

  /**
   * Check whether the enclosure is empty.
   *
   * @return Boolean
   */
  public boolean isEmpty() {
    return (this.capacity == this.availableSpace);
  }

  /**
   * If new species come in, set the species sign.
   *
   * @param newSpecies species type
   */
  public void newSpecies(Animal.Species newSpecies) {
    this.species = newSpecies;
  }

  /**
   * Add new monkeys to the troop.
   *
   * @param resident a monkey object
   */
  public void addResident(Animal resident) {
    int space;
    if (resident.getSize() < 10) {
      space = 1;
    } else if (resident.getSize() > 20) {
      space = 10;
    } else {
      space = 5;
    }
    if (this.availableSpace < space) {
      throw new IllegalStateException("Maximum capacity exceeded for the enclosure");
    } else if (this.species != resident.getSpecies() && !this.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      sb.append("Only the '" + this.species + "' species can live in this enclosure");
      throw new IllegalStateException(sb.toString());
    } else {
      this.newSpecies(resident.getSpecies());
      this.residents.add(resident);
      this.availableSpace -= space;
    }
  }

  /**
   * Get the sign of the enclosure.
   * Sign includes monkey name, sex, and favorite food.
   */
  public void sign() {
    for (Animal a : this.residents) {
      System.out.println("Name: " + a.getName() + ", Sex: "
              + a.getSex() + ", Favorite food: " + a.getFavFoods());
    }
  }


}
