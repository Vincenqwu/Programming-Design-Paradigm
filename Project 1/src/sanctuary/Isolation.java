package sanctuary;

import java.util.ArrayList;
import java.util.List;

/**
 * Isolation represents as number of cages,number of free cages,
 * and list of animals currently living inside.
 */
public class Isolation {
  private int numCages;
  private int freeSpace;
  private List<Animal> isolatedAnimals;

  /**
   * Construct an Isolation that takes an integer n,
   * which represents the number of cages in the isolation;
   * each cage can only host one animal.
   *
   * @param n number of cages.
   */
  public Isolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Number of cages must be a positive integer");
    }
    this.numCages = n;
    this.freeSpace = n;
    this.isolatedAnimals = new ArrayList<>();
  }

  /**
   * Expand the size of isolation.
   *
   * @param nNewCages number of new cages
   */
  public void addCages(int nNewCages) {
    this.numCages += nNewCages;
  }

  /**
   * Return the number of cages in isolation.
   *
   * @return number of cages.
   */
  public int getNumCages() {
    return this.numCages;
  }

  /**
   * Return the number of unused cages.
   *
   * @return number of unused cages
   */
  public int getFreeSpace() {
    return this.freeSpace;
  }

  /**
   * Return the list of animals in isolation.
   *
   * @return List of animal objects
   */
  public List<Animal> getIsolatedAnimals() {
    return this.isolatedAnimals;
  }

  /**
   * Add a animal object to the isolatedAnimals.
   *
   * @param member Animal object
   */
  public boolean addMember(Animal member) {
    boolean success = false;
    if (this.freeSpace > 0) {
      this.freeSpace -= 1;
      this.isolatedAnimals.add(member);
      success = true;
    } else {
      throw new IllegalStateException("Maximum isolation capacity exceeded!");
    }
    return success;
  }

  /**
   * Remove the first animal object inside isolatedAnimals.
   *
   * @return Animal object
   */
  public Animal removeMember() {
    if (this.freeSpace == this.numCages) {
      throw new IllegalStateException("All cages are empty!");
    } else {
      Animal removedAnimal = isolatedAnimals.get(0);
      isolatedAnimals.remove(0);
      this.freeSpace += 1;
      return removedAnimal;
    }
  }
}
